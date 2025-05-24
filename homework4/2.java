//22311947 김서현

import java.util.ArrayList;
import java.util.Scanner;

public class HW2 {
    HW2(char[] X, char[] Y){
        int X_len = X.length;
        int Y_len = Y.length;
        int D[][] = new int[X_len+1][Y_len+1];
        ArrayList<Character> reverseList = new ArrayList<>();;

        for(int i = 1; i <= X_len; i++)
            for(int j = 1; j <= Y_len; j++){
                if(X[i-1]==Y[j-1])
                    D[i][j] = 1 + D[i-1][j-1];
                else
                    D[i][j] = Math.max(D[i-1][j], D[i][j-1]);
            }

        for(int i = X_len, j = Y_len; i != 0 && j != 0;) {
            if(X[i-1]==Y[j-1]){
                reverseList.add(X[i-1]);
                i--; j--;
            }
            else
                if(D[i-1][j] >= D[i][j-1])  i--;
                else j--;
        }
        for(int k = reverseList.size()-1; k >= 0; k--)
            System.out.print(reverseList.get(k));

        System.out.print("\n" + D[X_len][Y_len]);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("문자열 X : ");
        String X_input = scanner.nextLine();
        char[] X = X_input.toCharArray();

        System.out.println("문자열 Y : ");
        String Y_input = scanner.nextLine();
        char[] Y = Y_input.toCharArray();

        new HW2(X, Y);
    }
}

