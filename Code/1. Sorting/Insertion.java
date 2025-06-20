package Sorting;

public class Insertion extends AbstractSort{
    public static void sort(Comparable[] a){
        int N = a.length;
        for(int i=1;i<N;i++)
            //a[j-1]이 존재하고 a[j]가 a[j-1]보다 작을 때 앞으로 한 칸씩 이동
            for(int j = i; j>0 && less(a[j], a[j-1]); j--)
                exch(a, j, j-1);

        if (!isSorted(a)) throw new AssertionError();
    }

    public static void main(String[] args){
        Integer[] a = {10, 4, 5, 2, 1, 8, 3, 6};
        Insertion.sort(a);
        Insertion.show(a);
    }
}
