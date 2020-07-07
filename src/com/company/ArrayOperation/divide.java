package com.company.ArrayOperation;

import java.util.Random;

public class divide {
    //215. 数组中的第K个最大元素
    public int findKthLargest(int[] nums, int k) {
        int l=0;
        int r=nums.length-1;
        int dp=0;  ///
        int target=nums.length-k;
        Random random = new Random();
        while(dp!=target){
            if(dp>target){
                r=dp;
            }else if(dp<target){
                l=Math.min(dp+1,nums.length-1);
            }
            dp= random.nextInt(r-l+1)+1;
            int i=l;
            int j=r;
            int x=nums[dp];
            while(i<j){
                while(i<j && nums[i]<x){
                    i++;
                }
                while(i<j && nums[j]>=x){
                    j--;
                }
                if(i<j){
                    int temp=nums[i];
                    nums[i]=nums[j];
                    nums[j]=temp;
                }
            }

        }
    }
}
