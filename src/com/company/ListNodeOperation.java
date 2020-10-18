package com.company;

public class ListNodeOperation
{
    class ListNode
    {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val)
        {
            this.val = val;
        }
        ListNode(int val, ListNode next)
        {
            this.val = val;
            this.next = next;
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

    //142. 环形链表 II
    public ListNode detectCycle(ListNode head) {
        if (null==head || null==head.next){
            return null;
        }
        ListNode fast=head.next.next;
        ListNode slow=head.next;
        ListNode pHead=head;
        ListNode pMeet=null;
        while(null!=fast && null!=fast.next){
            if (fast==slow){
                pMeet=fast;
                break;
            }
            slow=slow.next;
            fast=fast.next.next;
        }
        if (null!=pMeet){
            while(pHead!=pMeet){
                pHead=pHead.next;
                pMeet=pMeet.next;
            }
        }
        return pMeet;
    }

    //19. 删除链表的倒数第N个节点
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode front=head;
        ListNode back=head;
        for (int i=0;i<n;i++)
        {
            if (null!= front.next)
            {
                front=front.next;
            }else{
                return head.next;
            }

        }
        while(null!=front.next)
        {
            front=front.next;
            back=back.next;
        }
        back.next=back.next.next;
        return head;
    }
}
