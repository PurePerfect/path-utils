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

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

/**
 * Common tests for the {@link ClasspathResolver}
 * 
 * @author J. Chris Folsom
 * @version 0.2
 * @since 0.2
 */
public class Common_ClasspathResolverTest extends Common_Base
{
	@Test
	@Override
	public void getPath() throws Exception
	{
		assertEquals("mydir/baz/", PathResolver.createFor("mydir/baz/")
				.getPath());
	}

	@Test
	@Override
	public void getSubFilesEmpty() throws IOException
	{
		super.underTest = PathResolver.createFor("test/internal/");

		super.expectedResolverType = ClasspathResolver.class;

		super.getSubFilesEmpty();
	}

	@Test
	@Override
	public void getSubFilesFileResolvesToParentDir() throws Exception
	{
		super.underTest = PathResolver
				.createFor("test/internal/path/resolver/file/file1.txt");

		super.expectedResolverType = ClasspathResolver.class;

		super.getSubFilesFileResolvesToParentDir();
	}

	@Test
	@Override
	public void getSubFilesMultiple() throws Exception
	{
		super.underTest = PathResolver
				.createFor("test/internal/path/resolver/file/");

		super.expectedResolverType = ClasspathResolver.class;

		super.getSubFilesMultiple();
	}

	@Test
	@Override
	public void getSubFilesSingle() throws Exception
	{
		super.underTest = PathResolver
				.createFor("test/internal/path/resolver/file/child/");

		super.expectedResolverType = ClasspathResolver.class;

		super.getSubFilesSingle();
	}

	@Test
	@Override
	public void openStreamDownDir() throws Exception
	{
		super.underTest = PathResolver
				.createFor("test/internal/path/resolver/file/");

		super.expectedResolverType = ClasspathResolver.class;

		super.openStreamDownDir();
	}

	@Test
	@Override
	public void openStreamFileInSameDir() throws Exception
	{
		super.underTest = PathResolver
				.createFor("test/internal/path/resolver/file/file2.txt");

		super.expectedResolverType = ClasspathResolver.class;

		super.openStreamFileInSameDir();
	}

	@Test
	@Override
	public void openStreamInDir() throws Exception
	{
		super.underTest = PathResolver
				.createFor("test/internal/path/resolver/file/");

		super.expectedResolverType = ClasspathResolver.class;

		super.openStreamInDir();
	}

	@Test
	@Override
	public void openStreamMissing() throws Exception
	{
		super.underTest = PathResolver
				.createFor("test/internal/path/resolver/file/");

		super.expectedResolverType = ClasspathResolver.class;

		super.openStreamMissing();
	}

	@Test
	@Override
	public void openStreamUpDir() throws Exception
	{
		super.underTest = PathResolver
				.createFor("test/internal/path/resolver/file/");

		super.expectedResolverType = ClasspathResolver.class;

		super.openStreamUpDir();
	}

	@Test
	@Override
	public void resolveDownDir() throws Exception
	{
		super.underTest = PathResolver
				.createFor("test/internal/path/resolver/file/");

		super.expectedResolverType = ClasspathResolver.class;

		super.expectedResultType = String.class;

		super.resolveDownDir();
	}

	@Test
	@Override
	public void resolveFileInSameDir() throws Exception
	{
		super.underTest = PathResolver
				.createFor("test/internal/path/resolver/file/");

		super.expectedResolverType = ClasspathResolver.class;

		super.expectedResultType = String.class;

		super.resolveFileInSameDir();
	}

	@Test
	@Override
	public void resolveFileURLResolvesToFile() throws Exception
	{
		super.underTest = PathResolver.createFor("mydir/baz/");
		super.expectedResolverType = ClasspathResolver.class;

		super.resolveFileURLResolvesToFile();
	}

	@Test
	@Override
	public void resolveInDir() throws Exception
	{
		super.underTest = PathResolver
				.createFor("test/internal/path/resolver/file/");

		super.expectedResolverType = ClasspathResolver.class;

		super.expectedResultType = String.class;

		super.resolveInDir();
	}

	@Test
	@Override
	public void resolveMissing() throws Exception
	{
		super.underTest = PathResolver
				.createFor("test/internal/path/resolver/file/");

		super.expectedResolverType = ClasspathResolver.class;

		super.expectedResultType = String.class;

		super.resolveMissing();
	}

	@Test
	@Override
	public void resolveNonfileURLResolvesToURL() throws Exception
	{
		super.underTest = PathResolver.createFor("mydir/baz/");
		super.expectedResolverType = ClasspathResolver.class;

		super.resolveNonfileURLResolvesToURL();
	}

	@Test
	@Override
	public void resolveUpDir() throws Exception
	{
		super.underTest = PathResolver
				.createFor("test/internal/path/resolver/file/");

		super.expectedResolverType = ClasspathResolver.class;

		super.expectedResultType = String.class;

		super.resolveUpDir();
	}
}