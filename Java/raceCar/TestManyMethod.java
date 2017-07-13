public class TestManyMethod{
	public static void main(String[] args){
		Car normalCar = new Car("普通车", "白色");
		normalCar.setSpeedAndDirection(50,"采石场");
		normalCar.printCarRunningMessage();
		Car crazyCar = new Car("疯狂赛车","黑色");
		crazyCar.setSpeedAndDirection(35,"赛车场");
		crazyCar.printCarRunningMessage();
		crazyCar.overTakeCar(normalCar);
		crazyCar.printCarRunningMessage();
		crazyCar.raiseSpeed((int)50);
		crazyCar.printCarRunningMessage();
		crazyCar.raiseSpeed((byte)50);
		crazyCar.printCarRunningMessage();
		Car trafficAdmin = new Car("汽车管理员", "红色", true);
		trafficAdmin.setSpeedAndDirection(55, "交管中心");	
		trafficAdmin.printCarRunningMessage();
		CarStatus normalCarStatus = new CarStatus();
		normalCarStatus = trafficAdmin.getAnotherCarStatus(normalCar);
		if(trafficAdmin.isLegalCarStatus(normalCarStatus)){
			trafficAdmin.setNameForAnotherCar(normalCar,"驾车典范");
		}
		normalCar.printCarRunningMessage();
		CarStatus crazyCarStatus = new CarStatus();
		crazyCarStatus = trafficAdmin.getAnotherCarStatus(crazyCar);
		crazyCar.stopAnotherCar(trafficAdmin);
		if(!trafficAdmin.isLegalCarStatus(crazyCarStatus)){
			trafficAdmin.setNameForAnotherCar(crazyCar,"超速车辆");
			trafficAdmin.stopAnotherCar(crazyCar);
			crazyCar.printCarRunningMessage();
		}
		normalCar.setSpeed(0);
		normalCar.printCarRunningMessage();
	}
}
