public class Ex{
    public static void main(String[] args) throws NewEx{  
            String s = "abc";  
            if(s.equals("abc")) {
				NewEx ne = new NewEx("错误代码");
                throw ne;  
            } else {  
                System.out.println(s);  
            }  
            //function();  
    }
}
