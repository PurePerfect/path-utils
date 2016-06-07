package test.utils;

import java.io.File;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * 
 * @author J. Chris Folsom
 * @since 0.3
 * @version 0.3
 */
public class TestServer
{
	private Server server;

	public TestServer(int port, String warpath) throws Exception
	{
		System.out.println("Starting test server on port: " + port);

		server = new Server(port);

		WebAppContext ctx = new WebAppContext("embeddedtestserver", warpath);

		ctx.setContextPath("/");
		File warFile = new File(warpath);

		System.out.println("Context is: " + warFile.getAbsolutePath());

		ctx.setWar(warFile.getAbsolutePath());

		server.setHandler(ctx);

		server.start();

		System.out.println("Embedded test server started successfully.");
	}

	public void stop() throws Exception
	{
		if (this.server.isRunning())
		{
			System.out.println("Stopping embedded test server....");

			this.server.stop();

			System.out.println("Embedded test server stopped successfully.");
		}
	}

	@Override
	protected void finalize() throws Throwable
	{
		this.stop();
	}
}