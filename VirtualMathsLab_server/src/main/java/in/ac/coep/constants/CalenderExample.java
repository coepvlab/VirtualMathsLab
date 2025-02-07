package in.ac.coep.constants;

import com.ibm.icu.util.IndianCalendar;

public class CalenderExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IndianCalendar calendar = new IndianCalendar(2022, 06, 07);
		System.out.println(calendar.getType());
		System.out.println(calendar.getFirstDayOfWeek());
	}

}
