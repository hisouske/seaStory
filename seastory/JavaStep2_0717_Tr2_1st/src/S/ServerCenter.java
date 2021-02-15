package S;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerCenter {

ServerSocket serverS = null;
Socket serverClient = null;
ServerSocket ClientMsg = null;

ArrayList<WithClient> clientSocket = new ArrayList<>();

	ServerCenter() {
		fromclient();
	}
	
	private void fromclient() {
		try {
			int num = 1;
			serverS = new ServerSocket();
			serverS.bind(new InetSocketAddress("127.0.0.1", 8888));

			while (true) {
				System.out.println("클라이언트 대기중");
				serverClient = serverS.accept();
				System.out.println("클라이언트 접속");

				WithClient w = new WithClient(serverClient,this);
				w.setName("client" + num);
				num++;
				w.start();
				clientSocket.add(w);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void allSend(String msg) {
		for(int i=0; i<clientSocket.size(); i++) {
			try {
				//if(!id.equals(clientSocket.get(i).getId())){
					//clientSocket.get(i).sendMsg.write(msg.getBytes());
				clientSocket.get(i).goData(msg);
				//}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
