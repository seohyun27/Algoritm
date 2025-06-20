package DP;

public class ShortPath {
    public static void floyd(int n, int[][] W, int[][] D){
        int i, j, k;
        for(i=1; i<=n; i++)
            for(j=1; j<=n; j++) { //최단 경로 배열 입력 배열로 초기화
                D[i][j] = W[i][j];
            }

        //배열 값이 0부터 시작
        for(k=1; k<=n; k++)
            for(i=1; i<=n; i++)
                for(j=1; j<=n; j++)
                    D[i][j] = Math.min(D[i][k]+D[k][j], D[i][j]); //거쳐가기 vs 직항하기
    }

    public static void floyd(int[][] W, int[][] D, int[][] P){ //최단 경로를 구하기 위한 P 배열이 추가
        int i, j, k;

        //배열 값이 0부터 시작
        for(i=0; i<W.length; i++)
            for(j=0; j<W.length;j++){
                D[i][j] = W[i][j];
                P[i][j] = -1;
            }

        for(k=0; k<W.length; k++) //경유하기
            for(i=0; i<W.length; i++)
                for(j=0; j<W.length;j++)
                    if(D[i][k]+D[k][j] < D[i][j]){ //경유가 더 짧다면
                        D[i][j] = D[i][k]+D[k][j];
                        P[i][j] = k; //어디로 경유했는지 표기
                    }

    }

    public void path(int[][] P, int i, int j){
        System.out.print(i + " ");
        stopover(P, i, j); //경유지 출력
        System.out.println(j);
    }

    private void stopover(int[][] P, int i, int j){
        //배열 값이 0부터 시작
        if(P[i][j] != -1) {
            stopover(P, i, P[i][j]); //경유지로 돌아가기
            System.out.print(P[i][j]  + " ");
            stopover(P, P[i][j], j);
        }
    }
}
