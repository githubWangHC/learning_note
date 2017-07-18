public class TestSystem{
	public static void main(String[] args){
		TestSystem ts = new TestSystem();
		Thread ctmsg = Thread.currentThread();
		System.out.println("hello" + ctmsg.getName());
	}
}
