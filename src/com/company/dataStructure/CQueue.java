package com.company.dataStructure;

import java.util.Stack;

//剑指 Offer 09. 用两个栈实现队列
class CQueue {
    public Stack<Integer>mTail;
    public Stack<Integer>mHead;

    public CQueue() {
        mTail=new Stack<>();
        mHead=new Stack<>();
    }

    public void appendTail(int value) {
        mTail.push(value);
    }

    public int deleteHead() {
        if(mHead.empty()){
            while(!mTail.empty()) {
                mHead.push(mTail.pop());
            }
        }
        if(mHead.empty()) {
            return -1;
        } else {
            return mHead.pop();
        }
    }
}

/**
 * Your CQueue object will be instantiated and called as such:
 * CQueue obj = new CQueue();
 * obj.appendTail(value);
 * int param_2 = obj.deleteHead();
 */
