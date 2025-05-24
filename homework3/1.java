//22311947 김서현

class Solution {
    int zero = 0;
    int one = 0;

    public int[] solution(int[][] arr) {
        int n = arr.length;
        divide(0, 0, n, arr);

        int[] answer = {zero, one};
        return answer;
    }

    void divide(int x, int y, int n, int[][] arr) {
        if(Check(x, y, n, arr)){
            if(arr[x][y] == 0) zero++;
            else one++;
            return;
        }

        divide(x, y, n/2, arr);
        divide(x+n/2, y, n/2, arr);
        divide(x, y+n/2, n/2, arr);
        divide(x+n/2, y+n/2, n/2, arr);
    }

    boolean Check(int x, int y, int n, int[][] arr){
        int standard = arr[x][y];
        for(int i = x; i< x+n; i++)
            for(int j = y; j < y+n; j++)
                if(arr[i][j] != standard) return false;
        return true;
    }
}
