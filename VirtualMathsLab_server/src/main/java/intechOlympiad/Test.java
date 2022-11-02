package intechOlympiad;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		float value = 4.5f;
//		String tes = "test";
//		
//		Integer temp = 88;
		
//		if (tes == Math.ceil(tes)) {
//			System.out.println("T - "+Math.ceil(value));
//		}else {
//			System.out.println("F - "+Math.ceil(value));
//		}
		
		
//		if (value == Math.round(value)) {
//			  System.out.println("Integer "+Math.round(value) );
//			} else {
//			  System.out.println("Not an integer"+ Math.round(value));
//			}
		
		
//		 	String input1 = "abc.fd"; 
//	        String input2 = "1234.56"; 
	          
//	        try 
//	        { 
//	            // checking valid integer using parseInt() method 
//	            Integer.parseInt(input1); 
//	            System.out.println(input1 + " is a valid integer number"); 
//	        }  
//	        catch (NumberFormatException e)  
//	        { 
//	            System.out.println(input1 + " is not a valid integer number"); 
//	        } 
//	          
//	        try 
//	        { 
//	            // checking valid integer using parseInt() method 
//	            Integer.parseInt(input2); 
//	            System.out.println(input2 + " is a valid integer number"); 
//	        }  
//	        catch (NumberFormatException e) 
//	        { 
//	            System.out.println(input2 + " is not a valid integer number"); 
//	        } 
	        
	        
	        
//	        if(temp instanceof Integer){
//	            System.out.println((Integer)temp);
//	        }else if(obj instanceof String){
//	            System.out.println((String)obj);
//	        }
	        
	        
	        show(5.0);
	        show("hello");
	        show(5);
	}
	
	
	
	public static void show(Object obj){
        if(obj instanceof Integer){
            System.out.println((Integer)obj);
        }
        else if(obj instanceof String){
            System.out.println((String)obj);
        }
        else if(obj instanceof Double){
        	System.out.println((Double)obj);
        }
    }

}
