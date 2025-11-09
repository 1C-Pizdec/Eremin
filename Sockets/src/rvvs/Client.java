package rvvs;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client {
	
	public static void main(String[] args) {
		try {
			Socket s0 = new Socket("localhost", 9001);
			BufferedWriter out = new BufferedWriter(
					new OutputStreamWriter(s0.getOutputStream()));
			String message = "hello";
			out.write(message, 0, message.length());
			out.newLine();
			out.flush();
			s0.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}