package org.github.rzdrigo.scrumboard;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.webbitserver.WebSocketConnection;
import org.webbitserver.WebSocketHandler;

public class BoardWebSocketHanlder implements WebSocketHandler
{
	private final ConcurrentMap<Object, WebSocketConnection> connections;

	public BoardWebSocketHanlder()
	{
		connections = new ConcurrentHashMap<Object, WebSocketConnection>();
	}

	@Override
	public void onOpen(WebSocketConnection conn) throws Exception
	{
		System.out.println("--------> created...");
		connections.putIfAbsent(conn.httpRequest().id(), conn);
	}

	@Override
	public void onClose(WebSocketConnection conn) throws Exception
	{
		System.out.println("--------> close...");
		connections.remove(conn.httpRequest().id());
	}

	@Override
	public void onMessage(WebSocketConnection conn, String msg) throws Throwable
	{
		System.out.println("--------> message..." + msg);
		for(WebSocketConnection current : connections.values())
			if(!current.httpRequest().id().equals(conn.httpRequest().id()))
				current.send(msg);
	}

	@Override
	public void onMessage(WebSocketConnection conn, byte[] msg) throws Throwable
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void onPong(WebSocketConnection conn, String msg) throws Throwable
	{
		conn.ping(msg);
	}

}
