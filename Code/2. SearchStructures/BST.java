package SearchStructures;

//이진 검색 트리
//루트부터 시작. 작으면 왼쪽, 크면 오른쪽
//연결 리스트형 : 정렬 순서를 유지할 필요 없음 -> 새 노드는 항상 리프에 위치 -> 삽입, 삭제시 데이터를 옮길 필요 X
//ordered : 크기순 저장, 기본 연산 외의 초가적인 기능이 더 존재 (ex.min, max 등)

import java.util.ArrayList;

public class BST <K extends Comparable<K>, V> {
    private static class Node<K, V>{ //이너 클래스로 구현된 Node 클래스
        K key;
        V value;
        Node<K,V> right, left;
        int N; //본인으로부터의 트리의 깊이
        Node<K,V> parent; //부모를 가리키는 link

        public Node(K key, V value){ //생성자. 자식이 존재하지 않음
            this.key = key;
            this.value = value;
            this.N = 1;
        }
    }

    protected Node<K, V> root;

    public int size() {return (root!=null)?root.N : 0;}

    public boolean contains(K key) {return get(key) != null;}

    public boolean isEmpty() {return root==null;}

    //해당하는 키가 존재하면 값을 반환
    //존재하지 않는다면 null
    public V get(K key) {
        if(root == null) return null;

        Node<K,V> x = treeSearch(key);
        if(key.equals(x.value)) return x.value;
        else return null;
    }

    //get과 같은 다른 함수들을 위한 내부 구현 함수
    //일치하는 키가 있으면 해당 노드를 반환
    //일치하는 키가 없으면 마지막 자리의 노드를 반환 -> 삽입 위치가 됨
    protected Node<K,V> treeSearch(K key){
        Node <K,V> x = root; //x를 움직여가며 값을 찾음

        while(true){
            int cmp = key.compareTo(x.key);
            if(cmp==0) return x; //일치하는 값을 찾았으면 해당 노드를 리턴
            else if(cmp<0){
                if(x.left == null) return x; //x가 더 내려갈 수 없다면 -> 해당 자리를 리턴
                x = x.left;
            }
            else{
                if(x.right == null) return x;
                x = x.right;
            }
        }
    }


    //해당하는 키가 이미 존재하면 값을 바꿈
    //존재하지 않는다면 입력된 키와 값으로 새로운 노드를 추가
    public void put(K key, V value){
        if(root == null){
            root = new Node<K,V>(key, value);
            return;
        }

        Node<K,V> x = treeSearch(key);
        int cmp = key.compareTo(x.key);
        if(cmp == 0) //해당하는 키가 존재할 때
            x.value = value;
        else{ //해당하는 키가 존재하지 않을 때
            Node<K,V> newNode = new Node<K,V>(key,value);
            if(cmp<0) x.left = newNode;
            else x.right = newNode;
            newNode.parent = x;
            rebalanceInsert(newNode); //삽입 후 N값을 조절
        }
    }

    //x에서 자식 노드로 내려가면서 N값을 조절하기
    private void resetSize(Node<K,V> x, int value){
        for(;x!=null;x=x.parent)
            x.N += value;
    }

    protected void rebalanceInsert(Node<K,V> x) {
        resetSize(x.parent, 1); //새 노드를 삽입 -> N값을 1씩 증가
    }

    protected void rebalanceDelete(Node<K,V> p, Node<K,V> deleted) {
        resetSize(p, -1); //기존 노드를 삭제 -> N값을 1씩 감소
    }


    //정렬된 키의 리스트들을 반환 -> inorder 순회 이용
    public Iterable<K> keys(){
        if(root == null) return null;
        ArrayList<K> keyList = new ArrayList<>(size());
        inorder(root, keyList);
        return keyList;
    }

    private void inorder(Node<K,V> x, ArrayList<K> keyList){
        if(x != null){
            inorder(x.left, keyList); //왼쪽 서브트리 방문
            keyList.add(x.key); //현재 노드 저장
            inorder(x.right, keyList); //오른쪽 서브트리 방문
        }
    }


