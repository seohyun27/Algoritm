package Sorting;

//Top Down Merge Sort

public class MergeTD extends AbstractSort {
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


    public static void sort(Comparable[] a){
        Comparable[] aux = new Comparable[a.length]; //merge를 사용하기 위한 임시배열
        sort(a, aux, 0, a.length-1);
    }

    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        //lo=hi=0는 데이터가 1개 남음 -> 더 이상 sort할 필요가 없음 -> 이때부터 merge 시작
        if(hi <= lo) return;
        int mid = lo + (hi-lo) / 2; //하나의 배열을 반으로 쪼개기
        sort(a, aux, lo, mid); //제귀함수를 호출하면서 계속 쪼개기
        sort(a, aux, mid+1, hi);
        merge(a, aux, lo, mid, hi); //여기서 두 배열을 합치면서 merge로 정렬
    }

    public static void main(String[] args){
        Character[] a = {'M', 'E', 'R', 'G', 'E', 'S', 'O', 'R',
                            'T', 'E', 'X', 'A', 'M', 'P', 'L', 'E'};
        MergeTD.sort(a);
        MergeTD.show(a);
    }
}
