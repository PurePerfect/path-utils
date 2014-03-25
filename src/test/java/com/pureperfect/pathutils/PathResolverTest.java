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

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;

/**
 * @author J. Chris Folsom
 * @version 0.2
 * @since 0.2
 */
public class PathResolverTest
{
	@Test
	public void testCreateForFile()
	{
		String path = PathUtils.osFriendly("src/test/resources/");

		assertTrue(PathResolver.createFor(new File(path)) instanceof FileResolver);
	}

	@Test
	public void testCreateForFileJar()
	{
		String path = PathUtils.osFriendly("src/test/resources/test1.jar");

		assertTrue(PathResolver.createFor(new File(path)) instanceof ZipResolver);
	}

	@Test
	public void testCreateForFileZip()
	{
		String path = PathUtils.osFriendly("src/test/resources/test1.zip");

		assertTrue(PathResolver.createFor(new File(path)) instanceof ZipResolver);
	}

	@Test
	public void testCreateForString()
	{
		assertTrue(PathResolver.createFor("classPath") instanceof ClasspathResolver);
	}

	@Test
	public void testCreateForURL() throws MalformedURLException
	{
		assertTrue(PathResolver.createFor(new URL("http://localhost/")) instanceof URLResolver);
	}

	@Test
	public void testCreateForURLFile() throws MalformedURLException
	{
		String path = PathUtils.osFriendly("src/test/resources/");

		assertTrue(PathResolver.createFor(new File(path).toURI().toURL()) instanceof FileResolver);
	}

	@Test
	public void testCreateForURLJar() throws MalformedURLException
	{
		String path = PathUtils.osFriendly("src/test/resources/test1.jar");

		assertTrue(PathResolver.createFor(new File(path).toURI().toURL()) instanceof ZipResolver);
	}

	@Test
	public void testCreateForURLZip() throws MalformedURLException
	{
		String path = PathUtils.osFriendly("src/test/resources/test1.zip");

		assertTrue(PathResolver.createFor(new File(path).toURI().toURL()) instanceof ZipResolver);
	}
}
