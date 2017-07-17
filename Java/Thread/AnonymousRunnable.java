public class AnonymousRunnable{
	public static void main(String[] args){
		Thread thread = new Thread(new Runnable() {
			public void run(){
				try{
				Thread.sleep(5000);
				}catch (Exception e){
				
				}
				System.out.println("在另外进程中了");
			}
		});
		thread.start();
		System.out.println("主程序结束了");
	}
}
