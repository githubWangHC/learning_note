public class ShowNameClass extends Thread{
	public ShowNameClass(){
		super();
	}
	public ShowNameClass(String p_name){
		super(p_name);
	}
	public void run(){
		System.out.println("线程的名字是" + this.getName());
	}
}
