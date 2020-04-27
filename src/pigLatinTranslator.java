import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class pigLatinTranslator {
	
	private static Scanner scnr = new Scanner(System.in);

	public static void main(String[] args) {
		welcomeMessage();
		do {
			String userString = pigLatinTranslator.getString();
			while (userString.isEmpty()) {
				System.out.println("You didn't enter any words");
				userString = pigLatinTranslator.getString();
			}
			String [] userStringArray = userString.split(" ");
			String output = "";
			for (int i = 0; i < userStringArray.length; i++) {
				String pigLatin = translateWord(userStringArray[i]);
				output += pigLatin+ " ";
			}
			if (containsSpecialCharacter(userString)) {
				System.out.println(userString);
			}
			else {
				System.out.println(capitalize(output));
			}
			//System.out.println(capitalize(output));
		} while(doesUserWantToContinue());
		System.out.println("Goodbye!");
	}
	
	public static String translateWord(String userStringArray) {
		userStringArray = userStringArray.toLowerCase();
		int vowelIndex = 0;
		char currentChar;
		
		for (int i = 0; i < userStringArray.length(); i++) {
			currentChar = userStringArray.charAt(i);
			
			if (isVowel(currentChar)) {
				vowelIndex = i;
				break;
			}
		}
		if (vowelIndex == 0) {
			return userStringArray + "way";
		}
		else {
			String afterVowel = userStringArray.substring(vowelIndex);
			String beforeVowel = userStringArray.substring(0, vowelIndex);
			return afterVowel + beforeVowel + "ay";
		}
	}
	
	public static void welcomeMessage() {
		System.out.println("Welcome to the Pig Latin Translator!");
		System.out.println();	
	}
	
	private static String getString() {
		Scanner scnr = new Scanner(System.in);
		System.out.println("Enter a line to be translated:");
		return scnr.nextLine();
	}
	
	public static boolean isVowel(char ch) {
            return (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u');
    }
	
	private static boolean doesUserWantToContinue() {
		System.out.print("Translate another line? (y/n): ");
		return scnr.next().startsWith("y");
	}
	
	public static String capitalize(String word) {
		return word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
	}
	public static boolean containsSpecialCharacter(String password) 
	{
	    Pattern digit = Pattern.compile("[0-9]");
	    Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
	    Matcher hasDigit = digit.matcher(password);
	    Matcher hasSpecial = special.matcher(password);

	    return hasDigit.find() || hasSpecial.find();
	}
}
