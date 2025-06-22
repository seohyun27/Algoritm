import java.util.Arrays;
import java.util.Comparator;

// 2.java의 단독 카메라 문제의 효율적 풀이

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;

        // 진출 지점 기준 오름차순 정렬
        Arrays.sort(routes, Comparator.comparingInt(a -> a[1]));

        int cameraPos = Integer.MIN_VALUE;

        for (int[] route : routes) {
            if (cameraPos < route[0]) { //새 카메라를 설치할 필요가 있음
                cameraPos = route[1]; // 이 차량의 진출 지점에 카메라 설치
                answer++; //카메라 설치 횟수 추가
            }
        }

        return answer;
    }
}
