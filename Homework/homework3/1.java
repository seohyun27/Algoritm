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

    //x : 행의 시작 인덱스
    //y : 열의 시작 인덱스
    //n : 배열의 크기

    void divide(int x, int y, int n, int[][] arr) { //배열 쪼개기
        if(Check(x, y, n, arr)){ //쪼갠 배열이 모두 같은 항이라면
            if(arr[x][y] == 0) zero++; //배열에 든 게 0일 때
            else one++;
            return;
        }

        divide(x, y, n/2, arr); //네 구역에서 다시 divide 호출
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
