/***Calculator Program. processes ()*+-/for integers.  All input separated by spaces. 
 * File input disabled.
 * 
 */

import java.io.*;
import java.util.*;

public class Calc {
	//input  operator and operand stacks.  Does not process () i.e. they need to be not in operator stack.  
	// pops operands and pushes result back on stack, then returns the calculaton.
	static int dumpStacks(Stack<Character> ops, Stack<Integer> nums) {
		// reverse since math is left to right
		Collections.reverse(ops);
		Collections.reverse(nums);
		//System.out.println(nums);
		int ans = 0;
		//this means no operators left
		if(nums.size()==1)
			return nums.pop();
		//if their are operators left, process them
		while (!ops.isEmpty()) {
			char op = ops.pop();
			int a = nums.pop();
			int b = nums.pop();
			nums.push(compute(op, b, a));

		}
		return nums.pop();

	}
//find operator precedence
	static int prec(char a) {
		if (a == '+' || a == '-' ) //|| a == '(
			return 0;
		if (a == '*' || a == '/')
			return 1;
		return 2;
	}
//computes binary operations
	static int compute(char o, int last, int first) {
		if (o == '*')
			return first * last;
		if (o == '/')
			return last / first;
		if (o == '+')
			return first + last;
		if (o == '-')
			return first - last;
		return -100;

	}

	public static void main(String[] args) throws IOException {
		// Scanner scan = new Scanner(new File("calc.dat"));
		// String str = scan.nextLine();
		String testInp = "( 1 ) - ( ( ( 3 + -8 ) * 1 * 5 ) * 2 )";
		String[] arr = testInp.split("\\s+");
		 //this stack stores operators
		Stack<Character> ops = new Stack();
		//this stack store number values
		Stack<Integer> nums = new Stack();
		for (int i = 0; i < arr.length; i++) {
			//if a number push to nums
			if (arr[i].matches("[\\d]+|-\\d+")) {
				nums.push(Integer.parseInt(arr[i]));
				//System.out.println(arr[i]);
			}
			//no handle the operators
			else if (arr[i].charAt(0) == '(') {
				ops.push('(');
			} 
			else if (arr[i].equals("-") || arr[i].equals("+")) {
				ops.push(arr[i].charAt(0));
			} 
			else if (arr[i].equals("/") || arr[i].equals("*")) {
				if (ops.empty())
					nums.push(compute(arr[i].charAt(0), nums.pop(), Integer.parseInt(arr[++i])));
				else if (prec(arr[i].charAt(0)) < prec(ops.peek())) {
					ops.push(arr[i].charAt(0));

				} else {
					// System.out.println("charAT(0"+arr[i].charAt(0)+" numps.pop "+nums.pop());
					nums.push(compute(arr[i].charAt(0), nums.pop(), Integer.parseInt(arr[++i])));

				}

			}
			// when reaching a ) process the stacks up to '('
			else if (arr[i].charAt(0) == ')') {
				//these mini stacks are sent to dumpStacks to process
				Stack<Character> pOps = new Stack();
				Stack<Integer> pNums = new Stack();
				while (ops.peek() != '(') {
					pOps.push(ops.pop());
					pNums.push(nums.pop());
					
				}
				pNums.push(nums.pop());
				//System.out.println(pOps+ "pOps"+ " pNums: "+pNums );
				//this discards the remaining (
				ops.pop();
				Collections.reverse(pOps);
				Collections.reverse(pNums);
				//now process the stacks
				int toPush = dumpStacks(pOps, pNums);
				nums.push(toPush);

			}

		}

		System.out.println("this was the input: "+ testInp);
		System.out.println(dumpStacks(ops, nums)+  " THis is the answer");

	}

}
