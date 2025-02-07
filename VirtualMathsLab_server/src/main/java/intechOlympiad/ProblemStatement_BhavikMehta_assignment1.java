package intechOlympiad;

public class ProblemStatement_BhavikMehta_assignment1 {


    /**
     * Question: If for a given Arithmetic Progression $d = . . .$ and $t_5 = . . ,$ then $S_5 = . . .?$<br>
     */

    // Followings are global variables to get question with options and solution
    String questionWithOptions = "";
    String solution = "";

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        ProblemStatement_BhavikMehta_assignment1 javaFile = new ProblemStatement_BhavikMehta_assignment1();

        javaFile.run();

    }


    // This functions returns the question statement with options and solution
    public void run() {

        // This functions returns the question statement with options and solution
        int a = 7;
        int diff = 4;
        int n = 5; //nth term
        int tn=a+(n-1)*diff;
        int sum1=a;
        for (int k = 2; k <= n; k++)
        {
            int ts = a + (k - 1) * diff;
            sum1 += ts;
        }

        // This generates problem statement with all the options
        questionWithOptions = generateProblemStatementWithOptions(diff, n, tn, a, sum1);
        System.out.println(questionWithOptions);

        // This generates the solution
        solution = generateSolutionText(diff, n, tn, a, sum1);
        System.out.println(solution);
    }


    // Change the number of parameters to this method depending on nature of your question
    public static String getQuestionStatement(int diff, int n,int tn) {

        // This function prints the question statement
        // Each question should have different numbersâ€™ data and solution
        // Each question should have all information in English as well as one more language other than English.

        String question = "If for a given Arithmetic Progression $d = "+diff+" $ and $t_{"+n+"} = "+tn+"$ then $S_{"+n+"} = . . . ?$<br>\n";
        return question;
    }

    // Change the number of parameters to this method depending on nature of your question
    public static String generateCorrectAnswers(int sum1) {
        // This function returns one or more correct answers
        // Each question should have at least one correct answer
        // Each option should have all information in English as well as one more language other than English.

        String correctAns = String.valueOf(sum1);
        return correctAns;
    }



    // Change the number of parameters to this method depending on nature of your question
    public static String wrongVariation1(int a, int n,int diff) {

        // Write an algorithm which will have mistake in the logic and hence will generate wrong variation
        // Should include a variation leading to wrong answers.
        // Each variation should have a mistake in only one step of the correct solution.
        // Discard a variation where wrong answer is equal to the correct answer.
        // Each option should have all information in English as well as one more language other than English.
        // This function returns 1st wrong variation

        int sum2=a;
        for (int k = 2; k < n; k++)
        {
            int ts = a + (k - 1) * diff;
            sum2 += ts;
        }
        String wrongOpt1 = String.valueOf(sum2);
        return wrongOpt1;
    }



    // Change the number of parameters to this method depending on nature of your question
    public static String wrongVariation2(int a, int n,int diff) {

        // Write an algorithm which will have another mistake in the logic and hence will generate another wrong variation
        // Should include a variation leading to wrong answers.
        // Each variation should have a mistake in only one step of the correct solution
        // Discard a variation where wrong answer is equal to the correct answer
        // Each option should have all information in English as well as one more language other than English.
        // This function returns 2nd wrong variation

        int sum3=a;
        for (int k = 2; k <= n+1; k++)
        {
            int ts = a + (k - 1) * diff;
            sum3 += ts;
        }
        String wrongOpt2 =  String.valueOf(sum3);
        return wrongOpt2;
    }



    // Change the number of parameters to this method depending on nature of your question
    public static String wrongVariation3(int a, int n,int diff) {

        // Write an algorithm which will have another mistake in the logic and hence will generate another wrong variation
        // Should include a variation leading to wrong answers.
        // Each variation should have a mistake in only one step of the correct solution.
        // Discard a variation where wrong answer is equal to the correct answer.
        // Each option should have all information in English as well as one more language other than English.
        // This function returns 3rd wrong variation

        int sum4=a;
        for (int k = 2; k < n-1; k++)
        {
            int ts = a + (k - 1) * diff;
            sum4 += ts;
        }

        String wrongOpt3 = String.valueOf(sum4);
        return wrongOpt3;
    }

    // Change the number of parameters to this method depending on nature of your question
    public static String generateSolutionText(int diff,int n,int tn,int a,int sum1) {
        // This function prints the solution text
        // Each question should have automatically generated solution explanation

        String Solution = "Given : $d = "+diff+"$ , $t_{"+n+"} = "+tn+"$<br>\n" +
                "To Find : $S_{"+n+"}$<br>\n" +
                "Formula to find sum of $n$ terms: $S_n = \\dfrac{n}{2}[2a + (n-1) \\times d]$<br>\n"+
                "$t_n = n^{th}$ term of the AP<br>\n" +
                "$d$ = Common difference between two consecutive terms of A.P. = $"+diff+"$ <br>\n" +
                "Here $t_{"+n+"} = "+tn+" \\Rightarrow n="+n+"$<br>\n" +
                "and $d = "+diff+"$ as given <br>\n" +
                "$\\therefore$ we get<br>\n" +
                "$"+tn+" = a + ("+n+"-1)\\times "+diff+" \\Rightarrow a = "+a+"$<br>\n" +
                "Now we will use same formula to find $S_{"+n+"}$ as<br>\n" +
                "$S_{"+n+"} = \\dfrac{"+n+"}{2}[2 \\times "+a+" + ("+n+"-1) \\times "+diff+"] = "+sum1+" $<br>\n"+
                "$\\fbox{$\\therefore S_{"+n+"}= "+sum1+"$}$ is the answer<br>\n";
        return Solution;
    }


    // This function prints the problem statement with options
    private static String generateProblemStatementWithOptions(int diff, int n, int tn, int a, int sum1) {


        // Generate Problem Statement With Options here
        String output = "Question :" + getQuestionStatement(diff, n, tn) + "<br>Correct Options :<br>" + generateCorrectAnswers(sum1) + "<br>Wrong Options :<br>"
                + wrongVariation1(a, n, diff) + "<br>" + wrongVariation2(a, n, diff) + "<br>" + wrongVariation3(a, n, diff) ;
        return output;

    }

}