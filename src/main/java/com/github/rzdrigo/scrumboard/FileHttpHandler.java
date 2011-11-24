package com.github.rzdrigo.scrumboard;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.webbitserver.HttpControl;
import org.webbitserver.HttpHandler;
import org.webbitserver.HttpRequest;
import org.webbitserver.HttpResponse;

public class FileHttpHandler implements HttpHandler
{

	@Override
	public void handleHttpRequest(HttpRequest request, HttpResponse response, HttpControl control) throws Exception
	{
		System.out.println(request.uri());
		final InputStream stream = getClass().getResourceAsStream(request.uri());
		if(stream != null)
			response.write(read(stream));
	}

	private String read(final InputStream stream) throws IOException
	{
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		final byte[] buffer = new byte[1024];
		int read = 0;
		while((read = stream.read(buffer)) > 0)
			baos.write(buffer, 0, read);
		return baos.toString("UTF-8");
	}
}
