public class UsePrintNumberThread{
	public static void main(String[] args){
		Copier aCopier = new Copier();
		PrintNumberThread threadOne = new PrintNumberThread(8, aCopier);
		PrintNumberThread threadTwo = new PrintNumberThread(5, aCopier);
		threadOne.setName("小张");
		threadTwo.setName("小王");
		threadOne.start();
		threadTwo.start();
		System.out.println("Main程序退出");
	}
}
