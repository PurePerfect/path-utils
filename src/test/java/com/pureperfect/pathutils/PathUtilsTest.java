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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;

/**
 * 
 * @author J. Chris Folsom
 * @version 0.3
 * @since 0.2
 */
public class PathUtilsTest
{
	@Test
	public void isFileURL() throws MalformedURLException
	{
		assertTrue(PathUtils.isLocalURL(new File("notreal").toURI().toURL()));
		assertFalse(PathUtils.isLocalURL(new URL("http://google.com")));
	}

	@Test
	public void osFriendly()
	{
		assertEquals("asdf" + File.separator + "foo",
				PathUtils.osFriendly("asdf/foo"));
	}

	@Test
	public void toUrl()
	{
		assertNull(PathUtils.toURL("asdf/sdfs"));
		assertNotNull(PathUtils.toURL("http://www.google.com"));
	}
	
	@Test
	public void fromClassPath()
	{
		assertNotNull(PathUtils
				.open("test/internal/path/resolver/file3.txt"));
		assertNull(PathUtils
				.open("/test/internal/path/resolver/file3.txt"));
	}

	@Test
	public void isDirectory()
	{
		assertTrue(PathUtils.isClasspathDirectory("/"));
		assertTrue(PathUtils.isClasspathDirectory("foo/bar/"));
		assertTrue(PathUtils.isClasspathDirectory("/foo/"));

		assertFalse(PathUtils.isClasspathDirectory("foo"));
		assertFalse(PathUtils.isClasspathDirectory("/foo"));
		assertFalse(PathUtils.isClasspathDirectory("/foo/bar"));
	}

	@Test
	public void normalizeToClasspathDirectory()
	{
		assertEquals(new StringBuilder("/").toString(), PathUtils
				.normalizeToDir("/").toString());

		assertEquals(new StringBuilder("foo/bar/").toString(), PathUtils
				.normalizeToDir("foo/bar/").toString());

		assertEquals(new StringBuilder("/foo/").toString(), PathUtils
				.normalizeToDir("/foo/").toString());

		assertEquals(new StringBuilder("/").toString(), PathUtils
				.normalizeToDir("foo").toString());

		assertEquals(new StringBuilder("/").toString(), PathUtils
				.normalizeToDir("/foo").toString());

		assertEquals(new StringBuilder("/foo/").toString(), PathUtils
				.normalizeToDir("/foo/bar").toString());
	}

	@Test
	public void processNavigation()
	{
		assertEquals("foo/", PathUtils
				.processNavigation("/foo/bar/../././").toString());

		assertEquals("foo/", PathUtils
				.processNavigation("foo/bar/../././").toString());
		
		assertEquals("foo/bar/", PathUtils
				.processNavigation("/foo/bar/././").toString());

		assertEquals("foo/bar/", PathUtils
				.processNavigation("foo/bar/././").toString());
	}

	@Test
	public void processUpDirUpDir()
	{
		assertEquals(new StringBuilder("myfile/myfile.txt").toString(),
				PathUtils.processUpDirectories("myfile/foo/../myfile.txt")
						.toString());
	}

	@Test
	public void processUpDirUpDirCannotPassTop()
	{
		assertEquals(new StringBuilder("myfile.txt").toString(), PathUtils
				.processUpDirectories("myfile/../../myfile.txt").toString());
	}

	@Test
	public void processUpDirUpDirCannotPassTop2()
	{
		assertEquals(new StringBuilder("myfile.txt").toString(), PathUtils
				.processUpDirectories("myfile/../../../myfile.txt").toString());
	}

	@Test
	public void processUpDirUpDirCannotPassTop3()
	{
		assertEquals(new StringBuilder("myfile.txt").toString(), PathUtils
				.processUpDirectories("myfile/../../../../myfile.txt")
				.toString());
	}

	@Test
	public void processUpDirUpDirToTopMissingLeadingSlash()
	{
		assertEquals(new StringBuilder("myfile.txt").toString(), PathUtils
				.processUpDirectories("myfile/../myfile.txt").toString());
	}

	@Test
	public void processUpDirUpDirToTopMissingLeadingSlashCannotPassTop()
	{
		assertEquals(new StringBuilder("myfile.txt").toString(), PathUtils
				.processUpDirectories("myfile/../../myfile.txt").toString());
	}

	@Test
	public void processUpDirUpDirToTopMissingLeadingSlashCannotPassTop2()
	{
		assertEquals(new StringBuilder("myfile.txt").toString(), PathUtils
				.processUpDirectories("myfile/../../../myfile.txt").toString());
	}

	@Test
	public void processUpDirUpDirToTopMissingLeadingSlashCannotPassTop3()
	{
		assertEquals(new StringBuilder("myfile.txt").toString(), PathUtils
				.processUpDirectories("myfile/../../../../myfile.txt")
				.toString());
	}

	@Test
	public void processUpDirUpDirToTopWithLeadingSlash()
	{
		assertEquals(new StringBuilder("myfile.txt").toString(), PathUtils
				.processUpDirectories("/myfile/../myfile.txt").toString());
	}

	@Test
	public void processUpDirUpDirToTopWithLeadingSlashCannotPassTop()
	{
		assertEquals(new StringBuilder("myfile.txt").toString(), PathUtils
				.processUpDirectories("/myfile/../../myfile.txt").toString());
	}

	@Test
	public void processUpDirUpDirToTopWithLeadingSlashCannotPassTop2()
	{
		assertEquals(new StringBuilder("myfile.txt").toString(), PathUtils
				.processUpDirectories("/myfile/../../../myfile.txt").toString());
	}

