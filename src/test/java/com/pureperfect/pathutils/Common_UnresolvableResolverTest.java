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

import java.io.IOException;

import org.junit.Test;

import static org.junit.Assert.*;

import com.pureperfect.pathutils.UnresolvableResolver;

/**
 * Common tests for the {@link UnresolvableResolver}.
 * 
 * @author J. Chris Folsom
 * @version 0.2
 * @since 0.2
 */
public class Common_UnresolvableResolverTest extends Common_Base
{
	@Test
	@Override
	public void getPath() throws Exception
	{
		super.underTest = PathResolver.createNonResolvable("sadf");

		assertEquals("sadf", super.underTest.getPath());
	}

	@Test
	@Override
	public void getSubFilesEmpty() throws IOException
	{
		super.underTest = PathResolver.createNonResolvable("sadf");
		super.expectedResolverType = UnresolvableResolver.class;

		super.getSubFilesEmpty();
	}

	@Test
	@Override
	public void getSubFilesFileResolvesToParentDir() throws Exception
	{
		/*
		 * This should always return empty irrespective of actual path. Relative
		 * paths are not resolvable.
		 */
		super.underTest = PathResolver
				.createNonResolvable("test/internal/path/resolver/file/file1.txt");
		super.expectedResolverType = UnresolvableResolver.class;

		super.getSubFilesEmpty();
	}

	@Test
	@Override
	public void getSubFilesMultiple() throws Exception
	{
		/*
		 * This should always return empty irrespective of actual path. Relative
		 * paths are not resolvable.
		 */
		super.underTest = PathResolver
				.createNonResolvable("test/internal/path/resolver/file/");
		super.expectedResolverType = UnresolvableResolver.class;

		super.getSubFilesEmpty();
	}

	@Test
	@Override
	public void getSubFilesSingle() throws Exception
	{
		/*
		 * This should always return empty irrespective of actual path. Relative
		 * paths are not resolvable.
		 */
		super.underTest = PathResolver
				.createNonResolvable("test/internal/path/resolver/file/child/");
		super.expectedResolverType = UnresolvableResolver.class;

		super.getSubFilesEmpty();
	}

	@Test
	@Override
	public void openStreamDownDir() throws Exception
	{
		super.underTest = PathResolver.createNonResolvable("sadf");
		super.expectedResolverType = UnresolvableResolver.class;

		try
		{
			super.openStreamDownDir();
			fail();
		}
		catch (PathResolutionException e)
		{
			assertEquals("child/file4.txt", e.getMessage());
		}
	}

	@Test
	@Override
	public void openStreamFileInSameDir() throws Exception
	{
		super.underTest = PathResolver.createNonResolvable("sadf");
		super.expectedResolverType = UnresolvableResolver.class;

		try
		{
			super.openStreamFileInSameDir();
			fail();
		}
		catch (PathResolutionException e)
		{
			assertEquals("file1.txt", e.getMessage());
		}
	}

	@Test
	@Override
	public void openStreamInDir() throws Exception
	{
		super.underTest = PathResolver.createNonResolvable("sadf");
		super.expectedResolverType = UnresolvableResolver.class;

		try
		{
			super.openStreamInDir();
			fail();
		}
		catch (PathResolutionException e)
		{
			assertEquals("file1.txt", e.getMessage());
		}
	}

	@Test
	@Override
	public void openStreamMissing() throws Exception
	{
		super.underTest = PathResolver.createNonResolvable("sadf");
		super.expectedResolverType = UnresolvableResolver.class;

		super.openStreamMissing();
	}

	@Test
	@Override
	public void openStreamUpDir() throws Exception
	{
		super.underTest = PathResolver.createNonResolvable("sadf");
		super.expectedResolverType = UnresolvableResolver.class;

		try
		{
			super.openStreamUpDir();
			fail();
		}
		catch (PathResolutionException e)
		{
			assertEquals("../file3.txt", e.getMessage());
		}
	}

	@Test
	@Override
	public void resolveDownDir() throws Exception
	{
		super.underTest = PathResolver.createNonResolvable("sadf");
		super.expectedResolverType = UnresolvableResolver.class;

		try
		{
			super.resolveDownDir();
			fail();
		}
		catch (PathResolutionException e)
		{
			assertEquals("child/file4.txt", e.getMessage());
		}
	}

	@Test
	@Override
	public void resolveFileInSameDir() throws Exception
	{
		super.underTest = PathResolver.createNonResolvable("sadf");
		super.expectedResolverType = UnresolvableResolver.class;

		try
		{
			super.resolveFileInSameDir();
			fail();
		}
		catch (PathResolutionException e)
		{
			assertEquals("file1.txt", e.getMessage());
		}
	}

	@Test
	@Override
	public void resolveFileURLResolvesToFile() throws Exception
	{
		super.underTest = PathResolver.createNonResolvable("sadf");
		super.expectedResolverType = UnresolvableResolver.class;

		super.resolveFileURLResolvesToFile();
	}

	@Test
	@Override
	public void resolveInDir() throws Exception
	{
		super.underTest = PathResolver.createNonResolvable("sadf");
		super.expectedResolverType = UnresolvableResolver.class;

		try
		{
			super.resolveInDir();
			fail();
		}
		catch (PathResolutionException e)
		{
			assertEquals("file1.txt", e.getMessage());
		}
	}

	@Test
	@Override
	public void resolveMissing() throws Exception
	{
		super.underTest = PathResolver.createNonResolvable("sadf");
		super.expectedResolverType = UnresolvableResolver.class;

		super.resolveMissing();
	}

	@Test
	@Override
	public void resolveNonfileURLResolvesToURL() throws Exception
	{
		super.underTest = PathResolver.createNonResolvable("sadf");
		super.expectedResolverType = UnresolvableResolver.class;

		super.resolveNonfileURLResolvesToURL();
	}

	@Test
	@Override
	public void resolveUpDir() throws Exception
	{
		super.underTest = PathResolver.createNonResolvable("sadf");
		super.expectedResolverType = UnresolvableResolver.class;

		try
		{
			super.resolveUpDir();
			fail();
		}
		catch (PathResolutionException e)
		{
			assertEquals("../file3.txt", e.getMessage());
		}
	}
}