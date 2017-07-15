public class TransportCount{
	public static void showTransport(TransportInterface transport){
		if(transport == null){
			return;
		}else{
			TransportStatus transStatus = transport.GetTransportStatus();
			System.out.println("这辆车的名字是：" + transStatus.getName());
			System.out.println("这辆车的颜色是：" + transStatus.getColor());
		}
	}
}
