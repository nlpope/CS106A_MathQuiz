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
	public void run()
	{
		println("Welcome to Math Quiz.");
		beginQuiz();
	}
	
	
	private void beginQuiz()
	{	
		String problemRetry = "That's incorrect - try a different answer: ";
		String problemFail = "No, the answer is ";
		String problemSuccess = "That's the answer!";
		
		for (int i = 0; i < 5; i++){
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
	
	
	private String generateRandomOperation()
	{
		r1 = rgen.nextInt(0, 10);
		r2 = rgen.nextInt(0, 10);
		operator = rgen.nextBoolean() ? "+" : "-";
		
		if (r1 + r2 > 20 || r1 - r2 < 0) generateRandomOperation();
		
		return r1 + " " + operator + " " + r2;
	}
	
	
	private boolean analyzeAnswer(int ans)
	{
		boolean result;
		expectedAnswer = operator.equals("+") ? r1 + r2 : r1 - r2;
		if (ans == expectedAnswer) result = true;
		else result = false;
		return result;
	}
	
	//ask 5 questions ea. coded as a named const. so it can be changed
	//ea q must consist of a single add or subt (chosen randomly). problem 
	//...involving just 2 nums
	//no num or answer should be less < 0 or > than 20 (2nd grade)
	//give client 3 tries. move on to nxt q if 3 tries attempted.
	
	
	
	/** Private instance variables */
	private int r1;
	private int r2;
	private String operator;
	int expectedAnswer;
	private static RandomGenerator rgen = RandomGenerator.getInstance();
	private ArrayList<String> qArray = new ArrayList<String>();
}