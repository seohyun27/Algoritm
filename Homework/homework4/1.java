//22311947 김서현

class Solution {
    public int solution(int[][] triangle) {
        int size = triangle.length;
        int[][] B = new int[size][];

        for (int i = 0; i < size; i++) {
            B[i] = new int[triangle[i].length];
            System.arraycopy(triangle[i], 0, B[i], 0, triangle[i].length);
        }

        for (int i = size - 2; i >= 0; i--) { //삼각형의 높이
            for (int j = 0; j <= i; j++) { //해당 높이의 값들
                B[i][j] = B[i][j] + Math.max(B[i+1][j], B[i+1][j+1]); //바로 아래 두 값 중에 높은 값을 선택해서 현재값에 더함
            }
        }

        int answer = B[0][0];
        return answer;
    }
}
