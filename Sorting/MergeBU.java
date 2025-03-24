package Sorting;

//Bottom Down Merge Sort
//아래쪽에서부터 2배씩 늘리며 merge

public class MergeBU extends AbstractSort {
    private static void merge(Comparable[] in, Comparable[] out, int lo, int mid, int hi){
        //in으로 입력받은 배열의 값을 out에 정렬
        int i = lo, j = mid+1;
        for(int k = lo; k<= hi; k++){
            if(i>mid)                        out[k] = in[j++]; //오른쪽 배열의 원소만 남음
            else if(j>hi)                    out[k] = in[i++]; //왼쪽 배열의 원소만 남음
            else if(less(in[j], in[i]))      out[k] = in[j++];
            else                             out[k] = in[i++]; //in[i] 작을 때 or 둘 다 같을 때
        }
    }

    public static void sort(Comparable[] a){
        //src : 원본을 카피한 배열
        //dst : 정렬 결과를 저장할 배열
        Comparable[] src = a, dst = new Comparable[a.length], tmp;
        int N = a.length;
        for(int n = 1; n<N; n *= 2){ //2, 4, 8, 16.. 간격으로 정렬 범위를 늘림
            for(int i=0;i<N; i+=2*n) //위에서 정한 n의 범위로 정렬 진행
                merge(src, dst, i, i+n-1, Math.min(i+2*n-1, N-1));
            tmp = src; src = dst; dst = tmp;
            //정렬된 결과를 src에 저장. for문으로 돌아가 다음 정렬을 실행함
            //만약 두 배열을 바꿔주지 않고 src = dst;만 실행할 경우 두 변수가 같은 배열을 가리키게 됨(참조 타입)
        }
        if(src!=a) System.arraycopy(src, 0, a, 0, N);
        //정렬된 배열 src가 a와 같지 않다면 src의 내용을 원본 a에 복사
        //최종 정렬본이 원본 a에 담기게 됨
    }

}
