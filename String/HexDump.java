package alg.chap6.base;

public class HexDump {

	public static void main(String[] args) {
		int width = 16;
		if (args.length == 1)
			width = Integer.parseInt(args[0]);
		
		int cnt;
		for (cnt = 0; !BinaryStdIn.isEmpty(); cnt++) {
			if (width == 0) {
				BinaryStdIn.readChar();
				continue;
			}
			if (cnt != 0 && cnt % width == 0)
				System.out.println();
			char ch = BinaryStdIn.readChar();
			System.out.printf("%02x ", ch & 0xff);
		}
		System.out.println();
		System.out.println((cnt * 8) + " bits"); 
	}
}
