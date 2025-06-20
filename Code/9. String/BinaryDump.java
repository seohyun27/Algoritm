package alg.chap6.base;

public class BinaryDump {

	public static void main(String[] args) {
		int width = 16;
		if (args.length == 1) {
			width = Integer.parseInt(args[0]);
		}
		
		int cnt;
		for (cnt = 0; !BinaryStdIn.isEmpty(); cnt++) {
			if (width == 0) {
				BinaryStdIn.readBoolean();
				continue;
			}
			else if (cnt != 0 && cnt % width == 0)
				System.out.println();
			
			if (BinaryStdIn.readBoolean())
				System.out.print("1");
			else
				System.out.print("0");
		}
		System.out.println();
		System.out.println(cnt + " bits");
	}

}
