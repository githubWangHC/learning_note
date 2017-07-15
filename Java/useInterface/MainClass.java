public class MainClass{
	public static void main(String[] args){
		CarBase baseCar = new CarBase("大黄蜂","红色");//完成初始化对象
		TransportCount.showTransport(baseCar);					//利用类的静态方法，不用再创造引用和对象了。
		Bus bus = new Bus("大卡车", "蓝色", "东南");
		TransportCount.showTransport(bus);
		Bick bick = new Bick("自行车", "紫色");
		TransportCount.showTransport(bick);
	}
}
