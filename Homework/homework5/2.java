//22311947 김서현

import java.util.ArrayList;
import java.util.Comparator;

class Comp implements Comparator<int[]> {
        public int compare(int[] a, int[] b) {
            return Integer.compare(a[1], b[1]);
        }
}

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;

        ArrayList<int[]> routesList = new ArrayList<>();
        for(int[] route : routes) {
            routesList.add(route);
        }
        routesList.sort(new Comp());

        for(int i = 0; i < routesList.size(); i++){
            for(int j = i+1; j < routesList.size(); j++){
                if(routesList.get(i)[1] >= routesList.get(j)[0]) {
                    routesList.remove(j);
                    j--;
                }
            }
            answer++;
        }

        return answer;
    }
}