import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Scanner;

public class prob21 {

	static HashMap<Integer, String> zodiacAnimal = new HashMap<>();
	static {
		zodiacAnimal.put(2008 % 12, "Rat");
		zodiacAnimal.put(2009 % 12, "Ox");
		zodiacAnimal.put(2010 % 12, "Tiger");
		zodiacAnimal.put(2011 % 12, "Rabbit");
		zodiacAnimal.put(2012 % 12, "Dragon");
		zodiacAnimal.put(2013 % 12, "Snake");
		zodiacAnimal.put(2014 % 12, "Horse");
		zodiacAnimal.put(2015 % 12, "Sheep");
		zodiacAnimal.put(2016 % 12, "Monkey");
		zodiacAnimal.put(2017 % 12, "Rooster");
		zodiacAnimal.put(2018 % 12, "Dog");
		zodiacAnimal.put(2019 % 12, "Pig");
	}

	public static void process(LocalDate input) {
		String animal = zodiacAnimal.get(input.getYear() % 12);
		String sign = getZodiacSign(input);
		System.out.println("If you were born on " + titleCase(input.getMonth().toString()) + " " + input.getDayOfMonth()
				+ ", your sign is " + sign + ".");
		System.out.println(input.getYear() + " is the year of the " + animal + ".");
	}

	public static String getZodiacSign(LocalDate input) {
		int month = input.getMonthValue();
		int dayOfMonth = input.getDayOfMonth();
		switch (month) {
		case 3:
			return dayOfMonth <= 20 ? "Pisces" : "Aries";
		case 4:
			return dayOfMonth <= 19 ? "Aries" : "Taurus";
		case 5:
			return dayOfMonth <= 20 ? "Taurus" : "Gemini";
		case 6:
			return dayOfMonth <= 20 ? "Gemini" : "Cancer";
		case 7:
			return dayOfMonth <= 22 ? "Cancer" : "Leo";
		case 8:
			return dayOfMonth <= 22 ? "Leo" : "Virgo";
		case 9:
			return dayOfMonth <= 22 ? "Virgo" : "Libra";
		case 10:
			return dayOfMonth <= 22 ? "Libra" : "Scorpio";
		case 11:
			return dayOfMonth <= 21 ? "Scorpio" : "Sagittarius";
		case 12:
			return dayOfMonth <= 21 ? "Sagittarius" : "Capricorn";
		case 1:
			return dayOfMonth <= 19 ? "Capricorn" : "Aquarius";
		case 2:
			return dayOfMonth <= 18 ? "Aquarius" : "Pisces";
		default:
			return "UH_OH";
		}
	}

	public static String titleCase(String input) {
		return input.charAt(0) + input.substring(1).toLowerCase();
	}

	public static void main(String[] args) throws IOException, ParseException {
		Scanner scan = new Scanner(new File("prob21.txt"));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
		while (scan.hasNextLine())
			process(LocalDate.parse(scan.nextLine(), formatter));
		scan.close();
	}

}
