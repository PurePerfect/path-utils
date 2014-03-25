/*
 * Copyright [2013] PurePerfect.com Licensed under the Apache License, Version
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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Some random utility methods for working with paths.
 * 
 * @author J. Chris Folsom
 * @version 0.2
 * @since 0.2
 */
public class PathUtils
{
	private PathUtils()
	{
		// static methods only
	}

	/**
	 * Read the bytes from the stream.
	 * 
	 * @param in
	 *            the stream to read
	 * @return the bytes in the stream
	 * @throws IOException
	 */
	public static byte[] toBytes(InputStream in) throws IOException
	{
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		for (int c = in.read(); c != -1; c = in.read())
		{
			out.write(c);
		}

		return out.toByteArray();
	}

	/**
	 * Whether or not it ends with .zip or .jar
	 * 
	 * @param name
	 *            the path name
	 * @return Whether or not it ends with .zip or .jar
	 */
	public static boolean isArchive(String name)
	{
		return name.endsWith(".zip") || name.endsWith(".jar");
	}

	/**
	 * Whether or not it's a file url.
	 * 
	 * @param url
	 *            the url to test
	 * @return Whether or not it's a file url.
	 */
	public static boolean isLocalURL(URL url)
	{
		return url.toString().startsWith("file:");
	}

	/**
	 * Converts all instances of '/' to File.separator
	 * 
	 * @param path
	 *            the path to make os friendly
	 * @return the converted path
	 */
	public static String osFriendly(String path)
	{
		return path.replace("/", File.separator);
	}

	/**
	 * Convert the path to a url.
	 * 
	 * @param path
	 *            the path to convert
	 * @return a url or null if the path is not a valid url
	 */
	public static URL toURL(String path)
	{
		try
		{
			return new URL(path);
		}
		catch (MalformedURLException e)
		{
			return null;
		}
	}
}