package com.bit2016.network.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class UDPTimeClient {
	private static final String SERVER_IP = "192.168.1.2";
	private static final int SERVER_PORT = 9000;
	private static final int BUFFER_SIZE = 1024;

	public static void main(String[] args) {
		DatagramSocket socket = null;

		try {
			// 1. 소켓 생성
			socket = new DatagramSocket();

			// 2. 요청 패킷 전송
			DatagramPacket sendPacket = new DatagramPacket("".getBytes(), 0, new InetSocketAddress( SERVER_IP, SERVER_PORT ));
			socket.send( sendPacket );
			
			// 3. 시간 데이터 수신
			DatagramPacket receivePacket = new DatagramPacket( new byte[ BUFFER_SIZE ], BUFFER_SIZE );
			socket.receive( receivePacket );
			
			// 4. 출력
			String now = new String( receivePacket.getData(), 0, receivePacket.getLength(), "UTF-8" );
			System.out.println( now );
			
		} catch ( IOException e ) {
			e.printStackTrace();
		}
	}

}
