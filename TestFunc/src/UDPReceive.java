

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import javax.swing.JTextArea;

public class UDPReceive implements Runnable {

	public UDPReceive(JTextArea contJTA,int port) {
		// TODO Auto-generated constructor stub
		this.port=port;
		this.buf=new byte[1024];
		this.contJTA=contJTA;
		try {
			socket=new DatagramSocket(port);
			pack=new DatagramPacket(buf,1024);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while(true)
			{
				socket.receive(pack);
				cont=new String(pack.getData(),0,pack.getLength());
				if("close".equals(cont))
					break;
				contJTA.setText(contJTA.getText()+"\n"+cont+"\tAddress:"+pack.getAddress().getHostAddress()+"\tPort:"+pack.getPort());
			}
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		socket.close();
	}
	String ip=null;
	int port=0;
	String cont;
	byte[]buf=null;
	DatagramPacket pack=null;
	DatagramSocket socket=null;
	JTextArea contJTA=null;

}
