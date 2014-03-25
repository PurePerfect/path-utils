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

import java.net.URL;
import java.util.LinkedList;
import java.util.List;

class URLResolver extends PathResolver
{
	private URL parent;

	URLResolver(URL parent)
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
		// Can't list directory for url
		return new LinkedList<String>();
	}

	@Override
	public Object resolve(String path)
	{
		Object defaultVal = super.resolve(path);

		if (defaultVal != null)
		{
			return defaultVal;
		}

		try
		{
			URL url = new URL(this.parent, path);

			url.openStream();

			return url;
		}
		catch (Throwable e)
		{
			throw new PathResolutionException(e);
		}
	}
}