


import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

class MyYList {
	private JFrame frame = new JFrame("Beyole");
	private Container container = frame.getContentPane();
	private JList<?> list1 = null;// 定义列表框
	private JList<?> list2 = null;// 定义列表框

	public MyYList() {
		this.frame.setLayout(new GridLayout(1, 2));
		String nation[] = { "中国", "日本", "俄罗斯", "朝鲜", "美国" };
		Vector<String> vector = new Vector<String>();
		vector.add("主站");
		vector.add("博客");
		vector.add("论坛");
		this.list1 = new JList<Object>(nation);
		this.list2 = new JList<Object>(vector);
		list1.setBorder(BorderFactory.createTitledBorder("你喜欢哪个国家"));
		list2.setBorder(BorderFactory.createTitledBorder("你喜欢哪个网站"));
		list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list2.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		container.add(this.list1);
		container.add(this.list2);
		this.frame.setSize(330, 180);
		this.frame.setVisible(true);
		this.frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				System.exit(1);
			}
		});
	}
}
