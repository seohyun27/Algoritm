package DP;

public class SubList {
    public double maxSubList(double A[], int n){
        double B[] = new double[n]; //최댓값 배열
        double max = A[0];

        B[0] = A[0];
        for(int i = 1; i<n; i++) {
            if (B[i - 1] < 0)
                B[i] = A[i];
            else
                B[i] = B[i - 1] + A[i];

            if (max < B[i]) //max값 바로 업데이트
                max = B[i];
        }
        return max;
    }

    public void printMaxSubList(double A[], int n){
        double B[] = new double[n]; //최댓값 배열
        int C[] = new int[n]; //출력 배열

        double max = A[0];
        int start = 0, end = 0;

        B[0] = A[0];
        C[0] = 0;

        for(int i = 1; i<n; i++) {
            if (B[i-1] > 0) {
                B[i] = B[i-1] + A[i];
                C[i] = C[i-1];
            }
            else {
                B[i] = A[i];
                C[i] = i;
            }

            if (max < B[i]) { //max값 바로 업데이트
                max = B[i];
                start = C[i];
                end = i;
            }
        }

        for(int i = start; i<=end; i++)
            System.out.print(A[i] + " ");

        System.out.print("\n최대 길이 : " + max);
    }
}
