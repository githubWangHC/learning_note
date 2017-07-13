public class Car{
	int speed;
	String color;
	String name;
	String direction;
	boolean isTrafficAdmin;
	public Car(){
	
	}
	public Car(String p_name, String p_color){
		this(p_name, p_color, false);	
	}
	public Car(String p_name, String p_color, boolean p_isTrafficAdmin){
	this.name = p_name;
	this.color = p_color;
	this.isTrafficAdmin = p_isTrafficAdmin;
	}
	public void driveCar(){
		speed = 50;
		direction = "南方";
	}
	public void raiseSpeed(int p_speed){
		if(p_speed < 0){
			System.out.println("参数小于0，提速结束");
			return;
		}else{
			int currentSpeed = speed + p_speed;
			speed = currentSpeed;
			System.out.println("用int提速完成");
		}
	}
	public void raiseSpeed(byte p_speed){
		if(p_speed < 0){
			System.out.println("参数小于0，提速结束");
			return;
		}else{
			int currentSpeed = speed + p_speed;
			speed = currentSpeed;
			System.out.println("用byte提速完成");
		}
	}
	public void setSpeedAndDirection(int p_speed, String p_direction){
		speed = p_speed;
		direction = p_direction;
	}
	public boolean isOverSpeed(){
		if(speed > 80){
			return true;
		}else{
			return false;
		}
	}
	public void overTakeCar(Car anotherCar){
		speed = anotherCar.speed +5;
		direction = anotherCar.direction;
	}
	public void setNameForAnotherCar(Car anotherCar, String new_name){
		if(isTrafficAdmin == true){
				anotherCar.name = new_name;
		}else{
			System.out.println("没有权限更改车的名字");
			return;
		}
	}
	public void stopAnotherCar(Car anotherCar){
		if(isTrafficAdmin == true){
			anotherCar.speed = 0;
		}else{
			System.out.println("没有权限停止另一辆车");
			return;
		}
	}
	public CarStatus getAnotherCarStatus(Car anotherCar){
		if(!isTrafficAdmin){
			System.out.println("没有权限查看车辆状态");
			anotherCar.speed = 0;
		}
			CarStatus getStatus = new CarStatus(anotherCar.speed, anotherCar.direction);
			return getStatus;
	}
	public boolean isLegalCarStatus(CarStatus p_car_status){
		if(p_car_status.speed > 80){
			return false;
		}else
			return true;
	}
	public void setSpeed(int p_speed){
		if(p_speed < 0){
			System.out.println("车速不能为负值");
			return;
		}else{
			speed = p_speed;
		}
	}
	public void printCarRunningMessage(){
		System.out.println("名字：" + name);
		System.out.println("速度：" + speed);
		System.out.println("方向：" + direction);
	}
}
