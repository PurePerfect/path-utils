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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

class ClasspathResolver extends PathResolver
{
	private String parentFile;

	ClasspathResolver(String parentFile)
	{
		this.parentFile = parentFile;
	}

	@Override
	public String getPath()
	{
		return this.parentFile;
	}

	@Override
	public List<String> getSubfiles(String regexMatch) throws IOException
	{
		List<String> results = new LinkedList<String>();

		String normalizePath = PathUtils.normalizeToDir(this.parentFile)
				.toString();

		InputStream in = PathUtils.open(normalizePath);

		BufferedReader r = new BufferedReader(new InputStreamReader(in));

		for (String line = r.readLine(); line != null; line = r.readLine())
		{
			if (Pattern.matches(regexMatch, line))
			{
				results.add(line);
			}
		}

		return results;
	}

	@Override
	public Object resolve(String path)
	{
		Object defaultVal = super.resolve(path);

		if (defaultVal != null)
			return defaultVal;

		StringBuilder targetPath = new StringBuilder(parentFile);

		targetPath = PathUtils.normalizeToDir(targetPath);

		targetPath.append(path);

		String result = PathUtils.processNavigation(targetPath).toString();

		if (PathUtils.open(result) != null)
			return result;

		throw new PathResolutionException(path);
	}
}
