import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class prob20 {

	public static HashSet<String> permutations = new HashSet<>();

	public static void permute(String input, char[] temp, int index) {
		for (int i = 0; i < input.length(); i++) {
			temp[index] = input.charAt(i);
			if (index == 3)
				permutations.add(new String(temp));
			else
				permute(input, temp, index + 1);
		}
	}

	private static void filter(String mustContain) {
		Iterator<String> it = permutations.iterator();
		while (it.hasNext()) {
			String next = it.next();
			boolean flag = false;
			for (int i = 0; i < mustContain.length(); i++) {
				if (!next.contains("" + mustContain.charAt(i))) {
					flag = true;
				}
			}
			if (flag)
				it.remove();
			else {
				int firstTwo = Integer.parseInt(next.substring(0, 2));
				int lastTwo = Integer.parseInt(next.substring(2, 4));
				if (lastTwo * lastTwo != firstTwo)
					it.remove();
			}
		}
	}

	public static void printResults() {
		Iterator<String> it = permutations.iterator();
		if (permutations.size() == 1)
			System.out.println("CODE IS: " + it.next());
		else if(permutations.isEmpty())
			System.out.println("COULD NOT DETERMINE CODE");
		else if (permutations.size() < 4)
			while (it.hasNext())
				System.out.println("POSSIBLE MATCH: " + it.next());
		else if (permutations.size() > 3)
			System.out.println("WILL NOT WIN BET");
	}

	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(new File("prob20.txt"));
		char[][] screen = new char[4][3];

		for (int i = 0; i < screen.length; i++) {
			char[] currentLine = scan.nextLine().toCharArray();
			screen[i] = currentLine;
		}
		scan.close();

		String permuteThis = "";
		String mustContain = "";
		int no = 1;
		for (int r = 0; r < screen.length - 1; r++) {
			for (int c = 0; c < screen[r].length; c++) {
				if (screen[r][c] == 'C') {
					permuteThis += no;
					mustContain += no;
				}
				if (screen[r][c] == 'U')
					permuteThis += no;
				no++;
			}
		}

		if (screen[screen.length - 1][1] == 'C') {
			permuteThis += "0";
			mustContain += "0";
		} else if (screen[screen.length - 1][1] == 'U') {
			permuteThis += "0";
		}

		permute(permuteThis, new char[4], 0);
		filter(mustContain);
		printResults();
	}

}
