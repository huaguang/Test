import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServerSocket {
	@SuppressWarnings("resource")
	public MyServerSocket() throws IOException
	{
		//单线程服务端程序；
		/*ServerSocket server=new ServerSocket(8888);
		Socket client=server.accept();
		String str=new String("Hello world!");
		OutputStream out=client.getOutputStream();
		PrintStream print=new PrintStream(out);
		print.print(str);
		System.out.println(client.getRemoteSocketAddress().toString());
		client.close();
		server.close();*/
		//多线程服务端程序
		ServerSocket server=new ServerSocket(8888);
		Socket client=null;
		MyTcpTest service=null;
		while(true)
		{
			client=server.accept();
			service=new MyTcpTest(client);
			new Thread(service).start();
		}
	}

}

