import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServerSocket {
	public MyServerSocket() throws IOException
	{
		ServerSocket server=new ServerSocket(8888);
		Socket client=server.accept();
		String str=new String("Hello world!");
		OutputStream out=client.getOutputStream();
		PrintStream print=new PrintStream(out);
		print.print(str);
		System.out.println(client.getRemoteSocketAddress().toString());
		client.close();
		server.close();
	}

}
