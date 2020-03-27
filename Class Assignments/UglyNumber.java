import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class UglyNumber {

	static ArrayList<Integer> uglyNumbers = new ArrayList<>();
	static int currentNo = 1;

	private static void findNthUglyNumber(int n) {
		while (uglyNumbers.size() < n) {
			if (isUglyNumber(currentNo))
				uglyNumbers.add(currentNo);
			currentNo++;
		}

		System.out.println(uglyNumbers.get(n - 1));
	}

	private static boolean isUglyNumber(int no) {
		ArrayList<Integer> primeFactors = new ArrayList<>();

		while (no % 2 == 0) {
			primeFactors.add(2);
			no /= 2;
		}

		for (int i = 3; i <= Math.sqrt(no); i += 2) {
			while (no % i == 0) {
				primeFactors.add(i);
				no /= i;
			}
		}

		if (no > 2)
			primeFactors.add(no);

		ArrayList<Integer> primesAllowed = new ArrayList<>(Arrays.asList(2, 3, 5));
		primeFactors.removeAll(primesAllowed);

		return primeFactors.size() == 0;
	}

	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(new File("uglyNumbers.dat"));
		while (scan.hasNextInt()) {
			int n = scan.nextInt();
			findNthUglyNumber(n);
		}

		scan.close();
	}

}
