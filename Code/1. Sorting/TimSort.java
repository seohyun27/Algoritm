package Sorting;

public class TimSort extends AbstractSort {
    private static final int RUN = 32; // 기본 런의 크기

    // Insertion sort
    //배열의 lo부터 hi까지 영역을 순회
    //제한된 영역 안에서 insertion sort를 실행
    private static void insertion(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            for (int j = i; j > lo && less(a[j], a[j-1]); j--) {
                exch(a, j, j-1);
            }
        }
    }

    // merge sort
    //작은 크기의 Run들을 병합한 후, 점점 더 큰 크기의 Run들을 병합합
    //각 병합 단계에서 배열 크기의 2배씩 처리
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi){
        //이미 정렬된 두 배열을 a[]에 담아 merge

        for(int k = lo; k<=hi; k++)
            aux[k] = a[k]; //임시 배열에 a[]의 내용을 복사, aux를 보고 a에 정렬 결과를 정리

        int i = lo, j = mid+1; //lo는 0~mid, j는 (mid+1)~hi 만큼만 움직임
        for(int k = lo; k<= hi; k++){ //aux[]를 한 바퀴 돈다
            if(i>mid)                        a[k] = aux[j++]; //오른쪽 배열의 원소만 남음
            else if(j>hi)                    a[k] = aux[i++]; //왼쪽 배열의 원소만 남음
            else if(less(aux[j], aux[i]))    a[k] = aux[j++];
            else                             a[k] = aux[i++]; //aux[i] 작을 때 or 둘 다 같을 때
        }
    }

    // Tim sort
    public static void sort(Comparable[] a) {
        int n = a.length;
        Comparable[] aux = new Comparable[n];

        // 배열이 작을 경우 삽입 정렬만 수행
        if (n < RUN) {
            insertion(a, 0, n - 1);
            return;
        }

        // 1. 배열을 런 크기로 분할하고 각 런을 Insertion sort
        for (int i = 0; i < n; i += RUN) {
            insertion(a, i, Math.min(i + RUN - 1, n - 1));
        }

        // 2. 정렬된 런들을 merge sort
        for (int size = RUN; size < n; size = size * 2) {
            for (int lo = 0; lo < n - size; lo += size * 2) {
                int mid = lo + size - 1;
                int hi = Math.min(lo + size * 2 - 1, n - 1);
                merge(a, aux, lo, mid, hi);
                }
            }
        if (!isSorted(a)) throw new AssertionError();
    }

    // 테스트를 위한 메인 함수
    public static void main(String[] args) {
        Integer[] a = new Integer[100];
        for (int i = 0; i < a.length; i++) {
            a[i] = (int) (Math.random() * 100) + 1; // 1~100 범위의 난수
        }

        System.out.print("정렬 전: "); TimSort.show(a);
        TimSort.sort(a);
        System.out.print("정렬 후: "); TimSort.show(a);
    }
}
