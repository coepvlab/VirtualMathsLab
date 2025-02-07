package intechOlympiad;

public class FindSinglePrimeNumberProgram {

	/**
	 * Question: x is a prime number
	 * 
	 */
	
	// Followings are global variable to get question with options and solution 
	String questionWithOptions = "";
	String solution = "";
	
	public static void main(String[] args) {

		FindSinglePrimeNumberProgram object = new FindSinglePrimeNumberProgram();
	
		object.run();
		
	}

	// This functions returns the question statement with options and solution
	public void run() {

		int inputNumber = 3;
		
		// This generates problem statement with all the options
		 questionWithOptions = generateProblemStatementWithOptions(inputNumber);
		System.out.println(questionWithOptions);
		
		// This generates the solution
		 solution = generateSolutionText(inputNumber);
		System.out.println(solution);
	}

	
	// This function prints the question statement
	public static String getQuestionStatement(int inputNumber) {

		String question =  inputNumber + " is a prime number.";
		
		return question;
	}
	
	
	
	// This function returns one or more correct answers
	public static String generateCorrectAnswer(int inputNumber) {

		String result = "";

		boolean isItPrime = true;

		if (inputNumber <= 1) {
			isItPrime = false;
		} else {
			for (int i = 2; i <= inputNumber / 2; i++) {
				if ((inputNumber % i) == 0) {
					isItPrime = false;

					break;
				}
			}
		}
		
		if (isItPrime) {
			result = "True";
		} else {
			result = "False";
		}
		
		return result;

	}

	
	// This function returns 1st wrong option 
	// The mistake introduced in getting this wrong option is to reverse the logic for returning true and false, as described below in the function
	public static String wrongOption1(int inputNumber) {

		// Write an algorithm which will have mistake in the logic and hence will generate wrong option
		String result = "";
		
		boolean isItPrime = true;

		if (inputNumber <= 1) {
			isItPrime = false;
		} else {
			for (int i = 2; i <= inputNumber / 2; i++) {
				if ((inputNumber % i) == 0) {
					isItPrime = false;

					break;
				}
			}
		}
		
		// This is mistake for getting wrong option 
		if (isItPrime) {
			result = "False";
		} else {
			result = "True";
		}

		return result;

	}

	
	
	// This function returns 2nd wrong option 
	public static String wrongOption2(int inputNumber) {

		// Write an algorithm which will have another mistake in the logic and hence will generate another wrong option
		String result = "";
		
		return result;

	}

	
	
	// This function returns 3rd wrong option 
	public static String wrongOption3(int inputNumber) {

		// Write an algorithm which will have another mistake in the logic and hence will generate another wrong option
		String result = "";

		return result;

	}

	
	// This function prints the solution text
	public static String generateSolutionText(int inputNumber) {

		String getCorrectAns = generateCorrectAnswer(inputNumber); //This is where you can get the correct answer instead of writing the same logic for getting the correct answer again
		String Solution = "<br>Solution: ";

		Solution += "<br/>" + getCorrectAns.split("#")[0] +"<br/>"
				+ " The number which has only two factors, 1 and the number itself, is called a prime number. The given number "
				+ inputNumber 
				+ ", have only two factors : 1 and the number itself.";
		

		return Solution;
	}
	
	
	// This function prints the problem statement with options 
		private static String generateProblemStatementWithOptions(int inputNumber) {
			
			String output = "Question :<br />" + getQuestionStatement(inputNumber) + "<br />Correct Options :<br />" + generateCorrectAnswer(inputNumber) + "<br>Wrong Options :<br />"
					+  wrongOption1(inputNumber);
			
			return output;
		}

}