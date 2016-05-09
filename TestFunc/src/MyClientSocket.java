import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class MyClientSocket {
	public MyClientSocket() throws UnknownHostException, IOException {
		// TODO Auto-generated constructor stub
		
		client=new Socket("localhost",8887);
		
		in=new Scanner(System.in);
		buf=new BufferedReader(new InputStreamReader(client.getInputStream()));
		out=new PrintStream(client.getOutputStream());
		str=buf.readLine();
		System.out.println("服务器内容："+str);
		while(true)
		{
			str=in.nextLine();
			out.println(str);
			if("close".equals(str)||"exit".equals(str))
				break;
			try {
				str=buf.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(str);
		}
	
	//	System.out.println(client.getRemoteSocketAddress().toString());
		client.close();
		buf.close();
		out.close();
		in.close();
	}
	Socket client=null;
	BufferedReader buf=null;
	PrintStream out=null;
	Scanner in=null;
	String str=null;

}
