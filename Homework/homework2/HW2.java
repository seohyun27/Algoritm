//22311947 김서현

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.io.FileInputStream;


class BST <K extends Comparable<K>, V> {
    private static class Node<K,V> {
        K key;
        V value;
        Node<K,V> left, right;

        int N;
        int aux;
        Node<K,V> parent;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            this.N = 1;
        }
    }


    protected Node<K,V> root;

    public int size() { return (root!=null) ? root.N : 0; }

    protected Node<K,V> treeSearch(K key){
        Node<K,V> x = root;

        while(x != null){
            int cmp = key.compareTo(x.key);

            if(cmp == 0) return x;
            else if(cmp > 0) {
                if(x.right == null) return x;
                else x = x.right;
            }
            else {
                if(x.left == null) return x;
                else x = x.left;
            }
        }
        return null;
    }

    public V get(K key){ //키 있으면 value 반환, 없으면 null 반환
        if(root == null) return null;
        Node<K,V> x = treeSearch(key);
        if(key.equals(x.key)) return x.value;
        else return null;
    }

    public void put(K key, V val){
        if(root == null) { root = new Node<>(key, val); return; }
        Node<K,V> x = treeSearch(key);
        int cmp = key.compareTo(x.key);

        if(cmp == 0) x.value = val;
        else {
            Node<K,V> newNode = new Node<>(key, val);
            if(cmp < 0) x.left = newNode;
            else x.right = newNode;
            newNode.parent = x;
            rebalanceInsert(newNode);
        }
    }

    protected void rebalanceInsert(Node<K,V> x){
        reSetSize(x.parent, 1);
    }

    private void reSetSize(Node<K,V> x, int value){
        for(; x!=null; x=x.parent)
            x.N += value;
    }

    public Iterable<K> key(){
        if(root==null) return null;
        ArrayList<K> keyList = new ArrayList<>(size());
        inorder(root, keyList);
        return keyList;
    }

    private void inorder(Node<K,V> x, ArrayList<K> keyList){
        if(x!=null){
            inorder(x.left, keyList);
            keyList.add(x.key);
            inorder(x.right, keyList);
        }
    }
}


public class HW2 {
    private static BST<String, Integer> bst1;
    private static BST<String, Integer> bst2;

    public static BST<String, Integer> makeBST(String fname) throws IOException {
        BST<String, Integer> bst = new BST<>();

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(fname), StandardCharsets.UTF_8)
        );

        ArrayList<String> word_list = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(line, " \t\n=;,<>()");
            while (st.hasMoreTokens()) {
                word_list.add(st.nextToken());
            }
        }
        reader.close();

        for (int i = 0; i <= word_list.size() - 5; i++) {
            StringBuilder shingleBuilder = new StringBuilder();
            for (int j = 0; j < 5; j++) {
                shingleBuilder.append(word_list.get(i + j));
                if (j < 4) shingleBuilder.append(" ");
            }

            String shingle = shingleBuilder.toString();
            Integer val = bst.get(shingle);
            if (val == null) bst.put(shingle, 1);
            else bst.put(shingle, val + 1);
        }

        return bst;
    }


    public static int shingle_count(BST<String, Integer> bst) throws IOException {
        int count = 0;

        Iterable<String> keys = bst.key();
        if (keys == null) return 0;

        for (String key : keys) {
            count += bst.get(key);
        }

        return count;
    }

    public static int shingle_count(BST<String, Integer> bst1, BST<String, Integer> bst2) throws IOException {
        int count = 0;
        Integer val1, val2;

        Iterable<String> keys = bst1.key();
        if (keys == null) return 0;

        for (String key : keys) {
            val1 = bst1.get(key);
            val2 = bst2.get(key);

            if(val2 == null) continue;
            if(val1 <= val2) count += val1;
            else count += val2;
        }
        return count;
    }


    public static void main(String[] args) {
        Scanner sc1 = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        System.out.println("첫 번째 파일 이름? ");
        String fname1 = sc1.nextLine();
        System.out.println("두 번째 파일 이름? ");
        String fname2 = sc2.nextLine();

        try {
            bst1 = makeBST(fname1);
            bst2 = makeBST(fname2);

            int bst1_count = shingle_count(bst1);
            int bst2_count = shingle_count(bst2);
            System.out.println("파일 " + fname1 + "의 shingle의 수 = " + bst1_count);
            System.out.println("파일 " + fname2 + "의 shingle의 수 = " + bst2_count);

            int top = shingle_count(bst1, bst2);
            int bottom = bst1_count + bst2_count - top;
            System.out.println("두 파일에서 공통된 shingle의 수 = " + top);

            double common_shingle = (double) top / bottom;
            System.out.println(fname1 + "과 " + fname2 + "의 유사도 = " + common_shingle);


        } catch (IOException e) { System.out.println(e); return; }
        if (sc1 != null) sc1.close();
        if (sc2 != null) sc2.close();
    }
}
