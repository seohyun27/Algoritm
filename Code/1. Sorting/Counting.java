package Sorting;

//키 값의 범위를 알 때 사용가능한 정렬 알고리즘
//범위가 오직 정수일 때만 사용가능 = Comparable인터페이스 사용X(AbstractSort 상속X)
public class Counting {
    //int 배열을 리턴하는 sort 함수
    public static int[] sort(int[] A, int K) {
        int i, N=A.length;
        int[] C = new int[K]; //계수를 저장할 C배열
        int[] B = new int[N]; //결과를 저장할 B배열

        for(i=0; i<N; i++) C[A[i]]++; //C배열에 계수 저장
        for(i=1; i<K; i++) C[i] += C[i-1]; //이전 값과 현재 값을 더해 각 값의 누적 개수(위치) 저장
        for(i=N-1; i>=0; i--) B[--C[A[i]]] = A[i];
        //N-1부터 인덱스를 줄여가며 결과 배열 B에 값을 정렬함
        //위치 C[A[i]]을 줄이면서 삽입 위치를 정한다
        //중복 값을 가진 애들도 기본 순서를 유지한다 = stable한 알고리즘
        return B;
    }

    public static void main(String[] args){
        int[] A = {10, 4, 5, 8, 1, 8, 3, 6};
        int[] B; //결과를 받기 위한 배열도 생성해둠

        B = Counting.sort(A, 11);
        for(int i = 0; i<B.length; i++) //결과 출력
            System.out.print(B[i] + " ");
        System.out.println();
    }
}