    // 해당 키를 삭제
    // 1. 리프 노드인 경우 : 삭제
    // 2. 자식이 하나인 경우 : 삭제 후 해당 자식을 부모의 위치로 이동
    // 3. 자식이 둘인 경우 : inorder successor의 내용으로 변경 후 inorder successor는 삭제
    public void delete(K key){
        if(root==null) return;
        Node<K,V> x, y, p;
        x = treeSearch(key);

        if(!key.equals(x.key)) return; //키가 존재하지 않음

        if(x==root || isTwoNode(x)){ //x가 root 또는 자식이 2명
            if(isLeaf(x)) { //자식이 둘인 노드는 리프가 될 수 없음 -> 즉, root가 리프일 때
                root = null; //root 노드만 없애주면 해결
                return;
            }
            else if(!isTwoNode(x)){ //자식이 1명일 때
                root = (x.right == null)?x.left:x.right; //자식을 root로 연결
                root.parent = null;
                return;
            }
            else { //자식이 2명인 경우
                y = min(x.right); //inorder successor
                x.key = y.key; //x노드의 내용을 inorder successor의 내용으로 교체
                x.value = y.value;

                //inorder successor를 삭제하기 위해 inorder successor의 부모와 inorder successor의 오른쪽 자식을 연결
                //자식이 없다면 null이 연결됨
                p = y.parent;
                relink(p, y.right, y==p.left);
                rebalanceDelete(p, y);
            }
        }
        else{ //자식이 1명 이하이고 루트가 아닐 때
            p = x.parent;
            if(x.right==null) //자식이 왼쪽에 존재
                //왼쪽에 존재하는 x의 자식을 x가 원래 연결되어 있던 방향으로 (x == p.left 값을 참고) 연결
                relink(p,x.left, x == p.left);
            else if(x.left==null)
                relink(p,x.right, x == p.left);
            rebalanceDelete(p, x); //삭제 후 밸런스 맞추기
        }
    }

    protected boolean isLeaf(Node<K,V> x){
        return x.left==null && x.right==null;
    }

    protected boolean isTwoNode(Node<K,V> x){
        return x.left!=null && x.right!=null;
    }

    //나를 기준으로 부모와 자식을 연결
    //makeLeft가 ture라면 부모의 왼쪽에 연결
    protected void relink(Node<K,V> parent, Node<K,V> child, boolean makeLeft){
        if(child != null) child.parent = parent;
        if(makeLeft) parent.left = child;
        else parent.right = child;
    }

    protected Node<K,V> min(Node<K,V> x) { //key의 값이 가장 작은 Node를 반환
        while(x.left!=null)
            x = x.left;
        return x;
        /*
        참고 사항 : 해당 코드에서 원본 x의 값은 변화하지 않는다!
        Why? x는 참조값의 복사본 → 함수 내부에서 x를 바꿔도 원본에는 영향 없음
        But! x가 가리키는 객체의 내부 값(x.left, x.key)은 함수 안에서 바꾸면 호출 쪽에도 영향 있음
        */
    }


    //트리 내에서 제일 작은 키를 반환
    public K min(){
        if(root==null) return null;
        Node<K,V> x = root;
        while(x.left != null)
            x = x.left;
        return x.key;
    }


    //트리 내에서 제일 큰 키를 반환
    public K max(){
        if(root==null) return null;
        Node<K,V> x = root;
        while(x.right != null)
            x = x.right;
        return x.key;
    }


    //주어진 key보다 작거나 같은 키들 중 제일 큰 키
    public K floor(K key){
        if(root == null || key == null) return null;
        Node<K,V> x = floor(root, key);
        if(x == null) return null;
        else return x.key;
    }

    //위의 함수에서 사용하는 floor 탐색 알고리즘
    private Node<K,V> floor(Node<K,V> x, K key){
        if(x==null) return null;
        //여기서부터 이어 작성!!

        return null;
    }
}
