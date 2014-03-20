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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ArchiveEntry
{
	private byte[] zipData;

	private String path;

	public ArchiveEntry(String path, byte[] zipData)
	{
		super();
		this.zipData = zipData;
		this.path = path;
	}

	public byte[] getZipData()
	{
		return zipData;
	}

	public void setZipData(byte[] zipData)
	{
		this.zipData = zipData;
	}

	public String getPath()
	{
		return path;
	}

	public void setPath(String path)
	{
		this.path = path;
	}

	public InputStream open()
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