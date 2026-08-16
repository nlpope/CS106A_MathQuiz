/***
 * File: CS106A_MathQuiz.java
 * ----------------------------------
 * The CS106A_MathQuiz class provides the client
 * with 5 second grade level math questions. If  
 * incorrect, the class gives the client two more 
 * attempts before moving on to the next question.
 */

import acm.program.*;
import acm.util.*;
import java.util.*;

public class CS106A_MathQuiz extends ConsoleProgram
{	
	/** Named constants */
	private final static String s1 = "Well done! Is your name Albert by chance?";
	private final static String s2 = "Correct!";
	private final static String s3 = "Amazing! Good work.";
	private final static String s4 = "That's the answer!";
	private final static String s5 = "You got it!";
	
	public void run()
	{
		winArray.add(s1);
		winArray.add(s2);
		winArray.add(s3);
		winArray.add(s4);
		winArray.add(s5);
		println("Welcome to Math Quiz.");
		beginQuiz();
	}
	
	/**
	 * Starts the quiz by first populating its various resulting
	 * messages to the client and setting up loops to ensure 5 
	 * questions are asked and three attempts are given for each.
	 * */
	private void beginQuiz()
	{	
		String problemRetry = "That's incorrect - try a different answer: ";
		String problemFail = "No, the answer is ";
		
		for (int i = 0; i < 5; i++){
			int n = rgen.nextInt(0,winArray.size()-1);
			String problemSuccess = winArray.get(n);
			String initialProblem = "What is " + generateRandomOperation() + "? ";
			int tries = 3;
			
			while(true){
				int userAnswer = readInt((tries < 3) ? problemRetry : initialProblem);
				tries--;
				if (analyzeAnswer(userAnswer)) { println(problemSuccess); break; }
				else if (tries < 1) { println(problemFail + expectedAnswer); break; }
			}
			
			println();
		}
		println("Well done, padewon! Until next time.");
	}
	
	
	/**
	 * Generates a random math expression with one operator and two operands.
	 * @return The math problem to be presented to the user as a String
	 * */
	private String generateRandomOperation()
	{
		r1 = rgen.nextInt(0, 10);
		r2 = rgen.nextInt(0, 10);
		operator = rgen.nextBoolean() ? "+" : "-";
		
		if (r1 + r2 > 20 || r1 - r2 < 0) generateRandomOperation();
		
		return r1 + " " + operator + " " + r2;
	}
	
	
	/**
	 * Analyzes the user's answer compared against the expected answer.
	 * @return A boolean value that determines whether the user's answer
	 * matches the expected answer.
	 * */
	private boolean analyzeAnswer(int ans)
	{
		boolean result;
		expectedAnswer = operator.equals("+") ? r1 + r2 : r1 - r2;
		if (ans == expectedAnswer) result = true;
		else result = false;
		return result;
	}
	
	/** Private instance variables */
	private int r1;
	private int r2;
	private String operator;
	private int expectedAnswer;
	private ArrayList<String> winArray = new ArrayList<String>();
	private static RandomGenerator rgen = RandomGenerator.getInstance();
}