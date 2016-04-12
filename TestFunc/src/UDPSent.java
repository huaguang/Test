

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPSent implements Runnable {

	public UDPSent(String cont,String ip,int port){
		// TODO Auto-generated constructor stub
		this.cont=cont;
		this.ip=ip;
		this.port=port;
		buf=new byte[1024];
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			pack=new DatagramPacket(cont.getBytes("UTF-8"),cont.length(),InetAddress.getByName(ip),port);
			socket=new DatagramSocket(8888);
			socket.send(pack);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		socket.close();
	}
	String ip=null;
	int port=0;
	String cont;
	DatagramPacket pack=null;
	DatagramSocket socket=null;
	byte[] buf=null;
}
