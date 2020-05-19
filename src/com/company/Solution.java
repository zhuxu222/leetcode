package com.company;

public class Solution {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if(nums1.length>nums2.length){
            int[] temp=nums1;
            nums1=nums2;
            nums2=temp;
        }
        int begin=0;
        int end=nums1.length;
        int lMax=Integer.MAX_VALUE;
        int rMin=Integer.MIN_VALUE;
        while(lMax>rMin){
            int i=(begin+end)/2;
            int j=(nums1.length+nums2.length)/2-i;
            lMax=i>0?Math.max(nums1[i-1],nums2[j-1]):nums2[j-1];
            rMin=i<nums1.length?Math.min(nums1[i],nums2[j]):nums2[j];
            if(i<nums1.length && nums1[i]<nums2[j-1]){
                begin=i+1;
            }
            if(i>0 && nums1[i-1]>nums2[j]){
                end=i;
            }
        }
        if ((nums1.length+nums2.length)%2==0) {
            return ((float)lMax+(float)rMin)/2;
        }else{
            return rMin;
        }
    }
}
