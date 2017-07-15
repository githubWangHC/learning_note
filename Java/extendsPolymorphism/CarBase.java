public class CarBase{
	String carName;
	String carColor;
	public CarBase(){
	}
	public CarBase(String p_name, String p_color){
		this.carName = p_name;
		this.carColor = p_color;
	}
	public CarStatus returnStatus(){	//因为获得车辆状态是刚须，故定义在这里了。
		CarStatus carStatu1 = new CarStatus(carName, carColor);
		return carStatu1;
	}
}
