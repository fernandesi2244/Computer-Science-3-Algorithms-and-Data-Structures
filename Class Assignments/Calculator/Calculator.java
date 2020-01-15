import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

/**
 * Class to model using a modified Dijkstra's Two-Stack Algorithm to evaluate a
 * mathematical expression.
 * 
 * @author Ian Fernandes
 *
 */
public class Calculator {

	/**
	 * Calculates the result of the expression using Dijkstra's Two-Stack Algorithm
	 * 
	 * Precondition: every character of expression is valid in a mathematical
	 * expression
	 * 
	 * @param expression the string representation of the mathematical expression to
	 *                   evaluate
	 * @return the result of evaluating the expression
	 */
	private static int calculate(String expression) {
		// Stacks for operands and operators
		Stack<String> operands = new Stack<>();
		Stack<Character> operators = new Stack<>();

		// Higher index = Higher precedence
		ArrayList<Character> precedenceLookup = new ArrayList<>(Arrays.asList('+', '-', '*', '/'));

		// Use scanner to scan in each component of the expression separated by a space
		Scanner scan = new Scanner(expression);

		// Follow steps on Google Classroom
		while (scan.hasNext()) {
			String currentChar = scan.next();
			if (currentChar.matches("\\d+")) { // one or more digits (operand)
				operands.push(currentChar);
			} else if (operators.isEmpty()/* || operators.peek() == '(' */) {
				operators.push(currentChar.charAt(0));
			} else if (precedenceLookup.indexOf(operators.peek()) < precedenceLookup.indexOf(currentChar.charAt(0))) {
				operators.push(currentChar.charAt(0));
			} else if (currentChar.equals("(")) {
				operators.push(currentChar.charAt(0));
			} else if (currentChar.equals(")")) {
				// Keep repeating "process" until '(' found
				while (operators.peek() != '(') {
					int operand1 = Integer.parseInt(operands.pop());
					int operand2 = Integer.parseInt(operands.pop());
					char operator = operators.pop();

					int result = compute(operand1, operand2, operator);
					operands.push(Integer.toString(result));
				}
				operators.pop(); // Remove (
			} else {
				// "Process"
				int operand1 = Integer.parseInt(operands.pop());
				int operand2 = Integer.parseInt(operands.pop());
				char operator = operators.pop();

				int result = compute(operand1, operand2, operator);
				operands.push(Integer.toString(result));
			}
		}

		scan.close();

		// "Process" for all elements left on the stacks
		while (operands.size() > 1) {
			int operand1 = Integer.parseInt(operands.pop());
			int operand2 = Integer.parseInt(operands.pop());
			char operator = operators.pop();

			int result = compute(operand1, operand2, operator);
			operands.push(Integer.toString(result));
		}

		// Return the result of the evaluation
		return Integer.parseInt(operands.peek());
	}

	/**
	 * Evaluates the result of applying the operator to operand1 and operand2
	 * 
	 * @param operand1 in reality, this is the second operand (due to the way the
	 *                 operands were popped off)
	 * @param operand2 in reality, this is the first operand (due to the way the
	 *                 operands were popped off)
	 * @param operator the mathematical operator
	 * @return the result of: operator2 operator operator1
	 */
	private static int compute(int operand1, int operand2, char operator) {
		try {
			if (operator == '+') {
				return operand1 + operand2;
			} else if (operator == '-') {
				return operand2 - operand1;
			} else if (operator == '*') {
				return operand1 * operand2;
			} else if (operator == '/') {
				return operand2 / operand1;
			} else {
				throw new IllegalArgumentException("Invalid operator detected! Please correct and try again.");
			}
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		return -1;
	}

	/**
	 * Pass each expression up to calculate() to evaluate the result.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String expression = "( 2 + 3 ) * 6"; // 30
		String expression2 = "2 + 3 * 6"; // 20

		// Todo: Determine logic error in calculate() with the following expression.
		// String expression3 = "( 5 / 2 + 3 ) * 2"; // 10

		int expression1Result = calculate(expression);
		int expression2Result = calculate(expression2);
		// int expression3Result = calculate(expression3);

		System.out.println(
				"The result of evaluating the following expression: " + expression + " is " + expression1Result + ".");
		System.out.println(
				"The result of evaluating the following expression: " + expression2 + " is " + expression2Result + ".");
		/*
		 * System.out.println( "The result of evaluating the following expression: " +
		 * expression3 + " is " + expression3Result + ".");
		 */

	}
}
