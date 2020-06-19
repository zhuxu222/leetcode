package com.company;

public class LinkedListOperation {
    public ListNode partition(ListNode head, int x) {
        ListNode pre=head;
        while(null!=pre){
            ListNode change=pre.next;
            if(null!=change && change.val<x){
                if(null!=change.next){
                    pre.next=change.next;
                }else{
                    pre.next=null;
                }
                change.next=head;
                head=change;
            }else{
                pre=pre.next;
            }
        }
        return head;
    }
}

class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
}
