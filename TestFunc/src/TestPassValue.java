import java.util.ArrayList;
import java.util.List;

public class TestPassValue {
	
	private int x=5;
	private List<Integer> mList=new ArrayList<Integer>();
	public TestPassValue() {
		super();
		System.out.println(x);
		setX(x);
		System.out.println(x);
		setList(mList);
		if(mList!=null){
			System.out.println(mList.size());
		}else{
			System.out.println("空");
		}
			
	}
	private void setX(int x){
		x=10;
	}
	private void setList(List<Integer> list){
		list=new ArrayList<Integer>();	//当非基本类型，但是重新new了之后，为传值
										//没有重新new，就是传址。
										//基本类型，为传值
		for(int i=0;i<10;i++){
			list.add(i);
		}
	}
	
	

}

