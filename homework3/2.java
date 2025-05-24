//22311947 김서현

import java.util.ArrayList;
import java.util.Scanner;

public class HW2 {
    ArrayList<Integer> subset = new ArrayList<>();
    int n, k;

    public HW2(int n, int k) {
        this.n = n;
        this.k = k;

        makeSubset(1, 0);
    }

    public void makeSubset(int start, int size) {
        if (size == k) {
            System.out.print(subset + " ");
            return;
        }

        for (int i = start; i <= n; i++) {
            subset.add(i);
            makeSubset(i + 1, size + 1);
            subset.remove(subset.size() - 1);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("정수 n과 k를 입력? ");
        String inputLine = scanner.nextLine();
        String[] inputs = inputLine.split(" ");
        int num_n = Integer.parseInt(inputs[0]);
        int num_k = Integer.parseInt(inputs[1]);
        new HW3(num_n, num_k);
    }
}
