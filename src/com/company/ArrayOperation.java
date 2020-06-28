package com.company;

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
}

