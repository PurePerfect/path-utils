/*
 * Copyright [2008] PurePerfect.com Licensed under the Apache License, Version
 * 2.0 (the "License"); you may not use this file except in compliance with the
 * License.
 * 
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * 
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.pureperfect.pathutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.zip.ZipInputStream;

/**
 * Resolves relative and absolute paths.
 * 
 * @author J. Chris Folsom
 * @version 0.1
 * @since 0.1
 */
public abstract class PathResolver
{
	/**
	 * Returns a {@link PathResolver} that resolves paths relative to the entry
	 * in the zip file.
	 * 
	 * @param path
	 *            the entry in the zip file to use as a reference point.
	 * @param zip
	 *            the zip file
	 * @return a {@link PathResolver} that resolves paths relative to the entry
	 *         in the zip file.
	 */
	public static PathResolver createFor(String path, ZipInputStream zip)
	{
		return new ZipResolver(path, zip);
	}

	/**
	 * Returns a {@link PathResolver} that resolves paths relative to the
	 * specified file.
	 * 
	 * @param f
	 *            the parent file
	 * @return a {@link PathResolver} that resolves paths relative to the
	 *         specified file.
	 */
	public static PathResolver createFor(File f)
	{
		if (PathUtils.isArchive(f.getName()))
		{
			try
			{
				return createFor("", new ZipInputStream(new FileInputStream(f)));
			}
			catch (FileNotFoundException e)
			{
				throw new PathResolutionException(e);
			}
		}
		else
		{
			return new FileResolver(f);
		}
	}

	/**
	 * Returns a {@link PathResolver} that resolves paths relative to the given
	 * classpath.
	 * 
	 * @param classPath
	 *            the parent location of the resolver
	 * @return a {@link PathResolver} that resolves paths relative to the given
	 *         classpath.
	 */
	public static PathResolver createFor(String classPath)
	{
		return new ClasspathResolver(classPath);
	}

	/**
	 * Returns a {@link PathResolver} that resolves paths relative to the
	 * specified URL.
	 * 
	 * @param url
	 *            the parent url
	 * @return a {@link PathResolver} that resolves paths relative to the
	 *         specified URL.
	 */
	public static PathResolver createFor(URL url)
	{
		String s = url.toString();

		if (PathUtils.isArchive(s))
		{
			try
			{
				return createFor("", new ZipInputStream(url.openStream()));
			}
			catch (IOException e)
			{
				throw new PathResolutionException(e);
			}
		}
		else if (PathUtils.isLocalURL(url))
		{
			try
			{
				return new FileResolver(new File(url.toURI()));
			}
			catch (URISyntaxException e)
			{
				throw new PathResolutionException(e);
			}
		}
		else
		{
			return new URLResolver(url);
		}
	}

	/**
	 * Returns an instance that can only resolve absolute urls and not relative
	 * paths.
	 * 
	 * @param parentPath
	 *            the parent path
	 * @return an instance that can only resolve absolute urls and not relative
	 *         paths.
	 */
	public static PathResolver createNonResolvable(String parentPath)
	{
		return new UnresolvableResolver(parentPath);
	}

	PathResolver()
	{
		// Package private constructor
	}

	/**
	 * Get the the origin path that this resolver was created with.
	 * 
	 * @return the parent path of this resolver
	 */
	public abstract String getPath();

	/**
	 * Return a list of subfiles matching the given regular expression.
	 * 
	 * @param regexMatch
	 *            the regular expression to use.
	 * @return the list of paths that matched the regular expression
	 * @throws IOException
	 */
	public abstract List<String> getSubfiles(String regexMatch)
			throws IOException;

	/**
	 * Calls {@link #resolve(String)} and then opens the returned resource as a
	 * stream.
	 * 
	 * @param path
	 *            the path to resolve
	 * @return a stream to the path or null if it does not exist
	 * @throws IOException
	 *             if it occurs
	 */
	public final InputStream openStream(String path) throws IOException
	{
		Object resolve = this.resolve(path);

		if (resolve instanceof File)
		{
			File f = (File) resolve;

			if (f.exists())
				return new FileInputStream(f);
			else
				return null;
		}
		else if (resolve instanceof String)
		{
			return ClasspathUtils.fromClassPath((String) resolve);
		}
		else if (resolve instanceof URL)
		{
			try
			{
				return ((URL) resolve).openStream();
			}
			catch (FileNotFoundException e)
			{
				return null;
			}
		}
		else if (resolve instanceof ArchiveEntry)
		{
			return ((ArchiveEntry) resolve).open();
		}

		return null;
	}

	/**
	 * Resolve the given path. The returned object will be one of:
	 * <ol>
	 * <li>A String on the classpath</li>
	 * <li>A File</li>
	 * <li>A URL</li>
	 * <li>{@link ArchiveEntry}</li>
	 * </ol>
	 * 
	 * @param path
	 *            the path to resolve
	 * @return the object that it resolved to
	 * @throws PathResolutionException
	 *             if the path cannot be resolved
	 */
	public Object resolve(String path) throws PathResolutionException
	{
		URL url = PathUtils.toURL(path);

		if (url != null && PathUtils.isLocalURL(url))
		{
			try
			{
				return new File(url.toURI());
			}
			catch (URISyntaxException e)
			{
				return url;
			}
		}

		return url;
	}
}