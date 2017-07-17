public class UseMyRunnable{
	public static void main(String[] args){
		MyRunnable myRun = new MyRunnable();
		Thread useTh = new Thread(myRun);
		useTh.start();
	}
}
