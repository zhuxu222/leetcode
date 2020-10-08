package com.company.ArrayOperation;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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

    //120. 三角形最小路径和
    public int minimumTotal(List<List<Integer>> triangle) {
        int m=triangle.size();
        int n=triangle.get(m-1).size();
        int[] bp=new int[n];
        bp[0]=triangle.get(0).get(0);
        for(int i=1;i<m;i++){
            List<Integer>tmp=triangle.get(i);
            int k=tmp.size();
            bp[k-1]=bp[k-2]+tmp.get(k-1);
            for(int j=k-2;j>0;j--){
                bp[j]=Math.min(bp[j],bp[j-1])+tmp.get(j);
            }
            if(k>1){
                bp[0]+=tmp.get(0);
            }
        }
        int minValue=bp[0];
        for(int i=1;i<n;i++){
            minValue=Math.min(minValue,bp[i]);
        }
        return minValue;
    }

    //35. 搜索插入位置
    public int searchInsert(int[] nums, int target) {
        int l=0;
        int r=nums.length-1;
        if(target<nums[0]){
            return 0;
        }else if(target>nums[r]){
            return r+1;
        }
        while(r>l){
            int m=(l+r)/2;
            if(nums[m]<target){
                l=m+1;
            }else{
                r=m;
            }
        }
        return r;
    }

    //344. 反转字符串
    public void reverseString(char[] s) {
        if(null==s || s.length<=1){
            return;
        }
        int mid=s.length/2;
        for (int i=0;i<mid;i++){
            char temp=s[i];
            s[i]=s[s.length-1-i];
            s[s.length-1-i]=temp;
        }
    }

}

