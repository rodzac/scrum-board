package com.github.rzdrigo.scrumboard;

import static org.webbitserver.WebServers.createWebServer;

import java.io.IOException;

import org.webbitserver.WebServer;
import org.webbitserver.handler.exceptions.PrintStackTraceExceptionHandler;

public class Start
{
	public static void main(final String[] args) throws IOException
	{
        final WebServer webServer = createWebServer(Integer.parseInt(args[0]));
        webServer.add("/ws",new BoardWebSocketHanlder());
        webServer.add(new FileHttpHandler());
        webServer.connectionExceptionHandler(new PrintStackTraceExceptionHandler()).start();

        System.out.println("running on: " + webServer.getUri());
	}
}
