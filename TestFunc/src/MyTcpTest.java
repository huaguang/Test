import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class MyTcpTest implements Runnable {

	public MyTcpTest(Socket client) throws IOException {
		// TODO Auto-generated constructor stub
		this.client=client;
		buf=new BufferedReader(new InputStreamReader(client.getInputStream()));
		out=new PrintStream(client.getOutputStream());
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		out.println("hello world!");
		while(true)
		{
			try {
				str=buf.readLine();
				if("close".equals(str))
					break;
				out.println("Echo:"+str);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			client.close();
			buf.close();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	Socket client=null;
	BufferedReader buf=null;
	PrintStream out=null;
	String str=null;
}
