package intechOlympiad;

public class ProblemStatement_VaibhavWani_assignment1{

        /**
         * There is an auditorium with 27 rows of seats. There are 20 seats in the first row, 22 seats in the second row, 24 seats in the third row and so on.
         * Find the number of seats in the 15th row and also find how many total seats are there in the auditorium?
         */

        // Followings are global variables to get question with options and solution
        String questionWithOptions = "";
        String solution = "";

        public static void main(String[] args) {
        // TODO Auto-generated method stub

        ProblemStatement_VaibhavWani_assignment1 call = new ProblemStatement_VaibhavWani_assignment1();

        call.run();
    }
        // This functions returns the question statement with options and solution
        public void run() {

            int totalRows=27;
            int firstTerm = 20;
            int diff = 2;
            int N = 15; //nth term

            // This functions returns the question statement with options and solution
            questionWithOptions = generateProblemStatementWithOptions(totalRows,firstTerm, diff, N);
            System.out.println(questionWithOptions);

            // This generates the solution
            solution = generateSolutionText(totalRows,firstTerm, diff, N);
            System.out.println(solution);

        }
        // Change the number of parameters to this method depending on nature of your question
        public static String getQuestionStatement(int totalRows,int firstTerm,int diff,int N) {

        // This function prints the question statement
        // Each question should have different numbers’ data and solution
        // Each question should have all information in English as well as one more language other than English.

            String suffixEn;
            switch (N){

                case 1:
                    suffixEn="st";
                    break;
                case 2:
                    suffixEn="nd";
                    break;
                case 3:
                    suffixEn="rd";
                    break;
                case 4:
                    suffixEn="th";
                    break;
                default:
                    suffixEn="th";
                    break;
            }

        String question = "There is an auditorium with $"+totalRows+"$ rows of seats. There are $"+firstTerm+"$ seats in the first row, $"+(firstTerm+diff)+"$ seats in the second row, $"+(firstTerm+2*diff)+"$ seats in the third row and so on." +
                " Find the number of seats in the $"+N+"^{"+suffixEn+"}$ row and also find how many total seats are there in the auditorium?";
        return question;
    }

        // Change the number of parameters to this method depending on nature of your question
        public static String generateCorrectAnswers(int totalRows, int firstTerm, int diff, int N) {

            int tn = firstTerm + (N - 1) * diff;
            int Sn= ((totalRows) * ( ( (2*firstTerm) + ((totalRows-1)*diff))))/ 2;

            String ans= tn+", "+Sn;
            return ans;
        // This function returns one or more correct answers
        // Each question should have at least one correct answer
        // Each option should have all information in English as well as one more language other than English.
    }
        // Change the number of parameters to this method depending on nature of your question
        public static String wrongVariation1(int totalRows, int  firstTerm, int diff, int N) {

        // Write an algorithm which will have mistake in the logic and hence will generate wrong variation
        // Should include a variation leading to wrong answers.
        // Each variation should have a mistake in only one step of the correct solution.
        // Discard a variation where wrong answer is equal to the correct answer.
        // Each option should have all information in English as well as one more language other than English.
        // This function returns 1st wrong variation

            int tn = firstTerm + (N + 1) * diff;
            int Sn= ((totalRows) * ( ( (2*firstTerm) + ((totalRows)*diff))))/ 2;

            String ans= tn+", "+Sn;
            return ans;
    }
        // Change the number of parameters to this method depending on nature of your question
        public static String wrongVariation2(int totalRows,int firstTerm, int diff, int N) {

        // Write an algorithm which will have another mistake in the logic and hence will generate another wrong variation
        // Should include a variation leading to wrong answers.
        // Each variation should have a mistake in only one step of the correct solution
        // Discard a variation where wrong answer is equal to the correct answer
        // Each option should have all information in English as well as one more language other than English.
        // This function returns 2nd wrong variation

            int tn = firstTerm + (N) * diff;
            int Sn= ((totalRows) * ( ( (2*firstTerm) + ((totalRows+1)*diff))))/ 2;

            String ans= tn+", "+Sn;
            return ans;
    }
     // Change the number of parameters to this method depending on nature of your question
        public static String wrongVariation3(int totalRows, int  firstTerm, int diff, int N) {

        // Write an algorithm which will have another mistake in the logic and hence will generate another wrong variation
        // Should include a variation leading to wrong answers.
        // Each variation should have a mistake in only one step of the correct solution.
        // Discard a variation where wrong answer is equal to the correct answer.
        // Each option should have all information in English as well as one more language other than English.
        // This function returns 3rd wrong variation

            int tn = firstTerm + (N - 1) *totalRows;
            int Sn= ((totalRows) * ( ( (2*firstTerm) + ((totalRows-1)*diff))));


            String ans= tn+", "+Sn;
            return ans;
    }

