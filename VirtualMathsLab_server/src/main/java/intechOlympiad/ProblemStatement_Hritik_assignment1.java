package intechOlympiad;

import java.io.IOException;
import java.text.DecimalFormat;

public class ProblemStatement_Hritik_assignment1
{
    /**
     *There are 37 terms in an Arithmetic Progression The sum of three terms placed exactly at the middle is 225
     * and the sum of the last three terms is 429. Write the Arithmetic Progression
     */

    // Followings are global variables to get question with options and solution
    String questionWithOptions = "";
    String solution = "";

    public static void main(String[] args) throws IOException {

        ProblemStatement_Hritik_assignment1 javaFile = new ProblemStatement_Hritik_assignment1();

        javaFile.run();
    }


    // This functions returns the question statement with options and solution
    public void run() {

        int N = 37;
        int sum_mid=225;
        int sum_last=429;

        int sum1=N/2+(N/2 +1)+(N/2 +2)-3;    //it is the total of coeff. of d of middle terms...-3 is because of (n-1)
        int sum2=N+(N-1)+(N-2)-3;            //it is the total of coeff. of d of last terms...-3 is because of (n-1)
        int d=(sum_last-sum_mid)/(sum2-sum1);
        int a=(sum_mid-sum1*d)/3;

        // This generates problem statement with all the options
        questionWithOptions = generateProblemStatementWithOptions(N, sum_mid, sum_last, a , d );
        System.out.println(questionWithOptions);

        // This generates the solution
        solution = generateSolutionText(N, sum_mid, sum_last, a , d, sum1,sum2);
        System.out.println(solution);

    }

    // This function prints the question statement
    public static String getQuestionStatement(int N, int sum_mid, int sum_last,int a ,int d) {
        String question = "There are "+N+" terms in an Arithmetic Progression. The sum of three terms placed exactly at the middle is "+sum_mid +
                " and the sum of the last three terms is "+sum_last+". Write the Arithmetic Progression.<br>";
        return question;
    }


    // This function returns one or more correct answers
    public static String generateCorrectAnswers(int N, int sum_mid, int sum_last,int a ,int d) {

        String seq ="$"+a+", "+(a+d)+"," +(a+2*d)+", "+(a+3*d)+", " +(a+4*d)+",....$";

        return seq;
    }


    // This function returns 1st wrong variation
    public static String wrongVariation1(int N, int sum_mid, int sum_last,int a ,int d) {

        // Write an algorithm which will have mistake in the logic and hence will generate wrong variation

        // This is mistake in wrong variation
        //this is wrong variation, if we take a as it is and d is 1 less than actual value
        String wrongOpt1 = "$"+a+", "+(a+d-1)+"," +(a+2*(d-1))+", "+(a+3*(d-1))+", " +(a+4*(d-1))+",....$";

        return wrongOpt1;
    }


    // This function returns 2nd wrong variation
    public static String wrongVariation2(int N, int sum_mid, int sum_last,int a ,int d) {

        int sum1=(N/2-1)+N/2+(N/2 +1)-3;                                //algorithm to generate answer if we take middle numbers as 1 less than actual value.
        int sum2=N+(N-1)+(N-2)-3;
        float d1= (float) (1.0*(sum_last-sum_mid)/(sum2-sum1));
        d1= (float) (Math.round(d1 * 10) / 10.0);
        float a1=(sum_mid-sum1*d1)/3;
        a1= (float) (Math.round(a1 * 10) / 10.0);

        DecimalFormat dformat= new DecimalFormat("#.##");  //this code block will print only one digit after decimal
        String ap2=dformat.format(a1+d1);
        String ap3=dformat.format(a1+2*d1);
        String ap4=dformat.format(a1+3*d1);
        String ap5=dformat.format(a1+4*d1);

        String wrongOpt2 ="$"+a1+", "+ap2+"," +ap3+", "+ap4+", " +ap5+",....$";
        return  wrongOpt2;
    }


