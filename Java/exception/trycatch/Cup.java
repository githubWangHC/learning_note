public class Cup{
	private static int cupCapacity;
	public static void main(String[] args) throws TB,TS{
		int capacity = Integer.valueOf(args[0]);
		if(capacity > 1000){
			TB big = new TB("太大了");
			throw big;
		}else if(capacity < 0){
			TS small = new TS("太小了");
			throw small;
		}
		Cup.cupCapacity = capacity;
	}
}
