package Sorting;

//AbstractSort 추상 클래스를 상속받는 Selection 클래스
public class Selection extends AbstractSort {
    public static void sort(Comparable[] a){
        int N = a.length;
        // N은 a 배열의 길이
        // i와 j는 a 배열의 인덱스
        for(int i = 0; i<N-1 ;i++){ //마지막 N번째 요소는 본인 혼자뿐이므로 비교할 필요 없음
            int min = i;
            for(int j=i+1;j<N;j++)
                if(less(a[j], a[min]))
                    min = j;
            //for문이 끝나고 min변수 안에 실제 최솟값이 담김
            exch(a, i, min);
        }
        if (!isSorted(a)) throw new AssertionError(); //정렬이 잘 됐는지 확인
    }

    public static void main(String[] args){
        Integer[] a = {10, 4, 5, 2, 1, 8, 3, 6};
        Selection.sort(a);
        Selection.show(a);
    }
}
