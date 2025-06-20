package alg.chap6.base;

import java.io.BufferedInputStream;
import java.io.IOException;

public class BinaryStdIn {
	private static BufferedInputStream in = new BufferedInputStream(System.in);
    private static final int EOF = -1;    // end of file

    private static int buffer;            // one character buffer
    private static int n;                 // number of bits left in buffer
    
    // don't instantiate
    private BinaryStdIn() { }
    
    // static initializer
    static {
        fillBuffer();
    }
    
    private static void fillBuffer() {
    	try {
    		buffer = in.read();
    		n = 8;
    	} catch (IOException e) {
    		System.out.println("EOF");
            buffer = EOF;
            n = -1;
    	}
    }
    
    // Close this input stream and release any associated system resources.
    public static void close() {
    	try {
            in.close();
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not close BinaryStdIn");
        }
    }
    
    // Returns true if standard input is empty.
    public static boolean isEmpty() {
    	return buffer == EOF;
    }
    
    // Reads the next bit of data from standard input and return as a boolean.
    public static boolean readBoolean() {
    	if (isEmpty())	throw new RuntimeException("Reading from empty input stream");
    	n--;
    	boolean bit = ((buffer >> n) & 1) == 1;
    	if (n == 0)
    		fillBuffer();
    	return bit;
    }
    
    // Reads the next 8 bits from standard input and return as an 8-bit char.
    public static char readChar() {
    	if (isEmpty())	throw new RuntimeException("Reading from empty input stream");
    	
    	// special case when aligned byte
    	if (n == 8) {
    		int x = buffer;
    		fillBuffer();
    		return (char) (x & 0xff);
    	}
    	
    	// combine last n bits of current buffer with first 8-n bits of new buffer
    	int x = buffer;
    	x <<= (8 - n);
    	int oldN = n;
    	fillBuffer();
    	if (isEmpty())	throw new RuntimeException("Reading from empty input stream");
    	x |= (buffer >> oldN);
    	n = oldN;
    	return (char) (x & 0xff);
    }
    
    // Reads the next r bits from standard input and return as an r-bit character.
    public static char readChar(int r) {
    	if (r < 1 || r > 16) throw new IllegalArgumentException("Illegal value of r = " + r);
    	
    	// optimize r = 8 case
    	if (r == 8)	return readChar();
    	
    	char x = 0;
    	for (int i = 0; i < r; i++) {
    		x <<= 1;
    		boolean bit = readBoolean();
    		if (bit) x |= 1;
    	}
    	return x;
    }
    
    // Reads the remaining bytes of data from standard input and return as a string. 
    public static String readString() {
    	if (isEmpty())	throw new RuntimeException("Reading from empty input stream");
    	
    	StringBuilder sb = new StringBuilder();
    	while (!isEmpty()) {
    		char c = readChar();
    		sb.append(c);
    	}
    	return sb.toString();
    }
    
    // Reads the next 16 bits from standard input and return as a 16-bit short.
    public static short readShort() {
    	short x = 0;
    	for (int i = 0; i < 2; i++) {
    		char c = readChar();
    		x <<= 8;
    		x |= c;
    	}
    	return x;
    }
    
    // Reads the next 32 bits from standard input and return as a 32-bit int.
    public static int readInt() {
    	int x = 0;
    	for (int i = 0; i < 4; i++) {
    		char c = readChar();
    		x <<= 8;
    		x |= c;
    	}
    	return x;
    }
    
    // Reads the next r bits from standard input and return as an r-bit int.
    public static int readInt(int r) {
    	if (r < 1 || r > 32) throw new IllegalArgumentException("Illegal value of r = " + r);
    	
    	// optimize r = 32 case
    	if (r == 32) return readInt();
    	
    	int x = 0;
    	for (int i = 0; i < r; i++) {
    		x <<= 1;
    		boolean bit = readBoolean();
    		if (bit) x |= 1;
    	}
    	return x;
    }
    
    // Reads the next 64 bits from standard input and return as a 64-bit long.
    public static long readLong() {
    	long x = 0;
    	
    	for (int i = 0; i < 8; i++) {
    		char c = readChar();
    		x <<= 8;
    		x |= c;
    	}
    	return x;
    }
    
    // Reads the next 64 bits from standard input and return as a 64-bit double.
    public static double readDouble() {
    	return Double.longBitsToDouble(readLong());
    }
    
    // Reads the next 32 bits from standard input and return as a 32-bit float.
    public static float readFloat() {
    	return Float.intBitsToFloat(readInt());
    }
    
    // Reads the next 8 bits from standard input and return as an 8-bit byte.
    public static byte readByte() {
    	char c = readChar();
    	byte x = (byte) (c & 0xff);
    	return x;
    }
}
