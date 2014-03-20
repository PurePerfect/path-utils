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
import java.util.LinkedList;
import java.util.List;

class FileResolver extends PathResolver
{
	private File parent;

	FileResolver(File parent)
	{
		this.parent = parent;
	}

	@Override
	public String getPath()
	{
		return this.parent.toString();
	}

	@Override
	public List<String> getSubfiles(String regexMatch)
	{
		File tempParent = parent.isDirectory() ? parent : new File(
				parent.getParent());

		if (tempParent == null)
			tempParent = parent;

		File[] subFiles = tempParent.listFiles(new RegexFileFilter(regexMatch));

		List<String> results = new LinkedList<String>();

		if (subFiles != null)
			for (File f : subFiles)
			{
				results.add(f.getName());
			}

		return results;
	}

	@Override
	public Object resolve(String path)
	{
		Object defaultVal = super.resolve(path);

		if (defaultVal != null)
			return defaultVal;

		File f = parent.isDirectory() ? new File(parent, path) : new File(
				parent.getParent(), path);

		if (f.exists())
			return f;

		throw new PathResolutionException(path);
	}
}
