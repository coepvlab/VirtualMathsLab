package ac.in.coep.demo;
import java.util.*;
public class VariationFilter{
		
	public static void main(String []args)
	{
		int arr[]={11,34,54,43,23,98,32,
				76,103,102,789,67,89,
				65,687,78,56,456,564,
				778,87,543,876,64};
		ArrayList<ArrayList<Integer>> finalArr = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> mainArr1Even = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> mainArr1Odd = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> mainArr2Even = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> mainArr2Odd= new ArrayList<ArrayList<Integer>>();
		
		ArrayList<Integer> even2Arrlist1=new ArrayList<>();
		ArrayList<Integer> odd2Arrlist1=new ArrayList<>();
		ArrayList<Integer> even3Arrlist2=new ArrayList<>();
		ArrayList<Integer> odd3Arrlist2=new ArrayList<>();
		
		int evenn=0, oddn=0, evenn1=0, oddn1=0;
		
		for(int i=0;i< arr.length ; i++){
			if(arr[i]>0 && arr[i]<100 )
			{
				if(arr[i]%2==0)
				{
					even2Arrlist1.add(arr[i]);
					evenn++;
				}
				else
				{
					odd2Arrlist1.add(arr[i]);
					oddn++;
				}
			}else{
				if(arr[i]%2==0)
				{
					even3Arrlist2.add(arr[i]);
					evenn1++;
				}
				else{
					odd3Arrlist2.add(arr[i]);
					oddn1++;
				}
			}
		}
		System.out.println(even2Arrlist1.size());
		System.out.println(odd2Arrlist1.size());
		mainArr1Even=countOfDigitNOs(even2Arrlist1,evenn,6);
		mainArr1Odd=countOfDigitNOs(odd2Arrlist1,oddn,6);
		
		mainArr2Even=countOfDigitNOs(even3Arrlist2,evenn1,2);
		mainArr2Odd=countOfDigitNOs(odd3Arrlist2,oddn1,2);
				
		System.out.println("mainArr1Even"+mainArr1Even+"\n");
		System.out.println("mainArr1Odd"+mainArr1Odd+"\n");
		
		System.out.println("mainArr2Even"+mainArr2Even+"\n");
		System.out.println("mainArr2Odd"+mainArr2Odd+"\n");
		
		finalArr.addAll(mainArr1Even);
		finalArr.addAll(mainArr1Odd);
		finalArr.addAll(mainArr2Even);
		finalArr.addAll(mainArr2Odd);
		
		System.out.println("finalArr"+finalArr);
		System.out.println(finalArr.get(0).get(0));
	}
	
	
	public  static ArrayList<ArrayList<Integer>> countOfDigitNOs(ArrayList<Integer> arr,int n,int noOfDigit)
	{
		
			ArrayList<Integer> list=new ArrayList<>();
			ArrayList<ArrayList<Integer>> mainArr = new ArrayList<ArrayList<Integer>>();
			int count1=0;int k1=0;
			for(int i=0;i<arr.size() ; i++){
				ArrayList<Integer> arrlist=new ArrayList<>();
				arrlist.add(arr.get(i));
				list.add(arrlist.get(0));
				count1++;
				if(((count1)%noOfDigit)==0){
					ArrayList<Integer> tempArr=new ArrayList<>();
					//int cnt=0;
					for(int j=k1;j<count1;j++){
		
						tempArr.add(list.get(k1));
						k1++;//cnt++;
					}
					if(arrlist.size()>0){
					mainArr.add(tempArr);
					}
				}
				
			}
			int len=arr.size();
			int rem=len%6;
			if(rem!=0)
			{ 
				
				int v=len-rem;
			   ArrayList<Integer>rm=new ArrayList<Integer>();
			   for(int j=0;j<rem;j++)
			   {
				   rm.add(arr.get(v));
				   v++;
			   }
			   mainArr.add(rm);
			}
			return mainArr;
	}
	
	
}