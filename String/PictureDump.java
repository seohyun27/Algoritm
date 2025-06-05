package alg.chap6.base;

import java.awt.Color;

public class PictureDump {

	public static void main(String[] args) {
		int width = Integer.parseInt(args[0]);
		int height = Integer.parseInt(args[1]);
		Picture picture = new Picture(width, height);
		for (int col = 0; col < width; col++) {
			for (int row = 0; row < height; row++) {
				picture.set(col, row, Color.RED);
				if (!BinaryStdIn.isEmpty()) {
					boolean bit = BinaryStdIn.readBoolean();
					if (bit)	picture.set(col, row, Color.BLACK);
					else		picture.set(col, row, Color.WHITE);
				}
			}
		}
		picture.show();
	}

}
