public class TransportStatus{
	private String carName;
	private String carColor;
	public TransportStatus(String p_car_name, String p_car_color){
		this.carName = p_car_name;
		this.carColor = p_car_color;
	}
	public String getName(){
		return carName;
	}
	public String getColor(){
		return carColor;
	}
}
