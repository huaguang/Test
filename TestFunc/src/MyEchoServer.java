import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MyEchoServer implements Runnable{
	public MyEchoServer() throws IOException
	{
		server=new ServerSocket(8887);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		boolean flag=true;
		while(flag)
		{
			try{
				client=server.accept();
				buf=new BufferedReader(new InputStreamReader(client.getInputStream()));
				out=new PrintStream(client.getOutputStream());
			}catch(Exception e){
				e.printStackTrace();
			}
			out.print("服务端：Hello world!连接到新的客户端\n");
			while(true)
			{
				try {
					str=buf.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
			try {
				client.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		out.close();
		try {
			buf.close();
			server.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	ServerSocket server=null;
	Socket client=null;
	BufferedReader buf=null;
	PrintStream out=null;
	String str=null;
}
