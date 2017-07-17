public class Cup{
	private static int cupCapacity;
	public static void main(String[] args) throws TS{
		int capacity = Integer.valueOf(args[0]);
		try{
			if(capacity > 1000){
				TB big = new TB("太大了");
				throw big;
			}else if(capacity < 0){
				TS small = new TS("太小了");
				throw small;
			}
		} catch (TB big){
			System.out.println("由try catch捕捉到的异常信息为：" + big.getMessage());
		}
		Cup.cupCapacity = capacity;
	}
}

