package Sorting;

public class TimSort extends AbstractSort {
    private static final int RUN = 16; // 기본 런의 크기

    // Insertion sort
    private static void insertion(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            for (int j = i; j > lo && less(a[j], a[j-1]); j--) {
                exch(a, j, j-1);
            }
        }
    }

    // merge sort
    private static void merge(Comparable[] a, int lo, int mid, int hi) {
        int n1 = mid - lo + 1;
        int n2 = hi - mid;

        Comparable[] left = new Comparable[n1];
        Comparable[] right = new Comparable[n2];

        for (int i = 0; i < n1; i++) left[i] = a[lo + i];
        for (int j = 0; j < n2; j++) right[j] = a[mid + 1 + j];

        int i = 0, j = 0, k = lo;
        while (i < n1 && j < n2) {
            if (less(left[i], right[j])) a[k++] = left[i++];
            else a[k++] = right[j++];
        }

        while (i < n1)
            a[k++] = left[i++];
        while (j < n2)
            a[k++] = right[j++];
    }

    // Tim sort
    public static void sort(Comparable[] a) {
        int n = a.length;

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
        for (int size = RUN; size < n; size = 2 * size) {
            for (int left = 0; left < n; left += 2 * size) {
                int mid = left + size - 1;
                int right = Math.min(left + 2 * size - 1, n - 1);

                if (mid < right) {
                    merge(a, left, mid, right);
                }
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
