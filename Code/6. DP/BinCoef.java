package DP;

public class BinCoef {
    //n이 위, k가 아래
    public int binDynamic(int n, int k){
        int B[][] = new int[n+1][k+1];

        for(int i = 0; i <= n; i++)
            for(int j = 0; j <= Math.min(i, k); j++){
                if(i==0 || i==j)
                    B[i][j] = 1;
                else
                    B[i][j] = B[i-1][j] + B[i-1][j-1];
            }

        return B[n][k];
    }
}
