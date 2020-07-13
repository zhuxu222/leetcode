package com.company.ArrayOperation;

import java.util.HashMap;
import java.util.Map;

public class ArrayOperation {
    public int threeSumClosest(int[] nums, int target) {
        return 0;
    }

    //209. 长度最小的子数组
    public int minSubArrayLen(int s, int[] nums) {
        int i=0;
        int j=0;
        int lenMin=Integer.MAX_VALUE;
        int sum=0;
        while(j<nums.length){
            sum+=nums[j];
            j++;
            if(sum<s){
                continue;
            }
            while(i<nums.length && i<=j && sum>=s){
                sum-=nums[i];
                i++;
            }
            int lenMinTemp=j-i+1;
            if(lenMinTemp<lenMin){
                lenMin=lenMinTemp;
            }
        }
        return lenMin==Integer.MAX_VALUE?0:lenMin;
    }

    //350. 两个数组的交集 II
    public int[] intersect(int[] nums1, int[] nums2) {
        if(nums1.length>nums2.length){
            int[] temp=nums1;
            nums1=nums2;
            nums2=temp;
        }
        HashMap<Integer,Integer>map=new HashMap<>();
        for(int i=0;i<nums1.length;i++){
            map.put(nums1[i],map.getOrDefault(nums1[i],0)+1);
        }
        int len=0;
        int[] sav=new int[nums2.length];
        int k=0;
        for(int i=0;i<nums2.length;i++){
            if(map.getOrDefault(nums2[i],0)>0){
                map.put(nums2[i],map.get(nums2[i])-1);
                sav[k]=nums2[i];
                k++;
            }
        }
        int[] ret=new int[k];
        for(int i=0;i<k;i++){
            ret[i]=sav[i];
        }
        return ret;
    }
}

