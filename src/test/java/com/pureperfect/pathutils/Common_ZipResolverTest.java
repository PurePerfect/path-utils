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

import org.junit.Test;

public class Common_ZipResolverTest extends Common_Base
{
	@Test
	@Override
	public void getPath() throws Exception
	{
		PathResolver resolver = new ZipResolver(
				"test/internal/path/resolver/file/child",
				ClasspathUtils.fromClassPath("test1.jar"));

		assertEquals("test/internal/path/resolver/file/child",
				resolver.getPath());
	}

	@Test
	@Override
	public void getSubFilesEmpty() throws IOException
	{
		this.underTest = new ZipResolver("test/internal/",
				ClasspathUtils.fromClassPath("test1.jar"));

		this.expectedResolverType = ZipResolver.class;

		super.getSubFilesEmpty();
	}

	@Test
	@Override
	public void getSubFilesFileResolvesToParentDir() throws Exception
	{
		super.underTest = new ZipResolver(
				"test/internal/path/resolver/file/child",
				ClasspathUtils.fromClassPath("test1.jar"));

		super.expectedResolverType = ZipResolver.class;

		super.getSubFilesFileResolvesToParentDir();
	}

	@Test
	@Override
	public void getSubFilesMultiple() throws Exception
	{
		super.underTest = new ZipResolver("test/internal/path/resolver/file/",
				ClasspathUtils.fromClassPath("test1.jar"));

		super.expectedResolverType = ZipResolver.class;

		super.getSubFilesMultiple();
	}

	@Test
	@Override
	public void getSubFilesSingle() throws Exception
	{
		super.underTest = new ZipResolver(
				"test/internal/path/resolver/file/child/",
				ClasspathUtils.fromClassPath("test1.jar"));

		super.expectedResolverType = ZipResolver.class;

		super.getSubFilesSingle();
	}

	@Test
	@Override
	public void openStreamDownDir() throws Exception
	{
		super.underTest = new ZipResolver("test/internal/path/resolver/file/",
				ClasspathUtils.fromClassPath("test1.jar"));

		super.expectedResolverType = ZipResolver.class;
		super.openStreamDownDir();
	}

	@Test
	@Override
	public void openStreamFileInSameDir() throws Exception
	{
		super.underTest = new ZipResolver(
				"test/internal/path/resolver/file/file1.txt",
				ClasspathUtils.fromClassPath("test1.jar"));

		super.expectedResolverType = ZipResolver.class;

		super.openStreamFileInSameDir();
	}

	@Test
	@Override
	public void openStreamInDir() throws Exception
	{
		super.underTest = new ZipResolver("test/internal/path/resolver/file/",
				ClasspathUtils.fromClassPath("test1.jar"));

		super.expectedResolverType = ZipResolver.class;

		super.openStreamInDir();
	}

	@Test
	@Override
	public void openStreamMissing() throws Exception
	{
		super.underTest = new ZipResolver("test/internal/path/resolver/file/",
				ClasspathUtils.fromClassPath("test1.jar"));

		super.expectedResolverType = ZipResolver.class;
		super.openStreamMissing();
	}

	@Test
	@Override
	public void openStreamUpDir() throws Exception
	{
		super.underTest = new ZipResolver("test/internal/path/resolver/file/",
				ClasspathUtils.fromClassPath("test1.jar"));

		super.expectedResolverType = ZipResolver.class;
		super.openStreamUpDir();
	}

	@Test
	@Override
	public void resolveDownDir() throws Exception
	{
		super.underTest = new ZipResolver("test/internal/path/resolver/file/",
				ClasspathUtils.fromClassPath("test1.jar"));

		super.expectedResolverType = ZipResolver.class;
		super.expectedResultType = ArchiveEntry.class;

		super.resolveDownDir();
	}

	@Test
	@Override
	public void resolveFileInSameDir() throws Exception
	{
		super.underTest = new ZipResolver("test/internal/path/resolver/file/",
				ClasspathUtils.fromClassPath("test1.jar"));

		super.expectedResolverType = ZipResolver.class;
		super.expectedResultType = ArchiveEntry.class;

		super.resolveFileInSameDir();
	}

	@Test
	@Override
	public void resolveFileURLResolvesToFile() throws Exception
	{
		String path = PathUtils.osFriendly("src/test/resources/test1.jar");

		super.underTest = PathResolver
				.createFor(new File(path).toURI().toURL());
		super.expectedResolverType = ZipResolver.class;

		super.resolveFileURLResolvesToFile();
	}

	@Test
	@Override
	public void resolveInDir() throws Exception
	{
		super.underTest = new ZipResolver("test/internal/path/resolver/file/",
				ClasspathUtils.fromClassPath("test1.jar"));

		super.expectedResolverType = ZipResolver.class;
		super.expectedResultType = ArchiveEntry.class;

		super.resolveInDir();
	}

	@Test
	@Override
	public void resolveMissing() throws Exception
	{
		super.underTest = new ZipResolver("test/internal/path/resolver/file/",
				ClasspathUtils.fromClassPath("test1.jar"));

		super.expectedResolverType = ZipResolver.class;

		super.resolveMissing();
	}

	@Test
	@Override
	public void resolveNonfileURLResolvesToURL() throws Exception
	{
		String path = PathUtils.osFriendly("src/test/resources/test1.jar");

		super.underTest = PathResolver.createFor(new File(path));
		super.expectedResolverType = ZipResolver.class;

		super.resolveNonfileURLResolvesToURL();
	}

	@Test
	@Override
	public void resolveUpDir() throws Exception
	{
		super.underTest = new ZipResolver("test/internal/path/resolver/file/",
				ClasspathUtils.fromClassPath("test1.jar"));

		super.expectedResolverType = ZipResolver.class;
		super.expectedResultType = ArchiveEntry.class;

		super.resolveUpDir();
	}
}