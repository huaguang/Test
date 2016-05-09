

/*
 * 功能及使用说明：
 * 通过输入一个个字节值来计算其互联网检验和
 * 
 */
public class CalCheckSum {

	public CalCheckSum() {
		// TODO Auto-generated constructor stub
		long sum=0;
		short temp=0;
		/*int len=0;
		Scanner scan=new Scanner(System.in);
		len=Integer.valueOf(scan.nextLine());
		for(int i=0;i<len;i++){
			temp=scan.nextByte();
			sum+=temp;
		}*/
		sum=input();
		while(sum>65535)
		{
			temp=(short) (sum&0xffff);
			sum=sum>>16;
			sum+=temp;
		}
		temp=(short) (sum&0xffff);
		temp=(short)~temp;
		System.out.println(temp);
	}
	public long input()
	{
		long sum=0;
		short temp_2;
		byte []temp=new byte[]{69, 0, 0, 32, 46, -14, 0, 0, 1, 1, 0,0, -64, -88, 1, -15, -76, 97, 33, 107};
		for(int i=0;i<temp.length;i+=2)
		{
			temp_2=(short) ((temp[i]<<8)+temp[i+1]);
			sum+=(int)(temp_2&0xffff);
		}
		return sum;
		
	}

}

