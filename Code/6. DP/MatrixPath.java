package DP;

public class MatrixPath {
    public int maxPath(int A[][], int n){
        int B[][] = new int[n][n]; //결과 배열

        B[0][0] = A[0][0];

        for(int i = 1; i<n; i++) {
            B[i][0] = B[i-1][0] + A[i][0];
            B[0][i] = B[0][i-1] + A[0][i];
        }

        for(int i = 1; i < n; i++)
            for(int j = 1; j < n; j++)
                B[i][j] = Math.max(B[i-1][j], B[i][j-1]) + A[i][j];

        return B[n-1][n-1];
    }
}
