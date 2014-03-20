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

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

/**
 * Implemented tests here share common behavior. The abstract methods are
 * markers to make sure that all implementations of PathResolver test that
 * functionality.
 * 
 * @author J. Chris Folsom
 * @since 0.1
 * @version 0.1
 */
public abstract class Common_Base
{
	protected Class<? extends PathResolver> expectedResolverType;

	protected Class<? extends Object> expectedResultType;

	protected PathResolver underTest;

	public abstract void getPath() throws Exception;

	public void getSubFilesEmpty() throws IOException
	{
		List<String> results = underTest.getSubfiles("file[0-9]+.txt");

		assertEquals(expectedResolverType, underTest.getClass());

		assertEquals(0, results.size());
	}

	public void getSubFilesFileResolvesToParentDir() throws Exception
	{
		List<String> results = underTest.getSubfiles("file[0-9]+.txt");

		assertEquals(2, results.size());

		assertEquals("file1.txt", results.get(0));

		assertEquals("file2.txt", results.get(1));
	}

	public void getSubFilesMultiple() throws Exception
	{
		List<String> results = underTest.getSubfiles("file[0-9]+.txt");

		assertEquals(2, results.size());

		assertEquals("file1.txt", results.get(0));

		assertEquals("file2.txt", results.get(1));
	}

	public void getSubFilesSingle() throws Exception
	{
		assertEquals(expectedResolverType, underTest.getClass());

		List<String> results = underTest.getSubfiles("file[0-9]+.txt");

		assertEquals(1, results.size());

		assertEquals("file4.txt", results.get(0));
	}

	public void openStreamDownDir() throws Exception
	{
		InputStream i = underTest.openStream("child/file4.txt");

		BufferedReader in = new BufferedReader(new InputStreamReader(i));

		assertEquals("This is down a directory.", in.readLine());
		assertNull(in.readLine());
	}

	public void openStreamFileInSameDir() throws Exception
	{
		InputStream i = underTest.openStream("file1.txt");

		BufferedReader in = new BufferedReader(new InputStreamReader(i));

		assertEquals("This is file1", in.readLine());
		assertNull(in.readLine());
	}

	public void openStreamInDir() throws Exception
	{
		InputStream i = underTest.openStream("file1.txt");

		BufferedReader in = new BufferedReader(new InputStreamReader(i));

		assertEquals("This is file1", in.readLine());
		assertNull(in.readLine());
	}

	public void openStreamMissing() throws Exception
	{
		try
		{
			underTest.openStream("missing.txt");
			fail();
		}
		catch (PathResolutionException e)
		{
			// should have failed!
		}
	}

	public void openStreamUpDir() throws Exception
	{
		InputStream i = underTest.openStream("../file3.txt");

		BufferedReader in = new BufferedReader(new InputStreamReader(i));

		assertEquals("This is up a directory.", in.readLine());
		assertNull(in.readLine());
	}

	public void resolveDownDir() throws Exception
	{
		Object resolved = underTest.resolve("child/file4.txt");

		assertEquals(expectedResolverType, underTest.getClass());
		assertEquals(expectedResultType, resolved.getClass());
	}

	public void resolveFileInSameDir() throws Exception
	{
		Object resolved = underTest.resolve("file1.txt");

		assertEquals(expectedResolverType, underTest.getClass());
		assertEquals(expectedResultType, resolved.getClass());
	}

	public void resolveFileURLResolvesToFile() throws Exception
	{
		assertEquals(expectedResolverType, underTest.getClass());
		assertTrue(underTest.resolve("file:///file2.txt") instanceof File);
	}

	public void resolveInDir() throws Exception
	{
		Object resolved = underTest.resolve("file1.txt");

		assertEquals(expectedResolverType, underTest.getClass());
		assertEquals(expectedResultType, resolved.getClass());
	}

	public void resolveMissing() throws Exception
	{
		try
		{
			underTest.resolve("missing.txt");
			fail();
		}
		catch (PathResolutionException e)
		{
			// should have failed!
		}
	}

	public void resolveNonfileURLResolvesToURL() throws Exception
	{
		assertEquals(expectedResolverType, underTest.getClass());
		assertTrue(underTest.resolve("http://www.google.com") instanceof URL);
	}

	public void resolveUpDir() throws Exception
	{
		Object resolved = underTest.resolve("../file3.txt");

		assertEquals(expectedResolverType, underTest.getClass());
		assertEquals(expectedResultType, resolved.getClass());
	}
}