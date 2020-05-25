package com.company;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    private int capacity;
    private int size;
    private final DLinkedList head,tail;
    private Map<Integer,DLinkedList>cache=new HashMap<>();
    public LRUCache(int capacity) {
        this.capacity=capacity;
        this.head=new DLinkedList();
        this.tail=new DLinkedList();
        head.next=tail;
        tail.prev=head;
    }

    public int get(int key) {
        if(cache.containsKey(key)){
            DLinkedList temp=cache.get(key);
            moveToHead(temp);
            return temp.val;
        }
        return -1;
    }

    public void put(int key, int value) {
        DLinkedList tempNode;
        if(cache.containsKey(key)){
            tempNode=cache.get(key);
            tempNode.val=value;
        }else{
            tempNode=new DLinkedList(key,value);
            size++;
            if(size>capacity){
                cache.remove(tail.prev.key);
                tail.prev.prev.next=tail;
                tail.prev=tail.prev.prev;
                size--;
            }
            cache.put(key,tempNode);
        }
        moveToHead(tempNode);
    }

    private void moveToHead(DLinkedList temp){
        if(null!=temp.prev){
            temp.prev.next=temp.next;
        }
        if(null!=temp.next){
            temp.next.prev=temp.prev;
        }
        temp.next=head.next;
        temp.prev=head;
        head.next.prev=temp;
        head.next=temp;
    }
    class DLinkedList{
        int key;
        int val;
        DLinkedList prev;
        DLinkedList next;
        public DLinkedList(){}
        public DLinkedList(int key,int val){
            this.key=key;
            this.val=val;
        }
    }
}
