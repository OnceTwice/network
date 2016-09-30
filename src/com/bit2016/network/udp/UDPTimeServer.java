package com.bit2016.network.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Date;
import java.text.SimpleDateFormat;

public class UDPTimeServer {
	private static final int PORT = 9000;
	private static final int BUFFER_SIZE = 0;

	public static void main(String[] args) {
		DatagramSocket socket = null;

		try {
			// 1. socket 생성
			socket = new DatagramSocket(PORT);

			while (true) {
				// 2. 요청 패킷 수신
				DatagramPacket receivePacket = new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE);
				socket.receive(receivePacket);

				// 3. 시간 데이터 응답
				String now = new SimpleDateFormat("yyyy-MM-dd a hh:mm:ss.SSS").format( new Date() );
				byte[] sendData = now.getBytes();
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(), receivePacket.getPort());
				socket.send(sendPacket);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (socket != null && socket.isClosed() == false) {
				socket.close();
			}
		}
	}
}