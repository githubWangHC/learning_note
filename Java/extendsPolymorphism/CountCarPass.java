public class CountCarPass{
	public static void showCar(CarBase base){
		if(base == null){
			return;
		}else{
			CarStatus carStatus = base.returnStatus();
			System.out.println("这辆车的名字是：" + carStatus.getName());
			System.out.println("这辆车的颜色是：" + carStatus.getColor());
		}
	}
}