	@Test
	public void processUpDirUpDirToTopWithLeadingSlashCannotPassTop3()
	{
		assertEquals(new StringBuilder("myfile.txt").toString(), PathUtils
				.processUpDirectories("/myfile/../../../../myfile.txt")
				.toString());
	}

	@Test
	public void processUpDirUpMultipleDir()
	{
		assertEquals(
				new StringBuilder("myfile/myfile.txt").toString(),
				PathUtils.processUpDirectories(
						"myfile/foo/bar/baz/../../../myfile.txt").toString());
	}

	@Test
	public void processUpDirUpMultipleDirCannotPassTop()
	{
		assertEquals(new StringBuilder("myfile.txt").toString(), PathUtils
				.processUpDirectories("myfile/foo/bar/../../../../myfile.txt")
				.toString());
	}

	@Test
	public void processUpDirUpMultipleDirCannotPassTop2()
	{
		assertEquals(
				new StringBuilder("myfile.txt").toString(),
				PathUtils.processUpDirectories(
						"myfile/foo/bar/../../../../../myfile.txt").toString());
	}

	@Test
	public void processUpDirUpMultipleDirCannotPassTop3()
	{
		assertEquals(
				new StringBuilder("myfile.txt").toString(),
				PathUtils.processUpDirectories(
						"myfile/foo/bar/../../../../../../myfile.txt")
						.toString());
	}

	@Test
	public void processUpDirUpMultipleDirToTopMissingLeadingSlash()
	{
		assertEquals(new StringBuilder("myfile.txt").toString(), PathUtils
				.processUpDirectories("myfile/baz/../../myfile.txt").toString());
	}

	@Test
	public void processUpDirUpMultipleDirToTopMissingLeadingSlashCannotPassTop()
	{
		assertEquals(new StringBuilder("myfile.txt").toString(), PathUtils
				.processUpDirectories("myfile/baz/../../../myfile.txt")
				.toString());
	}

	@Test
	public void processUpDirUpMultipleDirToTopMissingLeadingSlashCannotPassTop2()
	{
		assertEquals(new StringBuilder("myfile.txt").toString(), PathUtils
				.processUpDirectories("myfile/baz/../../../../myfile.txt")
				.toString());
	}

	@Test
	public void processUpDirUpMultipleDirToTopMissingLeadingSlashCannotPassTop3()
	{
		assertEquals(new StringBuilder("myfile.txt").toString(), PathUtils
				.processUpDirectories("myfile/baz/../../../../../myfile.txt")
				.toString());
	}

	@Test
	public void processUpDirUpMultipleDirToTopWithLeadingSlash()
	{
		assertEquals(new StringBuilder("myfile.txt").toString(), PathUtils
				.processUpDirectories("/myfile/foo/../../myfile.txt")
				.toString());
	}

	@Test
	public void processUpDirUpMultipleDirToTopWithLeadingSlashCannotPassTop()
	{
		assertEquals(new StringBuilder("myfile.txt").toString(), PathUtils
				.processUpDirectories("/myfile/foo/../../../myfile.txt")
				.toString());
	}

	@Test
	public void processUpDirUpMultipleDirToTopWithLeadingSlashCannotPassTop2()
	{
		assertEquals(new StringBuilder("myfile.txt").toString(), PathUtils
				.processUpDirectories("/myfile/foo/../../../../myfile.txt")
				.toString());
	}

	@Test
	public void processUpDirUpMultipleDirToTopWithLeadingSlashCannotPassTop3()
	{
		assertEquals(new StringBuilder("myfile.txt").toString(), PathUtils
				.processUpDirectories("/myfile/foo/../../../../../myfile.txt")
				.toString());
	}

	@Test
	public void removeLeadingSlash()
	{
		assertEquals(new StringBuilder("foo").toString(), PathUtils
				.removeLeadingSlash("/foo").toString());

		assertEquals(new StringBuilder("foo").toString(), PathUtils
				.removeLeadingSlash("foo").toString());

		assertEquals(new StringBuilder("").toString(), PathUtils
				.removeLeadingSlash("/").toString());

		assertEquals(new StringBuilder("").toString(), PathUtils
				.removeLeadingSlash("/").toString());
	}

	@Test
	public void removeSameDirReferences()
	{
		assertEquals(new StringBuilder("foo").toString(), PathUtils
				.removeSameDirReferences("./foo").toString());

		assertEquals(new StringBuilder("foo").toString(), PathUtils
				.removeSameDirReferences("././foo").toString());

		assertEquals(new StringBuilder("foo").toString(), PathUtils
				.removeSameDirReferences("././././foo").toString());

		assertEquals(new StringBuilder("../foo").toString(), PathUtils
				.removeSameDirReferences("./../foo").toString());

		assertEquals(new StringBuilder("../foo").toString(), PathUtils
				.removeSameDirReferences("./.././foo").toString());

		assertEquals(new StringBuilder("../foo").toString(), PathUtils
				.removeSameDirReferences("./../foo./").toString());

		assertEquals(new StringBuilder("../foo/../").toString(), PathUtils
				.removeSameDirReferences("./../foo/./../").toString());

		assertEquals(new StringBuilder("../").toString(), PathUtils
				.removeSameDirReferences("./.././././").toString());
		assertEquals(new StringBuilder("../../../").toString(), PathUtils
				.removeSameDirReferences("./.././././../../").toString());
	}

	@Test
	public void trimToFile()
	{
		assertEquals("myfile.txt", PathUtils.trimToFile("foo/myfile.txt"));
		assertEquals("myfile.txt", PathUtils.trimToFile("/myfile.txt"));
		assertEquals("/", PathUtils.trimToFile("/"));
		assertEquals("foo/", PathUtils.trimToFile("foo/"));
	}
}