package emailtemplates;


public class FindMultiplePrimeNumberProgram2 {

	/**
	 * Question: Find the prime numbers among the numbers 5, 81,63, 37, 12, 46, 70, 9?
	 */
	
	
	// Followings are global variables to get question with options and solution 
	String questionWithOptions = "";
	String solution = "";
	
	public static void main(String[] args)  {

		FindMultiplePrimeNumberProgram2 javaFile = new FindMultiplePrimeNumberProgram2();
		javaFile.run();
		
	}

	// This functions returns the question statement with options and solution
	public void run() {

		int inputNumber[] = { 5, 81, 63, 37, 12, 46, 70, 9 };
		
		String questionWithOptions = generateProblemStatementWithOptions(inputNumber);
		System.out.println(questionWithOptions);
		
		
		// This generates the solution
		String solution = generateSolutionText(inputNumber);
		System.out.println(solution);

	}

	// This function prints the question statement
	public static String getQuestionStatement(int[] inputNumber) {

		String question = "Find the prime numbers among the numbers ";

		String numbers = "";
		for (int i = 0; i < inputNumber.length; i++) {
			if (i != (inputNumber.length - 1)) {
				numbers += inputNumber[i] + " , ";
			} else {
				numbers += inputNumber[i] + " ";
			}
		}

		question += numbers + "<br/>";

		return question;
	}

	
	// This function returns one or more correct answers
	public static String generateCorrectAnswers(int[] inputNumber) {

		String result = "";

		for (int i = 0; i < inputNumber.length; i++) {
			boolean isItPrime = true;
			int num = inputNumber[i];

			if (num <= 1) {
				isItPrime = false;

			} else {
				for (int j = 2; j <= num / 2; j++) {
					if ((num % j) == 0) {
						isItPrime = false;
						break;
					} else {
					}
				}

				if (isItPrime == true) {
					result += " " + num + ",";
				}
			}
		}

		return result.substring(0, result.length() - 1);

	}
	
	
	// This function returns 1st wrong variation 
	public static String wrongVariation1(int[] inputNumber) {
		
		// Write an algorithm which will have  mistake in the logic and hence will generate  wrong variation
		String result = "81, 37	";

		return result;

	}

	
	// This function returns 2st wrong variation 
	public static String wrongVariation2(int[] inputNumber) {
		// Write an algorithm which will have another mistake in the logic and hence will generate another wrong variation
		String result = "70, 12";

		return result;

	}
	

	// This function returns 3rd wrong variation 
	public static String wrongVariation3(int[] inputNumber) {

		// Write an algorithm which will have another mistake in the logic and hence will generate another wrong variation
		String result = "5, 46";

		return result;

	}

	
	// This function prints the solution text
	public static String generateSolutionText(int[] inputNumber) {

		String numbers = "";
		for (int i = 0; i < inputNumber.length; i++) {
			if (i != (inputNumber.length - 1)) {
				numbers += inputNumber[i] + " , ";
			} else {
				numbers += inputNumber[i] + " ";
			}
		}

		String getCorrectAns = generateCorrectAnswers(inputNumber);
		String Solution = "<br>Solution: ";

		Solution += "<br>" + getCorrectAns
				+ " The number which has only two factors, 1 and the number itself, is called a prime number. In the given set of numbers "
				+ numbers + "the numbers " + getCorrectAns.split(",")[0] + " and " + getCorrectAns.split(",")[1]
				+ ", have only two factors : 1 and the number itself. In all other options either no number is prime or at least one of the number is not prime."
				+ " Therefore only combination of 5 and 37 which are prime numbers is the right option. ";
		

		return Solution;
	}

	
	// This function prints the problem statement with options
	private static String generateProblemStatementWithOptions(int[] inputNumber) {
		
		String output = "Question :" + getQuestionStatement(inputNumber) + "<br>Correct Options :<br>" + generateCorrectAnswers(inputNumber) + "<br>Wrong Options :<br>"
				+ wrongVariation1(inputNumber) + "<br>" + wrongVariation2(inputNumber) + "<br>" + wrongVariation3(inputNumber);
		
		return output;
	}
}