    // This function returns 3rd wrong variation
    public static String wrongVariation3(int N,int sum_mid,int sum_last,int a ,int d) {

        //wrong answer if we take a as twice a and correct d.
        String wrongOpt3 = "$"+2*a+", "+(2*a+d)+"," +(2*a+2*d)+", "+(2*a+3*d)+", " +(2*a+4*d)+",....$";
        return wrongOpt3;
    }


    // This function prints the solution text
    public static String generateSolutionText(int N, int sum_mid, int sum_last,int a ,int d, int sum1,int sum2)
    {
        float mid=N*1.0F/2;

        int a_coff1=3; int a_coff2=3;   //self declared
        int a1=a_coff1;int b1=sum1;int c1=sum_mid;     // this algorithm will reduce the coefficient by a divisor which willable to divide all coefficient
        int a2=a_coff2;int b2=sum2;int c2=sum_last;
        for(int k=a_coff1;k>=1;k--) { if((b1%k==0)&&(c1%k==0)) { a1=a1/k;b1=b1/k;c1=c1/k; } }
        for(int k=a_coff2;k>=1;k--) { if((b2%k==0)&&(c2%k==0)) { a2=a2/k;b2=b2/k;c2=c2/k; } }

        String a1_str=Integer.toString(a1);    String b1_str=Integer.toString(b1);   String c1_str=Integer.toString(c1);
        String a2_str=Integer.toString(a2);    String b2_str=Integer.toString(b2);   String c2_str=Integer.toString(c2);

        if(a1==1) { a1_str=""; }          //this algorithm prints nothing if the coefficient is 1
        if(b1==1) { b1_str=""; }
        if(c1==1) { c1_str=""; }

        if(a2==1) {  a2_str=""; }
        if(b2==1) {  b2_str=""; }
        if(c2==1) {  c2_str=""; }

        String Solution = "<br>Solution : As per given information, sum of middle three terms is $"+sum_mid+"$ and sum of last three terms is $"+sum_last
                +"$<br>Here, the middle term of $t_{"+N+"}$ is $t_{"+(N/2+1)+"}$ and hence the middle $3$ terms are $t_{"+(N/2)+"},t_{"+(N/2+1)+"},t_{"+(N/2+2)+"}$ " +
                "and the last three terms will be $t_{"+(N-2)+"},t_{"+(N-1)+"},t_{"+N+"}$.<br>Using $t_n=a+(n-1)d$ " +
                "we get<br>"+"$t_{"+N/2+"} +t_{"+(N/2+1)+"}+t_{"+(N/2+2)+"} ="+sum_mid+"$<br>$\\therefore a+"+(N/2-1)+"d+a+"+(N/2)+"d+a+"+(N/2+1)+"d="+sum_mid+
                "$<br>$\\therefore 3a+"+sum1+"d="+sum_mid+"\\Rightarrow "+a1_str+"a+"+b1_str+"d="+c1_str+"  ....(i)$"+
                "<br>Also, $t_{"+(N-2)+"} +t_{"+(N-1)+"}+t_{"+(N)+"}="+sum_last+"$<br>$\\therefore a+"+(N-3)+"d+a+"+(N-2)+"d+a+"+(N-1)+"d="+sum_last+
                "$<br>$\\therefore 3a+"+sum2+"d="+sum_last+"\\Rightarrow "+a2_str+"a+"+b2_str+"d="+c2_str+"  ....(ii)$"+
                "<br>By solving $(i)$ and $(ii)$, we get<br> $d="+d+"$ and $a="+a+"$.<br>So, the AP is "+generateCorrectAnswers(N,sum_mid,sum_last,a,d);

        return Solution;
    }


    // This function prints the problem statement with options
    private static String generateProblemStatementWithOptions(int N, int sum_mid, int sum_last,int a ,int d) {

        String output = "Question :" + getQuestionStatement(N, sum_mid, sum_last, a , d) + "<br>Correct Options :<br>" + generateCorrectAnswers(N, sum_mid, sum_last, a , d) + "<br>Wrong Options :<br>"
                + wrongVariation1(N, sum_mid, sum_last, a , d) + "<br>" + wrongVariation2(N, sum_mid, sum_last, a , d) + "<br>" + wrongVariation3(N, sum_mid, sum_last, a , d) ;
        return output;

    }

}
