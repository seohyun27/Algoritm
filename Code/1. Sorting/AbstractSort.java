package Sorting;

//Sort의 추상 클래스
public abstract class AbstractSort {
    public static void sort(Comparable[] a){};
    //Comparable 타입의 배열 a를 받는 sort 추상함수
    //각 정렬 함수에서 구현

    protected static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
        //compareTo함수의 결과가 0보다 작다면 = v<w라면 true를 리턴
        //리턴 값이 true or false이므로 boolean
    }

    protected static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i]; //임시 저장 장소에 a[i] 값 집어넣어두기
        a[i] = a[j];
        a[j] = t;
    }

    protected static void show(Comparable[] a) {
        for(int i=0;i<a.length;i++)
            System.out.print(a[i]+" ");
        System.out.println();
    }

    protected static boolean isSorted(Comparable[] a){
        for(int i=1;i<a.length;i++) //첫번째 요소인 a[0]는 그 앞의 요소와 비교할 필요가 없다. i는 1부터 시작
            if(less(a[i], a[i-1])) return false;
        return true;
    }
}
