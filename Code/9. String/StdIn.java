package String;

import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class StdIn {
	private static final String CHARSET_NAME = "UTF-8";
	private static final Locale LOCALE = Locale.KOREAN;
	private static final Pattern WHITESPACE_PATTERN = Pattern.compile("\\p{javaWhitespace}+");
	private static final Pattern EMPTY_PATTERN = Pattern.compile("");
	private static final Pattern EVERYTHING_PATTERN = Pattern.compile("\\A");
	private static Scanner scanner;
	
	private StdIn() { }
	
	public static boolean isEmpty() {
		return !scanner.hasNext();
	}
	
	public static boolean hasNextLine() {
		return scanner.hasNextLine();
	}
	
	public static boolean hasNextChar() {
		scanner.useDelimiter(EMPTY_PATTERN);
		boolean result = scanner.hasNext();
		scanner.useDelimiter(WHITESPACE_PATTERN);
		return result;
	}
	
	public static String readLine() {
		String line;
		try {
			line = scanner.nextLine();
		} catch (NoSuchElementException e) {
			line = null;
		}
		return line;
	}
	
	public static char readChar() {
		scanner.useDelimiter(EMPTY_PATTERN);
		String ch = scanner.next();
		assert ch.length() == 1 : "Internal (Std)In.readChar() error!";
		scanner.useDelimiter(WHITESPACE_PATTERN);
		return ch.charAt(0);
	}
	
	public static String readAll() {
		if (!scanner.hasNextLine())
			return "";
		String result = scanner.useDelimiter(EVERYTHING_PATTERN).next();
		scanner.useDelimiter(WHITESPACE_PATTERN);
		return result;
	}
	
	public static String readString() {
		return scanner.next();
	}
	
	public static int readInt() {
		return scanner.nextInt();
	}
	
	public static double readDouble() {
        return scanner.nextDouble();
    }
	
	public static float readFloat() {
        return scanner.nextFloat();
    }
	
	public static long readLong() {
        return scanner.nextLong();
    }
	
	public static short readShort() {
        return scanner.nextShort();
    }
	
	public static byte readByte() {
        return scanner.nextByte();
    }
	
	public static boolean readBoolean() {
		String s = readString();
		if (s.equalsIgnoreCase("true"))		return true;
		if (s.equalsIgnoreCase("false"))	return false;
		if (s.equals("1"))					return true;
		if (s.equals("0"))					return false;
		throw new InputMismatchException();
	}
	
	public static String[] readAllStrings() {
		String[] tokens = WHITESPACE_PATTERN.split(readAll());
		if (tokens.length == 0 || tokens[0].length() > 0)
			return tokens;
		
		// don't include first token if it is leading whitespace
		String[] decapitokens = new String[tokens.length-1];
		for (int i = 0; i < tokens.length - 1; i++)
			decapitokens[i] = tokens[i+1];
		return decapitokens;
	}
	
	public static String[] readAllLines() {
		ArrayList<String> lines = new ArrayList<String>();
		while (hasNextLine()) {
			lines.add(readLine());
		}
		return lines.toArray(new String[0]);
	}
	
	// throws InputMismatchException if any token cannot be parsed as an int
	public static int[] readAllInts() {
		String[] fields = readAllStrings();
		int[] vals = new int[fields.length];
		for (int i = 0; i < fields.length; i++)
			vals[i] = Integer.parseInt(fields[i]);
		return vals;
	}
	
	// throws InputMismatchException if any token cannot be parsed as an double
	public static double[] readAllDoubles() {
        String[] fields = readAllStrings();
        double[] vals = new double[fields.length];
        for (int i = 0; i < fields.length; i++)
            vals[i] = Double.parseDouble(fields[i]);
        return vals;
    }
	
	// do this once when StdIn is initialized
	static {
		resync();
	}
	
	private static void resync() {
		setScanner(new Scanner(new BufferedInputStream(System.in), CHARSET_NAME));
	}
	
	private static void setScanner(Scanner scanner) {
		StdIn.scanner = scanner;
		StdIn.scanner.useLocale(LOCALE);
	}
	
	public static int[] readInts() {
		return readAllInts();
	}
	
	public static double[] readDoubles() {
        return readAllDoubles();
    }
	
	public static String[] readStrings() {
        return readAllStrings();
    }
	
	public static void main(String[] args) {
		System.out.print("Type a string: ");
		String s = StdIn.readString();
		System.out.println("Your string was: " + s);
		System.out.println();
		
		System.out.print("Type an int: ");
		int a = StdIn.readInt();
		System.out.println("Your int was: " + a);
		System.out.println();
		
		System.out.print("Type a boolean: ");
		boolean b = StdIn.readBoolean();
		System.out.println("Your boolean was: " + b);
		System.out.println();
		
		System.out.print("Type a double: ");
		double d = StdIn.readDouble();
		System.out.println("Your double was: " + d);
		System.out.println();
	}
}
