package intechOlympiad;

import java.io.IOException;

    public class ProblemStatement_ShreyashTambe_Assignment1 {

    /*
    If for a given Arithmetic Progression $S_{11}= . . .$ and $S_{19} = . . .,$ then $t_5+ t_{19} = . . .?$<br>
     #जर एका अंकगणिती श्रेढीमध्ये $S_{11} = . . .$आणि $S_{19} = . . .,$ तर $t_5+ t_{19} = . . .?$
     */

    // Followings are global variables to get question with options and solution
    String questionWithOptions = "";
    String solution = "";

    public static void main(String[] args) throws IOException {

        ProblemStatement_ShreyashTambe_Assignment1 javaFile = new ProblemStatement_ShreyashTambe_Assignment1();

        javaFile.run();
    }


    // This functions returns the question statement with options and solution
    public void run() {

        int N = 11,M=19,P=5;
        int difF=7,a=50,SN=440,SM=2147;
        

        // This generates problem statement with all the options
        questionWithOptions = generateProblemStatementWithOptions( N,  M,  P, difF,a,SN,SM);
        System.out.println(questionWithOptions);

        // This generates the solution
        solution = generateSolutionText(N, M, P,difF,a,SN,SM);
        System.out.println(solution);

    }



    // This function prints the question statement
    public static String getQuestionStatement( int N, int M, int P,int diff,int a,int SN, int SM) {


         String question = " If for a given Arithmetic Progression $S_{"+N+"}= "+SN+" $ and $S_{"+M+"} = "+SM+",$ then $t_"+P+"+ t_{"+M+"} = . . .?$<br>";

        return question;
    }


    // This function returns one or more correct answers
    public static String generateCorrectAnswers( int N, int M, int P,int diff,int a,int SN, int SM) {

        int ANS1 = ((4*a)+((P-1)*diff)+((M-1)*diff));

        return String.valueOf(ANS1);
    }


    // This function returns 1st wrong variation
    public static String wrongVariation1( int N, int M, int P,int diff,int a,int SN, int SM) {

        // Write an algorithm which will have mistake in the logic and hence will generate wrong variation

        // This is mistake in wrong variation
        int ANS2 = ((4*a)+((P)*diff)+((M-1)*diff));

        return String.valueOf(ANS2);
    }


    // This function returns 2nd wrong variation
    public static String wrongVariation2( int N, int M, int P,int diff,int a,int SN, int SM) {
        // Write an algorithm which will have another mistake in the logic and hence will generate another wrong variation

        // This is mistake in wrong variation
        int ANS3 = ((4*a)+((P+1)*diff)+((M)*diff));

        return String.valueOf(ANS3);
    }


    // This function returns 3rd wrong variation
    public static String wrongVariation3( int N, int M, int P,int diff,int a,int SN, int SM) {

        // Write an algorithm which will have another mistake in the logic and hence will generate another wrong variation

        // This is mistake in wrong variation
        int ANS4 = ((4*a)+((P)*diff)+((M)*diff));

        return String.valueOf(ANS4);
    }


    // This function prints the solution text
    public static String generateSolutionText( int N, int M, int P,int diff,int a,int SN, int SM) {

        int ANS = ((4*a)+((P-1)*diff)+((M-1)*diff));

        String Solution = "<br>Solution: formula for $S_{n}=\\dfrac {n}{2}[2a+(n-1)\\times d]$ <br>  $S_{"+N+"} -> n="+N+"$ hence,$ S_{"+N+"}=\\dfrac {"+N+"}{"+2+"}[2a+("+N+"-1)\\times d]$ <br> and $S_{"+M+"} -> n="+M+"$ hence,$ S_{"+M+"}=\\dfrac {"+M+"}{"+2+"}[2a+("+M+"-1)\\times d]$ <br> substituting values of $S_{"+N+"}= "+SN+" $ and $S_{"+M+"} = "+SM+" $ <br> we get, $ "+SN+" =\\dfrac {"+N+"}{"+2+"}[2a+("+N+"-1)\\times d]$ <br> and $ "+SM+" =\\dfrac {"+M+"}{"+2+"}[2a+("+M+"-1)\\times d]$ <br> now we get two equations $ 2a+("+(N-1)+")\\times d = "+((SN*2)/N)+" $. . . $(1)$ <br>  $ 2a+("+(M-1)+")\\times d = "+((SM*2)/M)+" $. . . $(2)$ <br> By solving these two equations we get $ a="+a+" $ and $ d="+diff+" $ <br> ";
        Solution=Solution+" To calculate $t_"+P+"+ t_{"+M+"}$ By using formula $t_{n}=a+(n-1)\\times d $ <br> we get $t_{"+P+"} -> n="+P+"$ hence,$ t_{"+P+"}="+a+"+("+P+"-1)\\times "+diff+" $ <br> and, $t_{"+M+"} -> n="+M+"$ hence, $t_{"+M+"}="+a+"+("+M+"-1)\\times "+diff+" $ <br> Hence, $t_{"+P+"}="+(a+(P-1)*diff)+"$ and $t_{"+M+"}="+(a+(M-1)*diff)+" $ <br> $\\therefore t_"+P+"+ t_{"+M+"} = "+ANS+" $";

        return Solution;
    }


    // This function prints the problem statement with options
    private static String generateProblemStatementWithOptions( int N, int M, int P,int diff,int a,int SN, int SM) {

        String output = "Question :" + getQuestionStatement( N,  M,  P,diff,a,SN,SM) + "<br>Correct Options :<br>" + generateCorrectAnswers( N,  M,  P,diff,a,SN,SM) + "<br>Wrong Options :<br>"
                + wrongVariation1( N,  M,  P,diff,a,SN,SM) + "<br>" + wrongVariation2( N,  M,  P,diff,a,SN,SM) + "<br>" + wrongVariation3( N,  M,  P,diff,a,SN,SM) ;
        return output;

    }
}
