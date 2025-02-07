package intechOlympiad;

import java.io.IOException;

public class ProblemStatement_SudhanvaJoshi_Assigment1 {

	/**
	 * Question: In a certain Arithmetic Progression if sum of first $9$ terms is $0$, then that AP will be . . .
	 * <br> 
	 */
	 
	// Followings are global variables to get question with options and solution 
	String questionWithOptions = "";
	String solution = "";
	 
	public static void main(String[] args) throws IOException {

		ProblemStatement_SudhanvaJoshi_Assigment1 template = new ProblemStatement_SudhanvaJoshi_Assigment1();

		template.run();
	}

	
	// This functions returns the question statement with options and solution
	public void run() {

		
		int N = 9; //nth term
		int firstTerm = N - 1;
		int diff = 2*(0 - (firstTerm*N))/(N*(N-1));
		int diff1 = diff - 3;
		int diff2 = diff - 2;
		int diff3 = diff - 1;
		
		
		// This generates problem statement with all the options
		 questionWithOptions = generateProblemStatementWithOptions(firstTerm, diff, N);
		System.out.println(questionWithOptions);
		
		// This generates the solution
		 solution = generateSolutionText(firstTerm, diff, N);
		System.out.println(solution);

	}
	

	
	// This function prints the question statement
	public static String getQuestionStatement(int firstTerm, int diff, int N) {
		String question = "In a certain Arithmetic Progression if sum of first $"+N+"$ terms is $0$, then that AP will be . . .\n";
						
		return question;
	}
	
	
	// This function returns one or more correct answers
	// Algorithm which will have correct logic by taking correct value of common difference 
	// hence will generate correct variation where Sum of terms is equal to zero as required
	
	public static String generateCorrectAnswer(int firstTerm, int diff, int N) {

		int tn2 = firstTerm + (2 - 1) * diff;
		int tn3 = firstTerm + (3 - 1) * diff;
		int tn4 = firstTerm + (4 - 1) * diff;
		int tn5 = firstTerm + (5 - 1) * diff;
		int tn6 = firstTerm + (6 - 1) * diff;
		String correctAns= firstTerm+","+tn2+","+tn3+","+tn4+","+tn5+","+tn6+",....";

		return correctAns;
	}

	
	// This function returns 1st wrong variation 
	public static String wrongOption1(int firstTerm, int diff1, int N) {

		// Algorithm which will have mistake in the logic by taking wrong value of common difference  
		// hence will generate wrong variation where Sum of terms is not equal to zero 
		
		// This is mistake in wrong variation 
		
		int tn2 = firstTerm + (2 - 1) * diff1;
		int tn3 = firstTerm + (3 - 1) * diff1;
		int tn4 = firstTerm + (4 - 1) * diff1;
		int tn5 = firstTerm + (5 - 1) * diff1;
		int tn6 = firstTerm + (6 - 1) * diff1;
		String wrongOpt1 = firstTerm+","+tn2+","+tn3+","+tn4+","+tn5+","+tn6+",....";

		return  wrongOpt1;
	}

	
	// This function returns 2nd wrong variation 
	public static String wrongOption2(int firstTerm, int diff2, int N) {
		// Algorithm which will have another mistake in the logic by taking wrong value of common difference 
		// hence will generate another wrong variation where Sum of terms is not equal to zero
		
		// This is mistake in wrong variation 
		
		int tn2 = firstTerm + (2 - 1) * diff2;
		int tn3 = firstTerm + (3 - 1) * diff2;
		int tn4 = firstTerm + (4 - 1) * diff2;
		int tn5 = firstTerm + (5 - 1) * diff2;
		int tn6 = firstTerm + (6 - 1) * diff2;
		String wrongOpt2 = firstTerm+","+tn2+","+tn3+","+tn4+","+tn5+","+tn6+",....";

		return  wrongOpt2;
	
	}
	
	
	// This function returns 3rd wrong variation 
	public static String wrongOption3(int firstTerm, int diff3, int N) {

		// Algorithm which will have another mistake in the logic by taking wrong value of common difference
		// hence will generate another wrong variation where Sum of terms is not equal to zero
		
		// This is mistake in wrong variation
		
		int tn2 = firstTerm + (2 - 1) * diff3;
		int tn3 = firstTerm + (3 - 1) * diff3;
		int tn4 = firstTerm + (4 - 1) * diff3;
		int tn5 = firstTerm + (5 - 1) * diff3;
		int tn6 = firstTerm + (6 - 1) * diff3;
		String wrongOpt3 = firstTerm+","+tn2+","+tn3+","+tn4+","+tn5+","+tn6+",....";

		return  wrongOpt3;
	
		
	}

	
	// This function prints the solution text
	public static String generateSolutionText(int firstTerm, int diff, int N) {
		double r = 0;
		if(N%2==0) {r=0.5;}
		

		String Solution = "<br>Solution: <br> Formula  for $S_n =\\dfrac {n}{2}[2a+d(n-1)]$ <br> Given: $ S_{" + N + "}  = 0,n = "+N+"$  Now, substituting the values in formula <br> $\\therefore  0 = \\dfrac {"+N+"}{2}[2a"+"+("+N+"-1)d] $ <br>  $\\therefore 0 = (2a+"+(N-1)+"d)$ <br> $\\therefore a = (-"+(((N-1)/2)+r)+")d $  <br> This is the condition to be satisfied by A.P. to get sum of terms as zero for number of terms $ n = "+N+"$ <br> Now, we check which given AP satisfy above condition <br>  AP $= "+generateCorrectAnswer(firstTerm, diff, N) +"$ <br> Here,  $a = " + firstTerm + ",d = "
				+ diff + "$ , Now substituting values of $ a$ and $ d $ in above condition, we get, <br>  $\\therefore   ("+ firstTerm + ") =  -"+(((N-1)/2)+r)+"("+ diff + ")$ \n <br> $\\therefore   "+ (firstTerm) + " =  "+(N-1)+"$ so, condition satisfied<br>$\\therefore $ AP $ = "+generateCorrectAnswer(firstTerm, diff, N) +"$ is the answer ";

		return Solution;
	}

	
	// This function prints the problem statement with options 
	private static String generateProblemStatementWithOptions(int firstTerm, int diff, int N) {
		int diff1 = -5;
		int diff2 = -4;
		int diff3 = -3;

		String output = "Question $:$ " + getQuestionStatement(firstTerm, diff, N) + " <br>Correct Options $:$<br>$" + generateCorrectAnswer(firstTerm, diff, N) + "$<br>Wrong Options $:$<br>$"
				+ wrongOption1(firstTerm, diff1, N) + "$<br>$" + wrongOption2(firstTerm, diff2, N) + "$<br>$" + wrongOption3(firstTerm, diff3, N) +"$";
		return output;
		
	}
}


