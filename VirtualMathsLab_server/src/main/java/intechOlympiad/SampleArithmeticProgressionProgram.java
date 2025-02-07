package intechOlympiad;

import java.io.IOException;

public class SampleArithmeticProgressionProgram {

	/**
	 * Question: If for certain AP if a=... and d=..., then find tn ?
	 */
	 
	// Followings are global variables to get question with options and solution 
	String questionWithOptions = "";
	String solution = "";
	 
	public static void main(String[] args) throws IOException {

		SampleArithmeticProgressionProgram javaFile = new SampleArithmeticProgressionProgram();

		javaFile.run();
	}

	
	// This functions returns the question statement with options and solution
	public void run() {

		int firstTerm = 7; // the first term in the sequence
		int diff = 4; // the common difference between terms
		int N = 13; // nth  term in the sequence
		
		// This generates problem statement with all the options
		 questionWithOptions = generateProblemStatementWithOptions(firstTerm, diff, N);
//		System.out.println(questionWithOptions);
		
		// This generates the solution
		 solution = generateSolutionText(firstTerm, diff, N);
//		System.out.println(solution);

	}
	

	
	// This function prints the question statement
	public static String getQuestionStatement(int firstTerm, int diff, int N) {
		String question = "If for certain AP if $a= " + firstTerm + "$ and $d= " + diff + "$, then find $t_{" + N + "}$ ?";
		return question;
	}
	
	
	// This function returns one or more correct answers
	public static String generateCorrectAnswer(int firstTerm, int diff, int N) {
		// A.P. Formula
		int tn = firstTerm + (N - 1) * diff;

		return String.valueOf(tn);
	}

	
	// This function returns 1st wrong option 
	// The mistake introduced in getting this wrong option is to change in AP formula - [firstTerm + (N * diff)], as described below in the function
	public static String wrongOption1(int firstTerm, int diff, int N) {

		// Write an algorithm which will have mistake in the logic and hence will generate wrong option
		
		// This is mistake for getting wrong option 
		int wrongOpt1 = firstTerm + (N * diff);

		return String.valueOf(wrongOpt1);
	}

	
	// This function returns 2nd wrong option 
	// The mistake introduced in getting this wrong option is to change in AP formula - [(N - 1) * diff], as described below in the function
	public static String wrongOption2(int firstTerm, int diff, int N) {
		// Write an algorithm which will have another mistake in the logic and hence will generate another wrong option
		
		// This is mistake for getting wrong option  
		int wrongOpt2 = (N - 1) * diff;

		return String.valueOf(wrongOpt2);
	}
	
	
	// This function returns 3rd wrong option 
	// The mistake introduced in getting this wrong option is to change in AP formula - [firstTerm - (N - 1) * diff] , as described below in the function
	public static String wrongOption3(int firstTerm, int diff, int N) {

		// Write an algorithm which will have another mistake in the logic and hence will generate another wrong option
		
		//This is mistake for getting wrong option 
		int wrongOpt3 = firstTerm - (N - 1) * diff;

		return String.valueOf(wrongOpt3);
	}

	
	// This function prints the solution text
	public static String generateSolutionText(int firstTerm, int diff, int N) {

		String correctAns = generateCorrectAnswer(firstTerm, diff, N);////This is where you can get the correct answer instead of writing the same logic for getting the correct answer again
		
		String Solution = "<br>Solution:  Formula  for $t_{n}=a+(n-1)d$ <br> From given data we have $a=" + firstTerm + ",d="
				+ diff + "$ and $n=" + N + "\\ (\\because t_{" + N + "}$ is asked.) <br> $\\therefore t_{" + N + "}$=$"
				+ firstTerm + "+(" + N + "-1)" + diff + "=" + correctAns
				+ "$ is the answer ";

		return Solution;
	}

	
	// This function prints the problem statement with options 
	private static String generateProblemStatementWithOptions(int firstTerm, int diff, int N) {

		String output = "Question :" + getQuestionStatement(firstTerm, diff, N) + "<br>Correct Options :<br>" + generateCorrectAnswer(firstTerm, diff, N) + "<br>Wrong Options :<br>"
				+ wrongOption1(firstTerm, diff, N) + "<br>" + wrongOption2(firstTerm, diff, N) + "<br>" + wrongOption3(firstTerm, diff, N) ;
		return output;
		
	}
}
