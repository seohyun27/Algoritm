package Sorting;

public class Shell extends AbstractSort{
    public static void sort(Comparable[] a){
        int N = a.length;
        int h = 1;
        while(h < N/3) h = 3*h+1; //적당한 h값을 구해줌

        //여기서 h값을 4, 13, 40, 121...로 바꾸면 각 h-sort의 값을 확인할 수 있음!!
        while (h >= 1) { //h=1까지만 실행
            for(int i = h; i < N; i++) //N-h만큼 실행
                //h 간격으로 비교해서 더 작은 값 찾거나 마지막 요소까지 앞으로 이동
                for(int j = i; j >= h && less(a[j], a[j-h]); j-=h)
                    exch(a, j, j-h);

            h /= 3; //h값 줄여서 다시 sort
        }
    }

    public static void main(String[] args){
        Integer[] a = {10, 4, 5, 2, 1, 8, 3, 6};
        Shell.sort(a);
        Shell.show(a);

        Character[] b = {'e', 'a', 's', 'y', 's', 'o', 'r',
                't', 'q', 'u', 'e', 's', 't', 'i', 'o', 'n'};
        Shell.sort(b);
        Shell.show(b);
    }
}
