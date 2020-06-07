package com.company;

import java.util.*;

public class FirstUnique {
    private Map<Integer,Integer>mHad;
    private LinkedList<Integer>mUnique;
    public FirstUnique(int[] nums) {
        mHad=new LinkedHashMap<>();
        mUnique=new LinkedList<>();
        for(int i:nums){
            mHad.put(i,mHad.getOrDefault(i,0)+1);
        }
        for(int i:nums){
            if(mHad.get(i)==1){
                mUnique.add(i);
            }
        }
    }

    public int showFirstUnique() {
        if(mUnique.isEmpty()){
            return -1;
        }
        return mUnique.getFirst();
    }

    public void add(int value) {
        mHad.put(value,mHad.getOrDefault(value,0)+1);
        if(mHad.get(value)==1){
            mUnique.add(value);
        }else{
            mUnique.removeFirstOccurrence(value);
        }
    }
}
