package com.bit2016.network.echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class EchoServer {
	private static final int PORT = 9000;
	
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		
		try {
			//1. Server Socket 생성
			String localHostAddress = InetAddress.getLocalHost().getHostAddress();
			serverSocket = new ServerSocket();
			
			//2. Binding
			serverSocket.bind( new InetSocketAddress( localHostAddress, PORT ) );
			log( "binding " + localHostAddress + ":" + PORT );
			
			//3. Accept
			Socket socket = serverSocket.accept();
			
			try{
				//4. IOStream 생성(받아오기)
				BufferedReader br = new BufferedReader( new InputStreamReader( socket.getInputStream(), "UTF-8" ) );
				PrintWriter pw = new PrintWriter( new OutputStreamWriter( socket.getOutputStream(), "UTF-8" ), true );
				while( true ) {
					String data = br.readLine();
					if( data == null ) {
						log( "closed by client" );
						break;
					}
					
					log( "received:" + data );
					pw.println( data );
				}
			} catch( SocketException ex ){
				log( "abnormal closed by client" );
			} catch( IOException ex ) {
				log( "error:" + ex );
			} finally {
				socket.close();
			}
		} catch( IOException ex ) {
			log( "error:" + ex );
		}
	}
	
	public static void log( String message ) {
		System.out.println( "[Echo Server]" + message );
	}
}
