package intechOlympiad;

public class Template {

	
	/**
	 * Problem statement
	 */
	
	// Followings are global variables to get question with options and solution 
	String questionWithOptions = "";
	String solution = "";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Template template = new Template();

		template.run();

	}
	

	// This functions returns the question statement with options and solution
	public void run() {

		// This functions returns the question statement with options and solution
		 
		  // use global variable questionWithOptions to assign the output of the generateProblemStatementWithOptions() functions
		questionWithOptions = generateProblemStatementWithOptions();
		System.out.println(questionWithOptions);
		
			// use global variable solution to assign the output of the generateSolutionText() functions
		solution = generateSolutionText();
		System.out.println(solution);
	}

	
	// Change the number of parameters to this method depending on nature of your question
	public static String getQuestionStatement() {

		// This function prints the question statement
		// Each question should have different numbers data and solution
		// Each question should have all information in English as well as one more language other than English.
		
		String question = "";
		return question;
	}

	// Change the number of parameters to this method depending on nature of your question
	public static String generateCorrectAnswer() {

		String correctAns = "";
		// This function returns one or more correct answers
		// Each question should have at least one correct answer
		// Each option should have all information in English as well as one more language other than English.
		return correctAns;
	}

	
	
	// Change the number of parameters to this method depending on nature of your question
	public static String wrongOption1() {
	
		// Write an algorithm which will have mistake in the logic and hence will generate wrong option
		// Should include a option leading to wrong answers.
		// Each option should have a mistake in only one step of the correct solution.
		// Discard a option where wrong answer is equal to the correct answer.
		// Each option should have all information in English as well as one more language other than English.
		// This function returns 1st wrong option 
		
		String wrongOpt1 = "";
		return wrongOpt1;
	}

	
	
	// Change the number of parameters to this method depending on nature of your question
	public static String wrongOption2() {

		// Write an algorithm which will have another mistake in the logic and hence will generate another wrong option
		// Should include a option leading to wrong answers.
		// Each option should have a mistake in only one step of the correct solution
		// Discard a option where wrong answer is equal to the correct answer
		// Each option should have all information in English as well as one more language other than English.
		// This function returns 2nd wrong option 
		
		String wrongOpt2 = "";
		return wrongOpt2;
	}
	
	

	// Change the number of parameters to this method depending on nature of your question
	public static String wrongOption3() {
		
		// Write an algorithm which will have another mistake in the logic and hence will generate another wrong option
		// Should include a option leading to wrong answers.
		// Each option should have a mistake in only one step of the correct solution.
		// Discard a option where wrong answer is equal to the correct answer.
		// Each option should have all information in English as well as one more language other than English.
		// This function returns 3rd wrong option 
		
		String wrongOpt3 = "";
		return wrongOpt3;
	}

	// Change the number of parameters to this method depending on nature of your question
	public static String generateSolutionText() {

		// This function prints the solution text
		// Each question should have automatically generated solution explanation
		//This is where you can get the correct answer by calling the generateCorrectAnswers() function instead of writing the same logic to get correct again
		
		String Solution = "";
		return Solution;
	}

	
	// This function prints the problem statement with options
	private static String generateProblemStatementWithOptions() {

		
		// Generate Problem Statement With Options here
		String output = "";
		return output;

	}

}
