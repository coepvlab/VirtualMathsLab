package emailtemplates;
class AA{
	public void run() {
		System.out.println("AA");
	}
	
	public void run(String str) {
		System.out.println("AA"+str);
	}
}
public class Test1 extends AA{

	public void run() {
		System.out.println("Test");
	}
	
	public void run1() {
		System.out.println("Test");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AA test = new Test1();
		test.run();
//		test.run1();
		
		Test1 a = new Test1();
		a.run();
		
//		int[] a = {1,2,3,4,5,6,7,8,9,10};
//		int[] b = {1,2,3,4,5,6,7,8,9,10};
//		int[] c = {1,2,3,4,5,6,7,8,9,10};
		
//		for (int i = 0; i < a.length; i++) {
//			for (int j = 0; j < b.length; j++) {
//				for (int k = 0; k < c.length; k++) {
//					if (a[i] == b[j] && a[i] == c[k] && b[j] == c[k]) {
//						System.out.println(a[i]+","+b[j]+","+c[k]);
//					}
//				}
//			}
//		}
		
		
//		for (int i = 0; i < a.length; i++) {
//			for (int j = 0; j < b.length; j++) {
//				for (int k = 0; k < c.length; k++) {
//					if (a[i] == b[j] && a[i]== c[k] && b[j] == c[k]) {
//						if ((a[i]%2) == 0 && (b[j]%2) == 0 && (c[k]%2) == 0) {
//							System.out.println(a[i]+","+b[j]+","+c[k]);
//						}
//					}
//				}
//			}
//		}
		
//		for (int i = 0; i < a.length; i++) {
//			for (int j = 0; j < b.length; j++) {
//				for (int k = 0; k < c.length; k++) {
//					if (a[i] == b[j] && a[i]== c[k] && b[j] == c[k]) {
//						if (!((a[i]%2) == 0) && !((b[j]%2) == 0) && !((c[k]%2) == 0)) {
//							System.out.println(a[i]+","+b[j]+","+c[k]);
//						}
//					}
//				}
//			}
//		}
		
		
//		for(char i='A';i <='Z';++i )
//			System.out.println(i);
//		System.out.println(a.length);
		
//		for (int i = a.length -1; i >= 0; i--) {
//			for (int j = b.length -1; j >= 0 ; j--) {
//				for (int k = c.length-1; k >= 0; k--) {
//					if (a[i] == b[j] && a[i] == c[k] && b[j] == c[k]) {
//						System.out.println(a[i]+","+b[j]+","+c[k]);
//					}
//				}
//			}
//		}
		
		
		}

}
