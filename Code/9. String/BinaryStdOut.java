package alg.chap6.base;

import java.io.BufferedOutputStream;
import java.io.IOException;

public class BinaryStdOut {
	private static BufferedOutputStream out = new BufferedOutputStream(System.out);
	private static int buffer;	// 8-bit buffer of bits to write out
	private static int n;		// number of bits remaining in buffer
	
	// don't instantiate
    private BinaryStdOut() { }
    
    // write out any remaining bits in buffer to standard output, padding with 0s
    private static void clearBuffer() {
    	if (n == 0)	return;
    	if (n > 0)	buffer <<= (8 - n);
    	try {
    		out.write(buffer);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	n = 0;
    	buffer = 0;
    }
    
    // Write the specified bit to standard output.
    private static void writeBit(boolean bit) {
    	buffer <<= 1;
    	if (bit)	buffer |= 1;
    	
    	n++;
    	if (n == 8)	clearBuffer();
    }
    
    // Write the 8-bit byte to standard output.
    private static void writeByte(int x) {
    	assert x >= 0 && x < 256;
    	if (n == 0) {
    		try {
    			out.write(x);
    		} catch (IOException e) {
        		e.printStackTrace();
        	}
        	return;
    	}
    	
    	for (int i = 0; i < 8; i++) {
    		boolean bit = (x >> (8 - i - 1) & 1) == 1;
    		writeBit(bit);
    	}
    }
    
    public static void flush() {
    	clearBuffer();
    	try {
    		out.flush();
    	} catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void close() {
    	flush();
    	try {
    		out.close();
    	} catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Write the specified bit to standard output.
    public static void write(boolean x) {
    	writeBit(x);
    }
    
    // Write the 8-bit byte to standard output.
    public static void write(byte x) {
    	writeByte(x);
    }
    
    // Write the 32-bit int to standard output.
    public static void write(int x) {
    	writeByte((x >> 24) & 0xff);
    	writeByte((x >> 16) & 0xff);
    	writeByte((x >> 8) & 0xff);
    	writeByte(x & 0xff);
    }
    
    // Write the r-bit int to standard output.
    public static void write(int x, int r) {
    	if (r == 32) {
    		write(x);
    		return;
    	}
    	
    	if (r < 1 || r > 32)
    		throw new IllegalArgumentException("Illegal value for r = " + r);
    	if (x < 0 || x >= (1 << r))
    		throw new IllegalArgumentException("Illegal " + r + "-bit char = " + x);
    	
    	for (int i = 0; i < r; i++) {
    		boolean bit = (x >> (r - i - 1) & 1) == 1;
    		writeBit(bit);
//    		if (bit) System.out.print("1");
//    		else System.out.print("0");
    	}
    }
    
    // Write the 64-bit double to standard output.
    public static void write(double x) {
    	write(Double.doubleToRawLongBits(x));
    }
    
    // Write the 64-bit long to standard output.
    public static void write(long x) {
    	for (int i = 56; i >= 0; i -= 8)
    		writeByte((int) (x >> i) & 0xff);
    }
    
    // Write the 32-bit float to standard output.
    public static void write(float x) {
    	write(Float.floatToRawIntBits(x));
    }
    
    // Write the 16-bit int to standard output.
    public static void write(short x) {
    	writeByte((x >> 8) & 0xff);
    	writeByte(x & 0xff);
    }
    
    // Write the 8-bit char to standard output.
    public static void write(char x) {
    	if (x < 0 || x >= 256) 
    		throw new IllegalArgumentException("Illegal 8-bit char = " + x);
    	writeByte(x);
    }
    
    // Write the r-bit char to standard output.
    public static void write(char x, int r) {
    	if (r == 8) {
    		write(x);
    		return;
    	}
    	if (r < 1 || r > 16)
    		throw new IllegalArgumentException("Illegal value for r = " + r);
    	if (x >= (1 << r))
    		throw new IllegalArgumentException("Illegal " + r + "-bit char = " + x);
    	for (int i = 0; i < r; i++) {
    		boolean bit = ((x >> (r - i - 1)) & 1) == 1;
    		writeBit(bit);
    	}
    }
    
    // Write the string of 8-bit characters to standard output.
    public static void write(String x) {
    	for (int i = 0; i < x.length(); i++)
    		writeByte(x.charAt(i));
    }
    
    // Write the String of r-bit characters to standard output.
    public static void write(String x, int r) {
    	for (int i = 0; i < x.length(); i++)
    		write(x.charAt(i), r);
    }
 
    /*
    public static void main(String[] args) {
		Scanner sc = new Scanner(System.in); 
		for (int i = 0; i < 5; i++) {
			System.out.print("���� �Է�: ");
    	
    		int T = sc.nextInt();
    		BinaryStdOut.write(T);
    	}
		BinaryStdOut.flush();
    	sc.close();
    }
    */
}
