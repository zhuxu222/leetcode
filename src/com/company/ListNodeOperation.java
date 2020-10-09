package com.company;

public class ListNodeOperation {
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    //141. 环形链表
    public boolean hasCycle(ListNode head) {
        ListNode tmpNode=head;
        while(null!=tmpNode){
            if (tmpNode.val<=100000){
                tmpNode.val+=100001;
            }else{
                ListNode tmp=head;
                while(null!=tmp){
                    if (tmp.val>100000){
                        tmp.val-=100001;
                    }else{
                        break;
                    }
                    tmp=tmp.next;
                }
                return true;
            }
            tmpNode=tmpNode.next;
        }
        return false;
    }

    //141. 环形链表
    public boolean hasCycleDoublePointer(ListNode head) {
        if (null==head || null==head.next){
            return false;
        }
        ListNode fast=head.next;
        ListNode slow=head;
        while(null!=fast && null!=fast.next){
            if (fast==slow){
                return true;
            }
            slow=slow.next;
            fast=fast.next.next;
        }
        return false;
    }


}
