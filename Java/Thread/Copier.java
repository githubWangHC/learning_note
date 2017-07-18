public class Copier{
	public synchronized void print(int p_page, String p_string){
//	public void print(int p_page, String p_string){
		System.out.println(p_string + "开始工作");
		int i;
		for(i = 0; i< p_page; i++){
			System.out.println(p_string + i);
			try{
				Thread.sleep(100);
			}catch(Exception e){
				System.out.println("出错信息是" + e.getMessage());
			}
		}
		System.out.println(p_string + "结束工作");
	}
}
