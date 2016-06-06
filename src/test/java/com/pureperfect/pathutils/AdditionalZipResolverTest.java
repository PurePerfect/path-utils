package com.pureperfect.pathutils;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import org.junit.Test;

public class AdditionalZipResolverTest
{
	@Test
	public void loadFromFile() throws Exception
	{
		String path = PathUtils.osFriendly("src/test/resources/file.jar");

		PathResolver resolver = PathResolver.createFor(new File(path));

		BufferedReader in = new BufferedReader(new InputStreamReader(
				resolver.openStream("file1.txt")));

		assertEquals("This is file1", in.readLine());
		assertNull(in.readLine());
	}

	@Test
	public void loadFromURL() throws Exception
	{
		String path = PathUtils.osFriendly("src/test/resources/file.jar");

		PathResolver resolver = PathResolver.createFor(new File(path).toURI()
				.toURL());

		BufferedReader in = new BufferedReader(new InputStreamReader(
				resolver.openStream("file1.txt")));

		assertEquals("This is file1", in.readLine());
		assertNull(in.readLine());
	}
}
