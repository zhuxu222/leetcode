package com.company;

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};

public class NextNodeTree {

    //117. 填充每个节点的下一个右侧节点指针 II
    public Node connect(Node root) {
        if (null==root){
            return null;
        }
        Node temp=root;
        Node last=null;
        Node next=null;
        while(null!=temp){
            if (null!=temp.left){
                if (null!=last){
                    last.next=temp.left;
                }
                last=temp.left;
                if (null==next){
                    next=last;
                }
            }
            if (null!=temp.right){
                if (null!=last){
                    last.next=temp.right;
                }
                last=temp.right;
                if (null==next){
                    next=last;
                }
            }
            temp=temp.next;
        }
        connect(next);
        return root;
    }
}
