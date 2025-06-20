//22311947 김서현

import java.util.ArrayDeque;

class Solution {
    public String solution(String number, int k) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        int newNum = number.charAt(0) - '0';
        stack.push(newNum);

        for(int i = 1; i<number.length(); i++){
            newNum = number.charAt(i)  - '0';

            while (k > 0 && !stack.isEmpty() && stack.peek() < newNum) {
                stack.pop();
                k--;
            }
            stack.push(newNum);
        }

        while (k > 0) {
            stack.pop();
            k--;
        }

        StringBuilder answer = new StringBuilder();

        while(!stack.isEmpty())
            answer.insert(0, stack.pop());

        return answer.toString();
    }
}