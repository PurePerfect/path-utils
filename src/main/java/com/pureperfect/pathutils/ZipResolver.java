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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

class ZipResolver extends PathResolver
{
	private String parent;

	private byte[] zipFile;

	ZipResolver(String entry, InputStream in)
	{
		this.parent = entry;

		try
		{
			this.zipFile = PathUtils.toBytes(in);
		}
		catch (IOException e)
		{
			throw new PathResolutionException(e);
		}
	}

	ZipResolver(String entry, byte[] zipFile)
	{
		this.parent = entry;
		this.zipFile = zipFile;
	}

	ZipResolver(ArchiveEntry entry)
	{
		this.parent = entry.getPath();
		this.zipFile = entry.getZipData();
	}

	@Override
	public String getPath()
	{
		return parent;
	}

	@Override
	public List<String> getSubfiles(String regexMatch) throws IOException
	{
		StringBuilder temp = new StringBuilder(parent);

		temp = PathUtils.normalizeToDir(temp);

		temp.append(regexMatch);

		String targetPath = PathUtils.processNavigation(temp).toString();

		ZipInputStream zipIn = new ZipInputStream(new ByteArrayInputStream(
				this.zipFile));

		try
		{
			List<String> results = new LinkedList<String>();

			for (ZipEntry current = zipIn.getNextEntry(); current != null; current = zipIn
					.getNextEntry())
			{
				if (Pattern.matches(targetPath, current.getName()))
				{
					results.add(PathUtils.trimToFile(current.getName()));
				}
			}

			return results;
		}
		catch (IOException e)
		{
			throw new PathResolutionException(e);
		}
	}

	@Override
	public Object resolve(String path)
	{
		Object defaultVal = super.resolve(path);

		if (defaultVal != null)
			return defaultVal;

		StringBuilder temp = new StringBuilder(parent);

		temp = PathUtils.normalizeToDir(temp);

		temp.append(path);

		String targetPath = PathUtils.processNavigation(temp).toString();

		ZipInputStream zipIn = new ZipInputStream(new ByteArrayInputStream(
				this.zipFile));

		try
		{
			for (ZipEntry current = zipIn.getNextEntry(); current != null; current = zipIn
					.getNextEntry())
			{
				if (Pattern.matches(targetPath, current.getName()))
				{
					return new ArchiveEntry(current.getName(), this.zipFile);
				}
			}
		}
		catch (IOException e)
		{
			throw new PathResolutionException(e);
		}

		throw new PathResolutionException(path);
	}
}