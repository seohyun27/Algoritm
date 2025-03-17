package Sorting;

//LSD(낮은 자리 우선 정렬)을 이용한 기수 정렬
//오로지 정수 배열일 때만
//stable한 알고리즘

public class Radix {
    public static void sort(int[] A){
        //m=가장 큰 수, exp=현재 보려하는 자리수
        int i, m = A[0], exp = 1, n = A.length;
        int B[] = new int[n]; //각 기수의 정렬 결과를 저장할 임시 배열

        for(i=1; i<n; i++)
            if(A[i] > m) m = A[i]; //한 바퀴 돌면서 제일 큰 수 찾기

        while(m/exp > 0){ //자바의 나눗셈은 소수점을 버림
            int[] C = new int[10]; //계수를 담을 배열 : 버킷 할당
            //필요한 자리를 1의 자리로 옮긴 뒤 모듈러 연산(mod 10)을 활용해 1의 자릿수만 남김
            // (A[i]/exp) % 10는 각 자리수의 기준값
            for(i=0; i<n; i++) C[(A[i]/exp) % 10]++; //계수 구하기
            for(i=1; i<10;i++) C[i] += C[i-1]; //누적 계수 구하기
            // C[(A[i]/exp) % 10는 A[i]의 누적 계수(=결과 배열의 위치)
            for (i = n - 1; i >= 0; i--) B[--C[(A[i]/exp) % 10]] = A[i];
            //구한 누적 계수로 해당 자릿수(exp)에 따른 정렬 결과를 B 임시배열에 저장
            for(i=0; i<n; i++) A[i] = B[i]; //B 임시배열의 내용을 A에 덮어쓰기

            exp *= 10; //다음 자릿수로 이동
            //한 번 정렬된 A 배열을 다음 자릿수로 옮겨 다시 정렬
            //모든 자릿수에 대한 정렬을 마치면 while문을 종료, 이때 A 배열은 완전히 정렬되어 있음

            //굳이 A 배열에 덮어쓰는 과정 없이 B와 A의 역할을 바꾸면 편리
            // But 마지막에는 반드시 A 배열에 결과가 담길 수 있도록 하는 코드를 추가해야 함
        }
    }

    public static void main(String[] args){
        int[] A = {427, 103, 54, 207, 9, 125};

        Radix.sort(A);
        for(int i = 0; i<A.length; i++) //결과 출력
            System.out.print(A[i] + " ");
        System.out.println();
    }
}
