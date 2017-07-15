public class Bick implements TransportInterface{
	String bickName;
	String bickColor;
	public Bick(String p_name, String p_color){
		bickName = p_name;
		bickColor = p_color;
	}
	public TransportStatus GetTransportStatus(){
		TransportStatus TransStatu = new TransportStatus(bickName, bickColor);
		return TransStatu;
	}
}
