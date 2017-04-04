import java.io.IOException;
import java.util.Scanner;

public class TestInputAndOutPut {
		int []a = new int[10];
		String str;
		boolean b;
		char ch;
		public TestInputAndOutPut(){
//			try {
//				ch = (char) System.in.read();
//				System.out.println("***"+ch+"***");
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
			Scanner scan;
			scan = new Scanner(System.in);
			for(int i = 0;i < 10; i++){
				a[i] = scan.nextInt();
				str = scan.nextLine();
				System.out.println("***" + a[i] + "***" + str +"***" + str.length());
			}
			
			scan.close();
		}
}
