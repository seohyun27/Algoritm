//22311947 김서현

import java.util.ArrayDeque;

//number = 23469185962
//k = 4
//number에서 k개 만큼 삭제해 가장 큰 수를 남기기

class Solution {
    public String solution(String number, int k) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        int newNum = number.charAt(0) - '0'; //첫번째 숫자를 스택에 넣기
        stack.push(newNum);

        for(int i = 1; i<number.length(); i++){
            newNum = number.charAt(i)  - '0'; //새로 들어온 숫자

            while (k > 0 && !stack.isEmpty() && stack.peek() < newNum) {
                //삭제 횟수가 남아있음 and 스택에 남은 게 있음 and 새로 들어온 숫자가 스택의 상단보다 큼
                stack.pop(); //뒤에서부터 제거
                k--; //삭제 횟수 차감
            }
            stack.push(newNum); //위의 while문 종료 후 새 숫자를 push
        }

        while (k > 0) { //만약 모든 과정이 끝난 후에도 삭제 횟수가 남아있다면
            stack.pop(); //뒤에서부터 제거
            k--;
        }

        StringBuilder answer = new StringBuilder();

        while(!stack.isEmpty())
            answer.insert(0, stack.pop());

        return answer.toString();
    }
}