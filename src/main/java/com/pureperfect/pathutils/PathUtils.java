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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class PathUtils
{
	public static byte[] toBytes(InputStream in) throws IOException
	{
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		for (int c = in.read(); c != -1; c = in.read())
		{
			out.write(c);
		}

		return out.toByteArray();
	}

	public static boolean isArchive(String name)
	{
		return name.endsWith(".zip") || name.endsWith(".jar");
	}

	public static boolean isLocalURL(URL url)
	{
		return url.toString().startsWith("file:");
	}

	public static String osFriendly(String path)
	{
		return path.replace("/", File.separator);
	}

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
