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

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.*;

import com.pureperfect.pathutils.FileResolver;
import com.pureperfect.pathutils.PathResolver;
import com.pureperfect.pathutils.PathUtils;

/**
 * Common tests for the {@link FileResolver}
 * 
 * @author J. Chris Folsom
 * @version 0.2
 * @since 0.2
 */
public class Common_FileResolverTest extends Common_Base
{
	@Test
	@Override
	public void getPath() throws Exception
	{
		String path = PathUtils
				.osFriendly("src/test/resources/test/internal/path/resolver/file");

		super.underTest = PathResolver.createFor(new File(path));

		assertEquals("src/test/resources/test/internal/path/resolver/file",
				super.underTest.getPath());
	}

	@Test
	@Override
	public void getSubFilesEmpty() throws IOException
	{
		String path = PathUtils.osFriendly("src/test/resources/test/internal/");

		super.underTest = PathResolver.createFor(new File(path));
		super.expectedResolverType = FileResolver.class;

		super.getSubFilesEmpty();
	}

	@Test
	@Override
	public void getSubFilesFileResolvesToParentDir() throws Exception
	{
		String path = PathUtils
				.osFriendly("src/test/resources/test/internal/path/resolver/file/file1.txt");

		super.underTest = PathResolver.createFor(new File(path));

		super.expectedResolverType = ClasspathResolver.class;

		super.getSubFilesFileResolvesToParentDir();
	}

	@Test
	@Override
	public void getSubFilesMultiple() throws Exception
	{
		String path = PathUtils
				.osFriendly("src/test/resources/test/internal/path/resolver/file/");

		super.underTest = PathResolver.createFor(new File(path));

		super.expectedResolverType = FileResolver.class;

		super.getSubFilesMultiple();
	}

	@Test
	@Override
	public void getSubFilesSingle() throws Exception
	{
		String path = PathUtils
				.osFriendly("src/test/resources/test/internal/path/resolver/file/child/");

		super.underTest = PathResolver.createFor(new File(path));

		List<String> results = underTest.getSubfiles("file[0-9]+.txt");

		assertEquals(1, results.size());

		assertEquals("file4.txt", results.get(0));
	}

	@Test
	@Override
	public void openStreamDownDir() throws Exception
	{
		String path = PathUtils
				.osFriendly("src/test/resources/test/internal/path/resolver/file/");

		super.underTest = PathResolver.createFor(new File(path));
		super.expectedResolverType = FileResolver.class;

		super.openStreamDownDir();
	}

	@Test
	@Override
	public void openStreamFileInSameDir() throws Exception
	{
		String path = PathUtils
				.osFriendly("src/test/resources/test/internal/path/resolver/file/");

		super.underTest = PathResolver.createFor(new File(path));
		super.expectedResolverType = FileResolver.class;

		super.openStreamFileInSameDir();
	}

	@Test
	@Override
	public void openStreamInDir() throws Exception
	{
		String path = PathUtils
				.osFriendly("src/test/resources/test/internal/path/resolver/file/");

		super.underTest = PathResolver.createFor(new File(path));
		super.expectedResolverType = FileResolver.class;

		super.openStreamInDir();
	}

	@Test
	@Override
	public void openStreamMissing() throws Exception
	{
		String path = PathUtils
				.osFriendly("src/test/resources/test/internal/path/resolver/file/");

		super.underTest = PathResolver.createFor(new File(path));
		super.expectedResolverType = FileResolver.class;

		super.openStreamMissing();
	}

	@Test
	@Override
	public void openStreamUpDir() throws Exception
	{
		String path = PathUtils
				.osFriendly("src/test/resources/test/internal/path/resolver/file/");

		super.underTest = PathResolver.createFor(new File(path));
		super.expectedResolverType = FileResolver.class;

		super.openStreamUpDir();
	}

	@Test
	@Override
	public void resolveDownDir() throws Exception
	{
		String path = PathUtils
				.osFriendly("src/test/resources/test/internal/path/resolver/file/");

		super.underTest = PathResolver.createFor(new File(path));

		super.expectedResolverType = FileResolver.class;

		super.expectedResultType = File.class;

		super.resolveDownDir();
	}

	@Test
	@Override
	public void resolveFileInSameDir() throws Exception
	{
		String path = PathUtils
				.osFriendly("src/test/resources/test/internal/path/resolver/file/file1.txt");

		super.underTest = PathResolver.createFor(new File(path));

		super.expectedResolverType = FileResolver.class;

		super.expectedResultType = File.class;

		super.resolveFileInSameDir();
	}

	@Test
	@Override
	public void resolveFileURLResolvesToFile() throws Exception
	{
		String path = PathUtils
				.osFriendly("src/test/resources/test/internal/path/resolver/file");

		super.underTest = PathResolver.createFor(new File(path));
		super.expectedResolverType = FileResolver.class;

		super.resolveFileURLResolvesToFile();
	}

	@Test
	@Override
	public void resolveInDir() throws Exception
	{
		String path = PathUtils
				.osFriendly("src/test/resources/test/internal/path/resolver/file/");

		super.underTest = PathResolver.createFor(new File(path));

		super.expectedResolverType = FileResolver.class;

		super.expectedResultType = File.class;

		super.resolveInDir();
	}

	@Test
	@Override
	public void resolveMissing() throws Exception
	{
		String path = PathUtils.osFriendly("test/internal/path/resolver/file/");

		super.underTest = PathResolver.createFor(new File(path));

		super.expectedResolverType = FileResolver.class;

		super.expectedResultType = File.class;

		super.resolveMissing();
	}

	@Test
	@Override
	public void resolveNonfileURLResolvesToURL() throws Exception
	{
		String path = PathUtils
				.osFriendly("src/test/resources/test/internal/path/resolver/file");

		super.underTest = PathResolver.createFor(new File(path));
		super.expectedResolverType = FileResolver.class;

		super.resolveNonfileURLResolvesToURL();
	}

	@Test
	@Override
	public void resolveUpDir() throws Exception
	{
		String path = PathUtils
				.osFriendly("src/test/resources/test/internal/path/resolver/file/");

		super.underTest = PathResolver.createFor(new File(path));

		super.expectedResolverType = FileResolver.class;

		super.expectedResultType = File.class;

		super.resolveUpDir();
	}
}