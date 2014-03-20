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

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.junit.Test;

import com.pureperfect.pathutils.PathResolver;
import com.pureperfect.pathutils.PathUtils;
import com.pureperfect.pathutils.URLResolver;

public class Common_URLResolverTest extends Common_Base
{
	@Test
	@Override
	public void getPath() throws Exception
	{
		PathResolver different = PathResolver.createFor(new URL(
				"http://localhost/test/internal/path/resolver/file/child"));

		assertEquals("http://localhost/test/internal/path/resolver/file/child",
				different.getPath());
	}

	@Test
	@Override
	public void getSubFilesEmpty() throws IOException
	{
		super.underTest = PathResolver.createFor(new URL(
				"http://localhost/test/internal/"));

		super.expectedResolverType = URLResolver.class;

		super.getSubFilesEmpty();
	}

	@Test
	@Override
	public void getSubFilesFileResolvesToParentDir() throws IOException
	{
		/*
		 * Get sub files will always return zero results since url cannot list
		 * sub files.
		 */
		PathResolver different = PathResolver.createFor(new URL(
				"http://localhost/test/internal/path/resolver/file/child"));

		assertEquals(0, different.getSubfiles("file[0-9]+.txt").size());
	}

	@Test
	@Override
	public void getSubFilesMultiple() throws IOException
	{
		/*
		 * Get sub files will always return zero results since url cannot list
		 * sub files.
		 */
		PathResolver different = PathResolver.createFor(new URL(
				"http://localhost/test/internal/path/resolver/file/"));

		assertEquals(0, different.getSubfiles("file[0-9]+.txt").size());
	}

	@Test
	@Override
	public void getSubFilesSingle() throws Exception
	{
		/*
		 * Get sub files will always return zero results since url cannot list
		 * sub files.
		 */
		PathResolver different = PathResolver.createFor(new URL(
				"http://localhost/test/internal/path/resolver/file/child/"));

		assertEquals(0, different.getSubfiles("file[0-9]+.txt").size());
	}

	@Test
	@Override
	public void openStreamDownDir() throws Exception
	{
		super.underTest = PathResolver.createFor(new URL(
				"http://localhost/test/internal/path/resolver/file/"));

		super.expectedResolverType = URLResolver.class;

		super.openStreamDownDir();
	}

	@Test
	@Override
	public void openStreamFileInSameDir() throws Exception
	{
		super.underTest = PathResolver.createFor(new URL(
				"http://localhost/test/internal/path/resolver/file/"));

		super.expectedResolverType = URLResolver.class;

		super.openStreamFileInSameDir();
	}

	@Test
	@Override
	public void openStreamInDir() throws Exception
	{
		super.underTest = PathResolver.createFor(new URL(
				"http://localhost/test/internal/path/resolver/file/"));

		super.expectedResolverType = URLResolver.class;

		super.openStreamInDir();
	}

	@Test
	@Override
	public void openStreamMissing() throws Exception
	{
		super.underTest = PathResolver.createFor(new URL(
				"http://localhost/test/internal/path/resolver/file/"));

		super.expectedResolverType = URLResolver.class;

		super.openStreamMissing();
	}

	@Test
	@Override
	public void openStreamUpDir() throws Exception
	{
		super.underTest = PathResolver.createFor(new URL(
				"http://localhost/test/internal/path/resolver/file/"));

		super.expectedResolverType = URLResolver.class;

		super.openStreamUpDir();
	}

	@Test
	@Override
	public void resolveDownDir() throws Exception
	{
		super.underTest = PathResolver.createFor(new URL(
				"http://localhost/test/internal/path/resolver/file/"));

		super.expectedResolverType = URLResolver.class;

		super.expectedResultType = URL.class;

		super.resolveDownDir();
	}

	@Test
	@Override
	public void resolveFileInSameDir() throws Exception
	{
		super.underTest = PathResolver.createFor(new URL(
				"http://localhost/test/internal/path/resolver/file/file1.txt"));

		super.expectedResolverType = URLResolver.class;

		super.expectedResultType = URL.class;

		super.resolveFileInSameDir();
	}

	@Test
	@Override
	public void resolveFileURLResolvesToFile() throws Exception
	{
		String path = PathUtils.osFriendly("blah/blah");

		super.underTest = new URLResolver(new File(path).toURI().toURL());
		super.expectedResolverType = URLResolver.class;

		super.resolveFileURLResolvesToFile();
	}

	@Test
	@Override
	public void resolveInDir() throws Exception
	{
		super.underTest = PathResolver.createFor(new URL(
				"http://localhost/test/internal/path/resolver/file/"));

		super.expectedResolverType = URLResolver.class;

		super.expectedResultType = URL.class;

		super.resolveInDir();
	}

	@Test
	@Override
	public void resolveMissing() throws Exception
	{
		super.underTest = PathResolver.createFor(new URL(
				"http://test/internal/path/resolver/file/"));

		super.expectedResolverType = URLResolver.class;

		super.expectedResultType = URL.class;

		super.resolveMissing();
	}

	@Test
	@Override
	public void resolveNonfileURLResolvesToURL() throws Exception
	{
		super.underTest = PathResolver.createFor(new URL("http://localhost"));
		super.expectedResolverType = URLResolver.class;

		super.resolveNonfileURLResolvesToURL();
	}

	@Test
	@Override
	public void resolveUpDir() throws Exception
	{
		super.underTest = PathResolver.createFor(new URL(
				"http://localhost/test/internal/path/resolver/file/"));

		super.expectedResolverType = URLResolver.class;

		super.expectedResultType = URL.class;

		super.resolveUpDir();
	}
}