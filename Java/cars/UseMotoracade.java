public class UseMotoracade {
	public static void main(String[] args){
		Motorcade guide = new Motorcade();
		guide.name = "guiderName";
		guide.cars = new Car[5];
		int i = 0;
		String name = "一辆车";
		for(i = 0; i < 5; i++){
			guide.cars[i] = new Car();
			guide.cars[i].speed = i;
			guide.cars[i].color = "红色";
			guide.cars[i].carName = name + (i + 1);
			guide.cars[i].direction = "南方";
		}
		System.out.println("车队的名字为：" + guide.name);
		for(i = 0; i< 5; i++){
			System.out.println("车速为：" + guide.cars[i].speed );
		}
	}
}
