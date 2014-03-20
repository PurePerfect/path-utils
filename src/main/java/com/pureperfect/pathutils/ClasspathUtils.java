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

import java.io.InputStream;

public class ClasspathUtils
{
	public static InputStream fromClassPath(String classpath)
	{
		return Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(classpath);
	}

	public static boolean isDirectory(String path)
	{
		return path.endsWith("/");
	}

	/**
	 * If: We're at the top of the tree don't remove parent file name. Instead
	 * just put in the '/' to prefix the absolute path so that file:
	 * 
	 * 'myfile.txt' becomes '/' instead.
	 * 
	 * Else: remove trailing file so that we have the directory name:
	 * 
	 * 'myfolder/myfile.txt becomes 'myfolder/'.
	 */
	public static StringBuilder normalizeToDir(CharSequence path)
	{
		StringBuilder results = new StringBuilder(path);

		if (!isDirectory(results.toString()))
		{
			int lastDir = results.lastIndexOf("/");

			if (lastDir < 0)
			{
				results = new StringBuilder("/");
			}
			else
			{
				results.replace(lastDir, path.length(), "/");
			}
		}

		return results;
	}

	public static StringBuilder processNavigation(CharSequence path)
	{
		return ClasspathUtils
				.removeLeadingSlash(processUpDirectories(removeSameDirReferences(path)));
	}

	public static StringBuilder processUpDirectories(CharSequence path)
	{
		StringBuilder results = new StringBuilder(path);

		/*
		 * Need to resolve ../ in path by eliminating previous directory from
		 * string.
		 */
		for (int upDirIndex = results.indexOf("../"); upDirIndex >= 0; upDirIndex = results
				.indexOf("../"))
		{
			// First trim off the ../ we found
			results.replace(upDirIndex, upDirIndex + 3, "");

			if (upDirIndex > 2)
			{
				/*
				 * Remove following '/' on the previous directory. IE:
				 * 
				 * "foo/" will become "foo"
				 */
				results.replace(upDirIndex - 1, upDirIndex, "");

				/*
				 * Now go back to the previous slash and remove the previous
				 * directory
				 */
				for (int backCount = upDirIndex - 2;; backCount--)
				{
					if (backCount == 0)
					{
						/*
						 * We got to the top of the path without hitting a
						 * slash. That means the path was created without a
						 * leading slash. IE:
						 * 
						 * my/path/to/file
						 */
						results.replace(0, upDirIndex - 1, "");
						break;
					}
					else if (results.charAt(backCount) == '/')
					{
						results.replace(backCount + 1, upDirIndex - 1, "");
						break;
					}
				}
			}
		}

		return results;
	}

	public static StringBuilder removeLeadingSlash(CharSequence path)
	{
		StringBuilder results = new StringBuilder(path);

		if (results.charAt(0) == '/')
			results.replace(0, 1, "");

		return results;
	}

	public static StringBuilder removeSameDirReferences(CharSequence path)
	{
		StringBuilder results = new StringBuilder(path);

		for (int i = 0; i < results.length() - 1; ++i)
		{
			if (results.charAt(i) == '.' && results.charAt(i + 1) == '/')
			{
				if (i == 0 || results.charAt(i - 1) != '.')
				{
					results.replace(i, i + 2, "");

					// Backup place to account for what we removed
					i = i - 1;
				}
			}
		}

		return results;
	}

	public static String trimToFile(String path)
	{
		int last = path.lastIndexOf('/');

		if (!path.endsWith("/") && last > -1 && last < path.length())
			return path.substring(last + 1);

		return path;
	}
}