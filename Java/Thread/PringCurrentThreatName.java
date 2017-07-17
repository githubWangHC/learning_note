public class PringCurrentThreatName{
	public static void main(String[] args){
		Thread currentThreadMsg = Thread.currentThread();
		System.out.println("当前线程的名字是："+ currentThreadMsg.getName());
		currentThreadMsg.setName("改变线程名字");
		System.out.println("当前线程的名字是："+ currentThreadMsg.getName());

	}
}

