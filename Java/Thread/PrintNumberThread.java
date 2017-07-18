public class PrintNumberThread extends Thread{
	Copier aCopier;
	int page;
	public PrintNumberThread(int p_times, Copier p_copier){
		this.page = p_times;
		this.aCopier = p_copier;
	}
	public void run(){
		aCopier.print(page, this.getName());
	}
}
