import java.io.IOException;

public class DECSteps {
	int[][]IP = {{58,50,42,34,26,18,10,2},
			{60,52,44,36,28,20,12,4},
			{62,54,46,38,30,22,14,6},
			{64,56,48,40,32,24,26,8},
			{57,49,41,33,25,17,9,1},
			{59,51,43,35,27,19,11,3},
			{61,53,45,37,29,21,13,5},
			{63,55,47,39,31,23,15,7}};
	byte []input = new byte[8];
	public DECSteps(){
		System.out.println(IP);
		input();
	}
	private void input(){
		try {
			System.in.read(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i = 0;i<8;i++){
			System.out.println(input[i]);
		}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     
	}
}
