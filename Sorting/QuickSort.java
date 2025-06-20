package Sorting;

public class QuickSort extends AbstractSort {
    public static void sort(Comparable[] a){
        quicksort(a, 0, a.length-1);
    }

    private static void quicksort(Comparable[] a, int low, int high){
        if(high > low){
            int pivotpoint = partition(a, low, high);
            quicksort(a, low, pivotpoint-1);
            quicksort(a, pivotpoint+1, high);
        }
    }

    private static int partition(Comparable[] a, int low, int high){
        //비교군의 위치(low)는 옮기지 않음

        Comparable pivotitem = a[low]; //비교 기준 담아두기
        int j = low;
        for(int i = low + 1; i<= high; i++)
            if(less(a[i], pivotitem)){ //기준보다 i 위치의 값이 작다면
                j += 1;
                exch(a, i, j); //j와 i의 값 뒤바꾸기
            }
        //for문이 끝나면 j의 앞에는 기준보다 작은 값들만 존재

        int pivotpoint = j;
        exch(a, low, pivotpoint); //j와 low(비교군의 위치)의 값 뒤바꾸기
        return pivotpoint; //배열을 나눈 중앙값을 리턴
    }
}
