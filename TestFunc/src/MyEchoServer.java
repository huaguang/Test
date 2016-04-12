import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MyEchoServer {
	public MyEchoServer() throws IOException
	{
		ServerSocket server=null;
		server=new ServerSocket(8887);
		Socket client=null;
		BufferedReader buf=null;
		PrintStream out=null;
		String str=null;
		boolean flag=true;
		while(flag)
		{
			client=server.accept();
			buf=new BufferedReader(new InputStreamReader(client.getInputStream()));
			out=new PrintStream(client.getOutputStream());
			out.print("Hello world!\n");
			while(true)
			{
				str=buf.readLine();
				System.out.println(str);
				if("close".equals(str))
					break;
				else if ("exit".equals(str))
				{
					flag = false;
					break;
				}
				out.println("echo:"+str);
			}
			client.close();
		}
		out.close();
		buf.close();
		server.close();
	}

}
