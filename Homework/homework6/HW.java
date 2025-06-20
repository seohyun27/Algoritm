import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Solution {
    public int solution(String dartResult) {
        int set = 0;
        int score;
        int[] scoreboard = new int[3];

        String regex = "([0-9]|10)([S|D|T])([*|#]?)";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(dartResult);

        while(m.find()){
            score = Integer.parseInt(m.group(1));

            switch (m.group(2)) {
                case "S":
                    scoreboard[set] = score;
                    break;
                case "D":
                    scoreboard[set] = score * score;
                    break;
                case "T":
                    scoreboard[set] = score * score * score;
                    break;
            }

            if(m.group(3).equals("*") && set > 0){
                scoreboard[set] *= 2;
                scoreboard[set - 1] *= 2;
            }
            else if(m.group(3).equals("*"))
                scoreboard[set] *= 2;
            else if(m.group(3).equals("#"))
                scoreboard[set] *= -1;

            set++;
        }
        return scoreboard[0] + scoreboard[1] + scoreboard[2];
    }
}