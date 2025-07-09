package SearchStructures;

//키의 오름차순으로 (key, value)의 쌍을 keys[], vals[]에 저장
//이진 검색 트리 배열형
//리스트의 중간값을 비교하여 검색key를 찾는 이진 검색을 활용
//검색 시간은 줄었으나 삽입/삭제의 시간을 줄이지 못함

import java.util.ArrayList;

public class BinarySearchST<K extends Comparable<K>, V> { //key끼리의 크기 비교를 위해 K는 Comparable 타입
    private static final int INIT_CAPACITY = 10;
    private K[] keys;
    private V[] vals;
    private int N; //전체 원소의 개수

    @SuppressWarnings("unchecked") // 제네릭 배열 관련 경고를 무시
    public BinarySearchST(){ //디폴트 생성자
        keys = (K[]) new Comparable[INIT_CAPACITY];
        vals = (V[]) new Object[INIT_CAPACITY];
    }

    @SuppressWarnings("unchecked") // 제네릭 배열 관련 경고를 무시
    public BinarySearchST(int capacity){ //배열의 크기를 받아 생성하는 생성자
        keys = (K[]) new Comparable[capacity];
        vals = (V[]) new Object[capacity];
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

    @SuppressWarnings("unchecked") // 제네릭 배열 관련 경고를 무시
    public void resize(int capacity){
        K[] tempk = (K[]) new Comparable[capacity]; //새 크기의 배열 생성
        V[] tempv = (V[]) new Object[capacity];

        System.arraycopy(keys, 0, tempk, 0, N);
        System.arraycopy(vals, 0, tempv, 0, N);

        keys = tempk;
        vals = tempv;
    }

    private int search(K key){ //이진 검색
        int lo = 0;
        int hi = N-1;
        while(lo<=hi){
            int mid = (lo+hi)/2;
            int cmp = key.compareTo(keys[mid]); //key와 mid가 같으면 0, key가 크면 1, 작으면 -1]

            if(cmp<0) hi = mid-1;
            else if(cmp>0) lo = mid+1;
            else return mid; //while안에서 cmp 위치 이리저리 옮기다 key를 찾으면 리턴
        }

        return lo; //key를 찾지 못하고 배열 뒤지기가 끝나면 그 순간의 lo 리턴 -> 추후 해당 key의 삽입 위치로 사용됨
    }

    private V get(K key){
        if(isEmpty()) return null;
        int i = search(key);
        if(i<N && keys[i].compareTo(key)==0) //찾아낸 위치의 key 값이 찾으려는 key 값과 같을 경우 = 제대로 된 위치를 찾았다
            return vals[i]; //해당 위치의 value 반환
        else return null;
    }

    public void put(K key, V value){ //넣을 때부터 정렬을 신경써서 삽입
        int i = search(key);
        if(i<N && keys[i].compareTo(key)==0){ //찾은 i값이 key의 위치가 맞다면
            vals[i] = value; //해당 위치의 value 교체
            return;
        }

        //위의 if문을 지나쳤다 = 배열 안에 해당 key가 존재하지 않음

        if(N==keys.length) resize(2* keys.length); //배열이 다 찼다면 크기를 2배로 늘리기

        for(int j = N; j<i; j--){ //값을 삽입할 i까지 배열 안의 내용 한 칸씩 뒤로 밀기
            keys[j] = keys[j-1];
            vals[j] = vals[j-1];
        }

        keys[i] = key; //이후 해당 자리에 새 쌍을 삽입
        vals[i] = value;
        N++;
    }

    public void delete(K key){
        if(isEmpty()) return;
        int i = search(key);
        //인덱스가 범위를 벗어났거나 키값의 위치가 일치하지 않는다면 = 해당 키는 존재하지 않음
        if(i==N || key.compareTo(keys[i])!=0) return;

        //위의 if에 걸리지 않음 = 해당 키가 존재한다
        for(int j = i; j<N-1; j++){ //지우고 싶은 값 뒤에 있는 키들을 한 칸씩 앞으로 밀기
            keys[j] = keys[j+1];
            vals[j] = vals[j+1];
        }
        N--;
        keys[N] = null; //가장 끝의 중복 값에 null 채워주기
        vals[N] = null;

        if(N>INIT_CAPACITY && N==keys.length/4) //이 과정 후 채워져있는 값에 비해 배열이 지나치게 크다면
            resize(keys.length/2);
    }

    public Iterable<K> keys(){ //for문 외에 linked list의 순차검색과 거의 동일
        ArrayList<K> keyList = new ArrayList<K>(N);
        for(int i = 0; i<N; i++)
            keyList.add(keys[i]);
        return keyList;
    }
}
