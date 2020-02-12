/**
 * File: ExpressionTester.java
 * Author: ME!
 * 
 * This file is given to you for you to add your own test cases. You will not 
 * need to turn this in, but adding test cases is only for your own benefit. 
 */
public class ExpressionTester
{
    public static void main(String[] args)
    {
        //Arithmetic expression tests
        ArithmeticExpression one = new IntegerValue(1);
        ArithmeticExpression two = new IntegerValue(2);
        ArithmeticExpression three = new IntegerValue(3);

        //1 + 2, expected 3
        Expression arith1 = new Sum(one, two);
        System.out.println("1 + 2 evaluated to: " + arith1.evaluate());

        //3 - 1, expected 2
        Expression arith2 = new Difference(three, one);
        System.out.println("3 - 1 evaluated to: " + arith2);

        //1 + (3 - 2), expected 2
        Expression arithComplex = new Sum(one, new Difference(three, two));
        System.out.println("1 + (3 - 2) evaluated to: " + arithComplex);


        //Boolean expression tests
        BooleanExpression myTrue = new BooleanValue(true);
        BooleanExpression myFalse = new BooleanValue(false);

        //True && false, expected false
        Expression bool1 = new Conjunction(myTrue, myFalse);
        System.out.println("True && false evaluated to: " + bool1);

        //Convert a non-zero IntegerValue to a boolean, expect true
        boolean nonZeroInt = new IntegerValue(1).boolEvaluate(); 
        System.out.println("IntegerValue(1) evaluated as a boolean to: " 
                                + nonZeroInt); 

        //Convert a false BooleanValue to an int, expect 0
        int falseIntValue = new BooleanValue(false).intEvaluate(); 
        System.out.println("BooleanValue(false) evaluated as an integer to: " 
                                + falseIntValue); 


        /* Add your own test cases here! */

    }
}
