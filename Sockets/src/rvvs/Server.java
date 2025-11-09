package rvvs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	private static void m() {
		System.out.println("Hello world from server!");
	}
	
	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(9001);
			System.out.println("waiting...");
			Socket s0 = ss.accept();
			BufferedReader in = new BufferedReader(
						new InputStreamReader(s0.getInputStream()));
			String message = in.readLine();
			if (message.equals("hello")) {
					m();
			}
			in.close();
			s0.close();
			ss.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}