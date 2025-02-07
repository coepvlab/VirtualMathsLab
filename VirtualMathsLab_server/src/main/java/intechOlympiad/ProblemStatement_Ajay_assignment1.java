package intechOlympiad;

import java.io.IOException;

public class ProblemStatement_Ajay_assignment1 {

    /**
     * Question: What is the sum of the first seven multiples of $7?$ ?
     */

    // Followings are global variables to get question with options and solution
    String questionWithOptions = "";
    String solution = "";

    public static void main(String[] args) throws IOException {

        ProblemStatement_Ajay_assignment1 javaFile = new ProblemStatement_Ajay_assignment1();

        javaFile.run();
    }


    // This functions returns the question statement with options and solution
    public void run() {

        int firstTerm = 7;
        int diff = 7;
        int N = 7; //nth term

        // This generates problem statement with all the options
        questionWithOptions = generateProblemStatementWithOptions(firstTerm, diff, N);
        System.out.println(questionWithOptions);

        // This generates the solution
        solution = generateSolutionText(firstTerm, diff, N);
        System.out.println(solution);

    }



    // This function prints the question statement
    public static String getQuestionStatement(int firstTerm, int diff, int N) {
        String question = "What is the sum of the first seven multiples of $"+firstTerm+"?$";
        return question;
    }


    // This function returns one or more correct answers
    public static String generateCorrectAnswers(int firstTerm, int diff, int N) {

        int Sn =(N)*(2*firstTerm+(N-1)*diff)/2;

        return String.valueOf(Sn);
    }


    // This function returns 1st wrong variation
    public static String wrongVariation1(int firstTerm, int diff, int N) {

        // Write an algorithm which will have mistake in the logic and hence will generate wrong variation

        // This is mistake in wrong variation
        int wrongOpt1 = (N)*(2*firstTerm+(N)*diff)/2;

        return String.valueOf(wrongOpt1);
    }


    // This function returns 2nd wrong variation
    public static String wrongVariation2(int firstTerm, int diff, int N) {
        // Write an algorithm which will have another mistake in the logic and hence will generate another wrong variation

        // This is mistake in wrong variation
        int wrongOpt2 = (N)*(firstTerm+(N-1)*diff)/2;

        return String.valueOf(wrongOpt2);
    }


    // This function returns 3rd wrong variation
    public static String wrongVariation3(int firstTerm, int diff, int N) {

        // Write an algorithm which will have another mistake in the logic and hence will generate another wrong variation

        // This is mistake in wrong variation
        int wrongOpt3 =(N)*(2*firstTerm+(N+1)*diff)/2;

        return String.valueOf(wrongOpt3);
    }


    // This function prints the solution text
    public static String generateSolutionText(int firstTerm, int diff, int N) {

        int Sn =(N)*(2*firstTerm+(N-1)*diff)/2;

        String Solution = "<br>Solution:  Formula  for $S_n=\\dfrac{n}{2}[2a+(n-1)d]$ <br> From given data we have $a=" + firstTerm + ",d="
                + diff + "$ and $n=" + N + "\\ (\\because S_{" + N + "}$ is asked.) <br> $\\therefore S_{" + N + "}$=$\\dfrac{"+N+"}{2}[2("+firstTerm+")+("+N+"-1)"+diff+"]="+Sn+"$ is the answer ";

        return Solution;
    }


    // This function prints the problem statement with options
    private static String generateProblemStatementWithOptions(int firstTerm, int diff, int N) {

        String output = "Question :" + getQuestionStatement(firstTerm, diff, N) + "<br>Correct Options :<br>" + generateCorrectAnswers(firstTerm, diff, N) + "<br>Wrong Options :<br>"
                + wrongVariation1(firstTerm, diff, N) + "<br>" + wrongVariation2(firstTerm, diff, N) + "<br>" + wrongVariation3(firstTerm, diff, N) ;
        return output;

    }
}


