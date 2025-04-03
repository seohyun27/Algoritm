//22311947 김서현

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

abstract class AbstractSort {
    public static void sort(double[] a){};

    protected static boolean less(double v, double w) {
        return v < w;
    }

    protected static void exch(double[] a, int i, int j) {
        double t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    protected static void show(double[] a) {
        for(int i=0;i<a.length;i++)
            System.out.print(a[i]+" ");
        System.out.println();
    }

    protected static boolean isSorted(double[] a){
        for(int i=1;i<a.length;i++)
            if(less(a[i], a[i-1])) return false;
        return true;
    }
}

class MergeBU extends AbstractSort {
    private static void merge(double[] in, double[] out, double[] inx,
                              double[] outx, double[] iny, double[] outy, int lo, int mid, int hi){
        int i = lo, j = mid+1;
        for(int k = lo; k<= hi; k++){
            if(i>mid)                        {out[k] = in[j]; outx[k] = inx[j]; outy[k] = iny[j++];}
            else if(j>hi)                    {out[k] = in[i]; outx[k] = inx[i]; outy[k] = iny[i++];}
            else if(less(in[j], in[i]))      {out[k] = in[j]; outx[k] = inx[j]; outy[k] = iny[j++];}
            else                             {out[k] = in[i]; outx[k] = inx[i]; outy[k] = iny[i++];}
        }
    }

    public static void sort(double[] d, double[] x, double[] y){
        int N = d.length;

        double[] srcd = d, dstd = new double[N], tmp;
        double[] srcx = x, dstx = new double[N];
        double[] srcy = y, dsty = new double[N];

        for(int n = 1; n<N; n *= 2){
            for(int i=0;i<N; i+=2*n)
                merge(srcd, dstd, srcx, dstx, srcy, dsty, i, i+n-1, Math.min(i+2*n-1, N-1));
            tmp = srcd; srcd = dstd; dstd = tmp;
            tmp = srcx; srcx = dstx; dstx = tmp;
            tmp = srcy; srcy = dsty; dsty = tmp;
        }

        if(srcd!=d) {
            System.arraycopy(srcd, 0, d, 0, N);
            System.arraycopy(srcx, 0, x, 0, N);
            System.arraycopy(srcy, 0, y, 0, N);
        }
    }
}


public class HW1 extends AbstractSort {
    private static int SIZE = 1000;
    private double[] x;
    private double[] y;
    private double[] distance;
    private int N;

    public HW1(){
        x = new double[SIZE];
        y = new double[SIZE];
        distance = new double[SIZE];
        N = 0;
    }


    public static double distanceCalculation(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    public void add(double x2, double y2, double d){
        x[N] = x2;
        y[N] = y2;
        distance[N] = d;
        N++;

        if(N==SIZE){ SIZE *= 2; resize(SIZE);}
    }


    public void resize(int capacity){
        double[] tempx = new double[capacity];
        double[] tempy = new double[capacity];
        double[] tempdistance = new double[capacity];

        System.arraycopy(x, 0, tempx, 0, N);
        System.arraycopy(y, 0, tempy, 0, N);
        System.arraycopy(distance, 0, tempdistance, 0, N);

        x = tempx;
        y = tempy;
        distance = tempdistance;
    }

    public void kNearest(int k) {
        if (k == -1) k = N;

        double[] distanceCopy = new double[N];
        double[] xCopy = new double[N];
        double[] yCopy = new double[N];
        double[] tmpCopy = new double[N];

        System.arraycopy(distance, 0, distanceCopy, 0, N);
        System.arraycopy(x, 0, xCopy, 0, N);
        System.arraycopy(y, 0, yCopy, 0, N);

        x = xCopy;
        y = yCopy;
        distance = distanceCopy;

        long start = System.currentTimeMillis();
        MergeBU.sort(distanceCopy, xCopy, yCopy);
        long end = System.currentTimeMillis();
        System.out.printf("k = %d일 때의 실행 시간 = %dms\n", k, (end-start));

        for(int i = 0; i < k; i++) {
            System.out.printf("%d : (%f, %f), 거리: %f\n", i, x[i], y[i], distance[i]);
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("입력 파일 이름? ");
        String fname = sc.nextLine();
        sc.close();

        HW1 list = new HW1();

        try {
            sc = new Scanner(new File(fname));
            double my_x = sc.nextDouble();
            double my_y = sc.nextDouble();
            int k = sc.nextInt();
            int station_n = sc.nextInt();
            double station_x, station_y, station_d;

            for (int i = 0; i < station_n; i++){
                station_x = sc.nextDouble();
                station_y = sc.nextDouble();
                station_d = distanceCalculation(my_x, my_y, station_x, station_y);
                list.add(station_x, station_y, station_d);
            }

            list.kNearest(100);
            list.kNearest(100);



            } catch (IOException e) { System.out.println(e); return; }
        if (sc != null) sc.close();
    }
}
