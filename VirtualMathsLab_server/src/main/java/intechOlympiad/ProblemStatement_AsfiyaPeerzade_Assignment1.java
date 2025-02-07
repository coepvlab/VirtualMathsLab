package intechOlympiad;

import java.util.Vector;

public class ProblemStatement_AsfiyaPeerzade_Assignment1 {

    // Followings are global variables to get question with options and solution
    String questionWithOptions = "";
    String solution = "";

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        ProblemStatement_AsfiyaPeerzade_Assignment1 assign1_AsfiyaP = new ProblemStatement_AsfiyaPeerzade_Assignment1();

        assign1_AsfiyaP.run();

    }

    // This functions returns the question statement with options and solution
    public void run() {
        /* Two n values of Sn according to the need of the question are initialized as n & m */
        int n = 11;
        int m = 9;
        /*Vector is like the dynamic array which can grow or shrink its size.
        If we add or remove any element, it increases and decreases its size respectively.
        We can store n number of elements in it as there is no size limit.
        Here I've introduced vector to add up the signs(like >,<,=) as well as remove it to get the wrong options.*/
        Vector<String> v=new Vector<String>();
        //vector is a dynamic array so the index number will change as we will remove any element.
        v.add("<");     //assigned to index 0.
        v.add(">");     //assigned to index 1.
        v.add("=");     //assigned to index 2.
        v.add("Can't Say");//assigned to index 3.
        // This functions returns the question statement with options and solution

        // use global variable questionWithOptions to assign the output of the generateProblemStatementWithOptions() functions
        questionWithOptions = generateProblemStatementWithOptions(n,m,v);
        System.out.println(questionWithOptions);

        // use global variable solution to assign the output of the generateSolutionText() functions
        solution = generateSolutionText(n,m,v);
        System.out.println(solution);
    }


    // Change the number of parameters to this method depending on nature of your question
    public static String getQuestionStatement(int n, int m) {

        // This function prints the question statement
        // Each question should have different numbers data and solution
        // Each question should have all information in English as well as one more language other than English.

        String question = "If for a given Arithmetic Progression $d<0,$ then $S_{"+n+"}$ will be . . . . $S_{"+m+"}$";
        return question;
    }

    // Change the number of parameters to this method depending on nature of your question
    public static String generateCorrectAnswer(int n, int m, Vector v) {
        String sign = "";// string type of variable is taken for signs like >,<,etc.
        /*In the given question, d<0 i.e d is negative and nothing is mentioned about a also the value of Sn is not given
        The only data we have is n values.
        If d is -ve, every next term will be smaller than the previous term.*/
        if(n>m){
            sign = "<";
        }
        else if(n<m){
            sign = ">";
        }
        else{
            sign = "=";
        }
        /*the correct answer is assigned to the variable sign, so we have to remove the right answer from the vector. */
        for(int i = 0; i<v.size();i++){
            if(v.elementAt(i)==sign){
                v.remove(sign);
            }
            return sign;
        }

        String correctAns = sign;
        // This function returns one or more correct answers
        // Each question should have at least one correct answer
        // Each option should have all information in English as well as one more language other than English.
        return correctAns;
    }



    // Change the number of parameters to this method depending on nature of your question
    public static String wrongOption1(Vector v) {

        // Write an algorithm which will have mistake in the logic and hence will generate wrong option
        // Should include a option leading to wrong answers.
        // Each option should have a mistake in only one step of the correct solution.
        // Discard a option where wrong answer is equal to the correct answer.
        // Each option should have all information in English as well as one more language other than English.
        // This function returns 1st wrong option

        /*assigning the value of index 0 to wrongopt1 and also removing it from the vector.*/
        String wrongOpt1 = (String) v.elementAt(0);
        v.remove(0);
        return wrongOpt1;
    }



    // Change the number of parameters to this method depending on nature of your question
    public static String wrongOption2(Vector v) {

        // Write an algorithm which will have another mistake in the logic and hence will generate another wrong option
        // Should include a option leading to wrong answers.
        // Each option should have a mistake in only one step of the correct solution
        // Discard a option where wrong answer is equal to the correct answer
        // Each option should have all information in English as well as one more language other than English.
        // This function returns 2nd wrong option

        /*Assigning the value of index 0 to wrongopt2 and also removing it from the vector.*/
        String wrongOpt2 = (String) v.elementAt(0);
        v.remove(0);
        return wrongOpt2;
    }



    // Change the number of parameters to this method depending on nature of your question
    public static String wrongOption3(Vector v) {

        // Write an algorithm which will have another mistake in the logic and hence will generate another wrong option
        // Should include a option leading to wrong answers.
        // Each option should have a mistake in only one step of the correct solution.
        // Discard a option where wrong answer is equal to the correct answer.
        // Each option should have all information in English as well as one more language other than English.
        // This function returns 3rd wrong option

        /*assigning the value of index 0 to wrongopt3 and also removing it from the vector*/
        String wrongOpt3 = (String) v.elementAt(0);
        v.remove(0);
        return wrongOpt3;
    }

    // Change the number of parameters to this method depending on nature of your question
    public static String generateSolutionText(int n, int m, Vector v) {

        // This function prints the solution text
        // Each question should have automatically generated solution explanation
        //This is where you can get the correct answer by calling the generateCorrectAnswers() function instead of writing the same logic to get correct again
        int n1 = n-1;
        int n2 = n1*n/2;
        int m1 = m-1;
        int m2 = m1*m/2;
        int sub1 = n-m;

        String value1 = sub1+"a";
        int sub2 = n2 - m2;
        String value2 = sub2+"d";
        /*The following if condition is for not displaying the subtracted values as 1a or 1d rather it should print as a or d */
        if(sub1 == 1 ){
            value1 = "a";
        }
        if(sub2 == 1 ){
            value2 = "d";
        }
        String Solution = "<br>Solution:<br>Formula for $S_n = \\frac{n}{2}[2a + (n-1) \\times d]$ <br>From given data, d<0, $S_{"+n+"}\\Rightarrow n = "+n+"$ and $S_{"+m+"}\\Rightarrow n = "+m+"$<br>$\\therefore S_{"+n+"} = \\frac{"+n+"}{2}[2a + ("+n+"-1) \\times d] \\Rightarrow "+n+"a + "+n2+"d$....(i)<br>Similarly for $S_{"+m+"}, S_{"+m+"} = \\frac{"+m+"}{2}[2a + ("+m+"-1) \\times d] \\Rightarrow "+m+"a + "+m2+"d$....(ii)<br>Substracting equation (i) and (ii) we get,<br>$S_{"+n+"} - S_{"+m+"} = "+value1+"+"+value2+"$<br>Here $d$ is negative i.e every next term will be smaller than the previous term.<br>$\\therefore S_{"+n+"}$$"+generateCorrectAnswer(n,m,v)+"$$S_{"+m+"}$";
        return Solution;
    }


    // This function prints the problem statement with options
    private static String generateProblemStatementWithOptions(int n,int m, Vector v) {


        // Generate Problem Statement With Options here
        String output = "Question :<br>" + getQuestionStatement(n,m) + "<br>Correct Options :<br>$"+ generateCorrectAnswer( n,m,v) + "$<br>Wrong Options :<br>$" + wrongOption1(v) + "$<br>$" + wrongOption2(v) + "$<br>" + wrongOption3(v) ;
        return output;

    }

}