        // Change the number of parameters to this method depending on nature of your question
        public static String generateSolutionText(int totalRows,int firstTerm,int diff, int N) {

        // This function prints the solution text
        // Each question should have automatically generated solution explanation

            String suffixEn;
            switch (N){

                case 1:
                    suffixEn="st";
                    break;
                case 2:
                    suffixEn="nd";
                    break;
                case 3:
                    suffixEn="rd";
                    break;
                case 4:
                    suffixEn="th";
                    break;
                default:
                    suffixEn="th";
                    break;
            }
            int tn = firstTerm + (N - 1) * diff;
            //int Sn= (totalRows/2)*(2*firstTerm + (totalRows-1)*diff);
            int Sn= ((totalRows) * ( ( (2*firstTerm) + ((totalRows-1)*diff))))/ 2;
        String Solution = "<br>As There are $"+firstTerm+"$ seats in the first row, $"+(firstTerm+diff)+"$ seats in the second row, $"+(firstTerm+2*diff)+"$ seats in the third row," +
                "we can see here that, terms in this sequence comes out to be $"+firstTerm+", "+(firstTerm+diff)+", "+(firstTerm+2*diff)+","+(firstTerm+3*diff)+",...$" +
                "<br>This sequence is forming an AP with first term $a="+firstTerm+"$ and common difference $d="+diff+"$" +
                "<br>So, seats in $"+N+"^{"+suffixEn+"}$ row can be calculated using formula: $t_n= a+(n-1) \\times d$ " +
                "<br> Substituting values of $a, d$ and $n$ in the Formula $t_n = a + (n-1)\\times d$, we get: " +
                " $t_{"+N+"}="+ firstTerm +"+(" + N + "-1)\\times"+diff+"$ <br>$\\Rightarrow  t_{"+N+"}="+ firstTerm +"+(" + (N-1)+")\\times"+diff+"$<br>" +
                "$\\therefore t_{"+N+" }=" + tn+"$" +
                "<br>So, number of seats in the $"+N+"^{"+suffixEn+"}$ row are $"+tn+"$."+
                "<br>Total seats in auditoriam can be calculated using formula $S_{n}= \\dfrac{n}{2}[2a+(n-1)d]$" +
                "<br>$"+totalRows+"$ rows indicates that the sum to be found out is $S_{"+totalRows+"}\\Rightarrow n="+totalRows+"$" +
                "<br>$\\therefore$ by substituting values of $a, d$ and $n$ in above formula" +
                "<br>we get $S_{"+totalRows+"}=\\dfrac{"+totalRows+"}{2} [2\\times("+firstTerm+")+("+totalRows+"-1)"+diff+"]$"+
                "<br>$\\Rightarrow \\dfrac{"+totalRows+"}{2}["+(2*firstTerm)+"+ ("+(totalRows-1)+"\\times"+diff+")]= "+Sn+"$" +
                "<br>So, total seats in the auditorium are $"+Sn+"$"+
                "<br> $ \\therefore t_n= "+tn+"$ and $S_n="+Sn+"$ is the answer.";
        return Solution;
    }
        // This function prints the problem statement with options
        private static String generateProblemStatementWithOptions(int totalRows,int firstTerm,int diff, int N) {

            // Generate Problem Statement With Options here

            String output = "Question :" + getQuestionStatement(totalRows,firstTerm, diff, N) + "<br>Correct Options :<br>" + generateCorrectAnswers(totalRows,firstTerm, diff, N) + "<br>Wrong Options :<br>"
                    + wrongVariation1(totalRows,firstTerm, diff, N) + "<br>" + wrongVariation2(totalRows,firstTerm, diff, N) + "<br>" + wrongVariation3(totalRows,firstTerm, diff, N) ;
            return output;

        }
}
