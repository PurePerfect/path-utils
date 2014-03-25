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
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Represents an entry in an archive.
 * 
 * @author J. Chris Folsom
 * @version 0.2
 * @since 0.2
 */
public class ArchiveEntry
{
	private byte[] zipData;

	private String path;

	/**
	 * Create a new archive entry.
	 * 
	 * @param path
	 *            the path of the entry
	 * 
	 * @param zipData
	 *            the bytes of the archive (i.e. not the entry in the archive).
	 */
	public ArchiveEntry(String path, byte[] zipData)
	{
		super();
		this.zipData = zipData;
		this.path = path;
	}

	/**
	 * The archive bytes.
	 * 
	 * @return The archive bytes.
	 */
	public byte[] getZipData()
	{
		return zipData;
	}

	/**
	 * The path of the entry.
	 * 
	 * @return The path of the entry.
	 */
	public String getPath()
	{
		return path;
	}

	/**
	 * Open a stream to the archive entry.
	 * 
	 * @return an InputStream
	 * @throws PathResolutionException
	 *             if the entry does not exist in the archive.
	 */
	public InputStream open() throws PathResolutionException
	{
		ZipInputStream zipIn = new ZipInputStream(new ByteArrayInputStream(
				this.zipData));

		try
		{
			for (ZipEntry current = zipIn.getNextEntry(); current != null; current = zipIn
					.getNextEntry())
			{
				if (Pattern.matches(this.path, current.getName()))
				{
					return zipIn;
				}
			}

			throw new PathResolutionException("Unable to open entry: "
					+ this.path);
		}
		catch (IOException e)
		{
			throw new PathResolutionException(e);
		}
	}
}