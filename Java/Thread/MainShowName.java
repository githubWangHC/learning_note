public class MainShowName{
	public static void main(String[] args){
		ShowNameClass defaultName = new ShowNameClass();
		ShowNameClass name = new ShowNameClass("你的名字");
		defaultName.start();
		name.start();
	}
}
