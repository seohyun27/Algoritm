package SearchStructures;

import javax.swing.*;
import java.io.File;
import java.util.Scanner;

//일단 교수님이 올려주신다는 코드와 파일 나오고 다시 작성

public class FrequencyCounter {
    public static void main(String[] args){
        int minlen = Integer.parseInt(args[0]); //args[0] = 최소 길이
        SequentialSearchST <String, Integer> st = new SequentialSearchST<>();
        File file;
        final JFileChooser fc = new JFileChooser();
        if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            file = fc.getSelectedFile();
        else{
            JOptionPane.showMessageDialog(null, "파일을 선택하세요.", "오류", JOptionPane.ERROR_MESSAGE);
            return;
        }


    }
}
