//22311947 김서현

import java.util.ArrayList;
import java.util.Comparator;

class Comp implements Comparator<int[]> { //진출 지점 = 2번째 항을 기준으로 정렬
        public int compare(int[] a, int[] b) {
            return Integer.compare(a[1], b[1]);
        }
}

// 입력 : [[-20, 15], [-14, -5], [-18, -13], [-5, -3]]

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        ArrayList<Integer> location = new ArrayList<>(); //설치 위치

        ArrayList<int[]> routesList = new ArrayList<>();
        for(int[] route : routes) {
            routesList.add(route);
        }
        routesList.sort(new Comp()); //진출 지점을 기점으로 오름차순 정렬

        for(int i = 0; i < routesList.size(); i++){ //카메라를 설치할 지점을 선택하기 위한 인덱스(리스트의 앞에 애들부터 선택됨)
            location.add(routesList.get(i)[1]); //설치 위치를 추가
            for(int j = i+1; j < routesList.size(); j++){ //선택된 지점 뒤의 차량들
                if(routesList.get(i)[1] >= routesList.get(j)[0]) { //해당 지점에 걸리는 애들
                    routesList.remove(j); //삭제
                    j--; //인덱스 조절
                }
            }
            answer++; //카메라 설치 개수 추가
        }

        System.out.println("카메라 설치 위치 : " + location);

        return answer;
    }
}