package SearchStructures;

import java.util.ArrayList;

//단방향 연결 리스트를 이용한 순차 검색 : 기본 연산들

public class SequentialSearchST<K, V> {
    private static class Node<K, V>{ //제네릭 타입
        K key; //K타입 변수 key
        V value; //V타입 변수 value
        Node<K, V> next; //참조 변수(포인터)

        public Node(K key, V value, Node<K,V> next){ //생성자
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }


    private Node<K, V> first; //첫 번째 노드를 가리키게 될 Node 타입의 참조 변수, 초기에는 null을 가리킨다
    int N; //연결리스트의 노드 수, 초기값 = 0

    public V get(K key) { //K형의 key값을 받아 해당하는 V형 변수를 리턴하는 함수
        for (Node<K, V> x = first; x != null; x = x.next)
            //Node형 변수를 가리키는 포인터 x에 first값을 담는다
            //x만 움직이고 first의 위치는 바꾸지 않도록
            if (key.equals(x.key))
                return x.value;
        return null; //해당 key가 존재하지 않음
    }

    public void put(K key, V value){ //key가 이미 존재하면 value의 값 변경, 없다면 가장 앞 위치에 새 노드 생성해 삽입
        for(Node<K,V> x = first; x!=null; x = x.next)
            if(key.equals(x.key)) {
                x.value = value;
                return;
            }

        //현재의 first 값을 새 노드의 next로 잡음 -> 이후 first의 위치를 새 노드로 정정
        first = new Node<K,V>(key, value, first);
        N++; //노드 수 증가
    }

    public void delete(K key){
        if(key.equals(first.key)){ //첫 노드의 값을 삭제하는 경우
            first = first.next; N--; //노드 수 감소
            return;
        }

        for(Node<K,V> x = first; x.next!=null; x = x.next){ //x.next가 null이 될때까지
            if(key.equals(x.next.key)){
                x.next = x.next.next; N--; //노드 수 감소
                return;
            }
        }
    }

    public Iterable<K> keys(){ //테이블에 저장된 모든 key 리턴
        ArrayList<K> keyList = new ArrayList<K>(N);
        for(Node<K,V> x = first; x!=null; x=x.next)
            keyList.add(x.key);
        return keyList;
    }

    public boolean contain(K key){
        return get(key) != null; //key가 존재한다면 true 반환
    }

    public boolean isEmpty(){
        return N==0;
    }

    public int size(){
        return N;
    }
 }

