import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class prob19 {
	
	static String addString;

	public static int getKey(String password) {
		int key = 0;
		for (int i = 0; i < password.length(); i++)
			key += (i % 2 == 0) ? +password.charAt(i) : -password.charAt(i);
		key = fixKey(key);
		return key;
	}

	private static int fixKey(int key) {
		if (key < 32)
			return fixKey(key + 32);
		if (key > 126)
			return fixKey(key - 16);
		return key;
	}

	public static void encrypt(String word, int key) {
		addString="";
		word.chars().map(x -> x * key).forEach(x -> addString+=Integer.toString(x, 16)+",");
		System.out.println(addString.substring(0,addString.length()-1));
	}

	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(new File("prob19.txt"));
		String password = scan.nextLine();
		int key = getKey(password);
		System.out.println("Key = "+key);
		while (scan.hasNextLine()) {
			encrypt(scan.nextLine(), key);
		}
		scan.close();

	}

}
