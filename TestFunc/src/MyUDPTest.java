import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
//使用说明:UDP协议：为不可靠传输，需要先开接收端（参数：端口默认为8887），再从发送端发送（参数：发送端口默认为8887，接收端口，接收IP）
//启动此程序两次，分别为A,B。
//初始化时，就启动了接收线程，未启动发送线程。
//点击接收按钮，另开一个接收线程。点击发送线程，另开一份发送线程。
public class MyUDPTest {

	public MyUDPTest() {
		// TODO Auto-generated constructor stub
		JFrame frame=new JFrame("UDPTest");
		frame.setLayout(new GridLayout(2,1));
		JSplitPane tJsp=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,true);
		JSplitPane bJsp=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,true);
		frame.add(tJsp);
		frame.add(bJsp);
		
		Panel receiveP=new Panel();
		receiveP.setLayout(new GridLayout(3,2));
		JLabel ipLab=new JLabel("ip地址",JLabel.CENTER);
		JTextField ipText=new JTextField();
		JLabel portLab=new JLabel("端口号",JLabel.CENTER);
		JTextField portText=new JTextField("8887");
		JButton receBut=new JButton("收");
		receiveP.add(ipLab);
		receiveP.add(ipText);
		receiveP.add(portLab);
		receiveP.add(portText);
		receiveP.add(receBut);
		tJsp.setLeftComponent(receiveP);
		tJsp.setDividerLocation(300);
		JTextArea contTextA=new JTextArea();
		tJsp.setRightComponent(contTextA);
		
		Panel sentP=new Panel();
		sentP.setLayout(new GridLayout(3,2));
		JLabel ipSLab=new JLabel("ip地址",JLabel.CENTER);
		JTextField ipSText=new JTextField("localhost");
		JLabel portSLab=new JLabel("端口号",JLabel.CENTER);
		JTextField portSText=new JTextField("8887");
		JButton sentBut=new JButton("发");
		sentP.add(ipSLab);
		sentP.add(ipSText);
		sentP.add(portSLab);
		sentP.add(portSText);
		sentP.add(sentBut);
		bJsp.setLeftComponent(sentP);
		bJsp.setDividerLocation(300);
		JTextArea contSTextA=new JTextArea();
		bJsp.setRightComponent(contSTextA);
		
		udpRece=new UDPReceive(contTextA,8887);
		receThread=new Thread(udpRece);
		receThread.start();
		
		sentBut.addActionListener(new ActionListener(){
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					udpSent=new UDPSent(contSTextA.getText(),ipSText.getText(),Integer.valueOf(portSText.getText().trim()));
					if(sentThread!=null&&sentThread.isAlive()){
						sentThread.stop();
					}
					sentThread=new Thread(udpSent);
					sentThread.start();
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println(contSTextA.getText());
				contSTextA.setText("");
			}
			
		});
		
		receBut.addActionListener(new ActionListener(){

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				udpRece=new UDPReceive(contTextA,Integer.valueOf(portText.getText().trim()));
				if(receThread!=null){
					receThread.stop();
				}
				receThread=new Thread(udpRece);
				receThread.start();
			}
			
		}); 
		frame.setVisible(true);
		frame.setSize(683, 384);
		frame.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				System.exit(1);
			}
			
		});
	}
	String content=null;
	int port=0;
	String ip=null;
	UDPSent udpSent=null;
	UDPReceive udpRece=null;
	Thread sentThread=null;
	Thread receThread=null;
}
