import java.util.ArrayList;
import java.util.LinkedList;
/*数据结构：State类：节点；stateList状态链表：储存所有出现过的合法的状态；queueList队列链表：将所有未处理（即未判断是否可以生成下一个节点）的节点放入队列；
 * 对队列中的每一个状态循环遍历所有规则，当产生一个状态
 * 1.此状态已经在状态链表中，则，只是在原状态的nextList增加一个此状态的no,表明此状态为原状态的下一个状态
 * 2.如果此状态不存在在状态链表中，说明此状态是一个新状态。
 * 		则，将原状态的nextList增加一个此状态的no,此状态放入stateList中,此状态放入queueList中。
 * 将遍历完毕的状态清除出队列。
 */
public class StateSpace {
	int no=1;
	ArrayList<State> stateList=new ArrayList<State>();		//所有已经出想过合法的状态
	LinkedList<State> queueList=new LinkedList<State>();	//未完成判断是否可以生成下一个节点的状态放进此队列
	public StateSpace(){
		State s1=new State(1,1,1,1,1);
		s1.no=no++;
		stateList.add(s1);
		queueList.add(s1);
		State test;
		State newS;
		while(!queueList.isEmpty()){
			//assert no<1000;		//如果超过1000个节点，表示异常。
			test=queueList.poll();
			newS=null;
			switch(test.pc1){
			case 1:{
				newS=new State(2,test.pc2,test.c1,test.c2,test.turn);
				break;
			}
			case 2:{
				newS=new State(3,test.pc2,test.c1,test.c2,test.turn);
				break;
			}
			case 3:{
				newS=new State(4,test.pc2,0,test.c2,test.turn);
				break;
			}
			case 4:{
				if(test.c2==0){
					newS=new State(5,test.pc2,test.c1,test.c2,test.turn);
				}else{
					newS=new State(9,test.pc2,test.c1,test.c2,test.turn);
				}
				break;
			}
			case 5:{
				if(test.turn==2){
					newS=new State(6,test.pc2,test.c1,test.c2,test.turn);
				}else{
					newS=new State(4,test.pc2,test.c1,test.c2,test.turn);	//这里永远不能生成新状态
				}
				break;
			}
			case 6:{
				newS=new State(7,test.pc2,1,test.c2,test.turn);
				break;
			}
			case 7:{
				if(test.turn==1){
					newS=new State(8,test.pc2,test.c1,test.c2,test.turn);
				}else{	//这里停住了，无新状态；
					newS=null;
				}
				break;
			}
			case 8:{	//这里不可能生成新状态
				newS=new State(4,test.pc2,0,test.c2,test.turn);
				break;
			}
			case 9:{
				newS=new State(10,test.pc2,test.c1,test.c2,test.turn);
				break;
			}
			case 10:{
				newS=new State(11,test.pc2,1,test.c2,test.turn);
				break;
			}
			case 11:{
				newS=new State(1,test.pc2,test.c1,test.c2,2);
				break;
			}
			}
			nextState(newS,test);
			
			switch(test.pc2){
			case 1:{
				newS=new State(test.pc1,2,test.c1,test.c2,test.turn);
				break;
			}
			case 2:{
				newS=new State(test.pc1,3,test.c1,test.c2,test.turn);
				break;
			}
			case 3:{
				newS=new State(test.pc1,4,test.c1,0,test.turn);
				break;
			}
			case 4:{
				if(test.c1==0){
					newS=new State(test.pc1,5,test.c1,test.c2,test.turn);
				}else{
					newS=new State(test.pc1,9,test.c1,test.c2,test.turn);
				}
				break;
			}
			case 5:{
				if(test.turn==1){
					newS=new State(test.pc1,6,test.c1,test.c2,test.turn);
					//	newS=new State(6,test.pc2,test.c1,test.c2,test.turn);
				}else{
					newS=new State(test.pc1,4,test.c1,test.c2,test.turn);
					//	newS=new State(4,test.pc2,test.c1,test.c2,test.turn);	//这里永远不能生成新状态
				}
				break;
			}
			case 6:{
				newS=new State(test.pc1,7,test.c1,1,test.turn);
				//	newS=new State(7,test.pc2,1,test.c2,test.turn);
				break;
			}
			case 7:{
				if(test.turn==2){
					newS=new State(test.pc1,8,test.c1,test.c2,test.turn);
					//	newS=new State(8,test.pc2,test.c1,test.c2,test.turn);
				}else{	//这里停住了，无新状态 ；
					newS=null;
				}
				break;
			}
			case 8:{	//这里不可能生成新状态
				newS=new State(test.pc1,4,test.c1,0,test.turn);
				//	newS=new State(4,test.pc2,0,test.c2,test.turn);
				break;
			}
			case 9:{
				newS=new State(test.pc1,10,test.c1,test.c2,test.turn);
				//	newS=new State(10,test.pc2,test.c1,test.c2,test.turn);
				break;
			}
			case 10:{
				newS=new State(test.pc1,11,test.c1,1,test.turn);
				//	newS=new State(11,test.pc2,1,test.c2,test.turn);
				break;
			}
			case 11:{
				newS=new State(test.pc1,1,test.c1,test.c2,1);
				//		newS=new State(1,test.pc2,test.c1,test.c2,2);
				break;
			}
			}
			if(newS!=null){
				assert !((newS.pc1==9)&&(newS.pc2==9));	//如果同时为9，表示不互斥。
				assert !((newS.pc1==9)&&(newS.pc2==10));//如果同时为9，10,表示不互斥。
				assert !((newS.pc1==10)&&(newS.pc2==9));//如果同时为10,9表示不互斥。
				assert !((newS.pc1==10)&&(newS.pc2==10));//如果同时为10,10，表示不互斥。
			}
			nextState(newS,test);
			assert test.nextList.size()>0;				//如果不大于0，则表示死锁
		}
		System.out.println("Size:"+stateList.size()+stateList.toString());
		
	}
	void nextState(State newS,State test){
		if(newS!=null){
			if(!stateList.contains(newS)){
				//新状态
				newS.no=no++;
				test.nextList.add(no-1);
				stateList.add(newS);
			//	System.out.println(stateList.size()+"\t"+stateList.toString());
				queueList.add(newS);
			}else{
				newS=stateList.get(stateList.indexOf(newS));
				assert newS.no!=0;
				test.nextList.add(newS.no);
			}
		}
	}	
}
//状态节点
class State{
	int no=0;	//节点编号
	int pc1;
	int pc2;
	int c1;
	int c2;
	int turn;
	ArrayList<Integer> nextList;	//下一个节点编号
	public boolean equals(Object s) {
		if(s instanceof State)
		{
			State s2=(State)s;
			if(this.pc1==s2.pc1&&this.pc2==s2.pc2&&this.c1==s2.c1&&this.c2==s2.c2&&this.turn==s2.turn)
				return true;
		}
		return false;
	}
	public State(int pc1, int pc2, int c1, int c2, int turn) {
		this.pc1 = pc1;
		this.pc2 = pc2;
		this.c1 = c1;
		this.c2 = c2;
		this.turn = turn;
		nextList=new ArrayList<Integer>();
	}
	public String toString(){
		return pc1+","+pc2+","+c1+","+c2+","+turn+","+nextList.size();
	}
}
