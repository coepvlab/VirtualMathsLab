package ac.in.coep.demo;

import java.util.ArrayList;
import java.util.Arrays;

public class TwoVariationFilter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		int arr[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,100,101,102,103,104,105,106,107,108,109,110};
//		int arr[] = {100,102,101,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,41,42,43,44,45,46,47,48,49,50};
//		int arr[] = {107,110,118,111,114,119,117,101,102,103,104,105,106,108,109,112,116,115,122,123,124,127,121,125,126,128,120};
//		int arr[] = {132,133,114,115,117,131,135,137,138,140,149,111,126,130,2,8,1,3,7,109,147,128,150,102,103,104,105,107,108,118,110,112,101,129,106,116,113,4,5,6,120,121,122,123,142,145,125};
//		int arr[] = {19,36,32,17,30,21,34,25,23,28,105,106,108,109,111,112,101,102,104,103};
//		int arr[] = {114,115,123,117,116,122,135,137,138,125,118,119,120,127,128,129,121,131,139,133,134,157,140,141};
//		int arr[] = {121,122,123,108,109,115,124,125,126,116,110,111,117,112,113,114,118,119,135,120,129,127,4,8,22,13,21,12,3,17,9,25,7,2,6,15,16,24,27,19,10,5,14,28,26,11,1,20,23,18,153,131,134,139,141,142,143,132,137,154,144,155,145,146,156,130,148,149,150,151};
//		int arr[] = {101,102,103,104,106,107,109,105,110,111,112,113,114,115,116,11,5,4,12,16,1,7,3,8,13,6,9,15,19,10,21,24,14,17,22,20,18,2,23,122,119,120,123,125,128,133,126,118,127,129,131,132,134,135,130,124};
//		int arr[] = {2,11,8,7,5,20,21,3,9,12,15,14,16,4,22,10,19,13,1,6,136,107,108,120,109,110,111,112,121,132,113,115,114,137,117,119,102,103};
//		int arr[] = {102,101,13,11,21,14,10,12,1,16,17,6,4,7,18,5,9,20,3,2,22,19,15,8};
//		int arr[] = {102,103,101,104,105,109,110,106,107,108,16,13,7,14,22,18,8,20,9,1,11,3,15,21,23,24,2,5,17,6,4,10,19,12};
//		int arr[] = {135,136,132,137,134,138,139,129,119,120,133,130,121,131,122,123,124,108,103,125,109,104,126,110,105,127,101,106,128,102,107,117,114,113,112,115,118,116,23,10,18,19,16,15,4,17,8,1,24,20,22,9,5,12,14,13,25,26,21,3,11,6,2,7,140};
		int arr[] = {133,128,129,104,131,112,118,114,113,130,123,115,116,124,125,117,29,11,15,25,30,21,37,31,12,33,43,18,27,16,4,19,26,42,1,3,5,23,13,45,20,40,28,10,22,32,2,41,8,38,34,35,17,39,6,9,14,46,7,44,24,36,126,119,127,120,105,121,132,102,107};
		
		varFilterArr(arr);
	}
	
	public static void varFilterArr(int arr[]) {
		Arrays.sort(arr);
		
		ArrayList<Integer> smallEven = new ArrayList<>();
		ArrayList<Integer> smallOdd = new ArrayList<>();
		ArrayList<Integer> big1 = new ArrayList<>();
		ArrayList<Integer> big2 = new ArrayList<>();
		ArrayList<Integer> var1 = new ArrayList<>();
		ArrayList<Integer> var2 = new ArrayList<>();
		
		boolean flgg = true;
		for(int i=0; i < arr.length; i++) {
			if(arr[i] < 100) {
				if(arr[i] % 2 == 0) {
					smallEven.add(arr[i]);
				}else {
					smallOdd.add(arr[i]);
				}
			}else {
				if(flgg == true) {
					flgg = false;
					big1.add(arr[i]);
				}else {
					flgg = true;
					big2.add(arr[i]);
				}
			}
		}
		
		System.out.println(smallEven);
		System.out.println(smallOdd);
		System.out.println(big1);
		System.out.println(big2);
		
		int se = smallEven.size();
		int so = smallOdd.size();
		
		int tmp = 0;
		if(se < so) {
			tmp = se;
		}else {
			tmp = so;
		}
		
		int flg = 0;
		for(int j=0; j < tmp; j++) {
			flg = (int) (Math.random() * (1 - 0 + 1) + 0);
			if(flg == 0) {
				var1.addAll(smallEven.subList(j, j+1));
			}else {
				var1.addAll(smallOdd.subList(j, j+1));
			}
			
			if(flg == 1) {
				var2.addAll(smallEven.subList(j, j+1));
			}else {
				var2.addAll(smallOdd.subList(j, j+1));
			}
		}
		
		var1.addAll(big1);
		var2.addAll(big2);
		
		System.out.println("var1 " + var1);
		System.out.println("var2 " + var2);
	}

}
