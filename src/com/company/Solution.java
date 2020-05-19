package com.company;


import javax.script.AbstractScriptEngine;
import java.util.*;

class Solution {
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode ret=new ListNode(0);
        ListNode temp=ret;
        int carry=0;
        while(null!=l1 || null!=l2 || 0!=carry){
            int sum=((null==l1)?0:l1.val)+((null==l2)?0:l2.val)+carry;
            l1=(null==l1)?null:l1.next;
            l2=(null==l2)?null:l2.next;
            temp.next=new ListNode(sum%10);
            temp=temp.next;
            carry=sum/10;
        }
        return ret.next;
    }
    public int lengthOfLongestSubstring(String s) {
        Set<Character>occ=new HashSet<Character>();
        int len=s.length();
        int begin=0,end=-1;
        int maxLen=0;
        for(begin=0;begin<len;begin++){
            if(0<begin){
                occ.remove(s.charAt(begin-1));
            }
            while(end+1<len && !occ.contains(s.charAt(end+1))){
                occ.add(s.charAt(end+1));
                end++;
            }
            if(occ.size()>maxLen){
                maxLen=occ.size();
            }
        }
        return maxLen;
    }
    public int subarraySum(int[] nums, int k) {
        int count=0;
        Map<Integer,Integer>pshm=new HashMap<>();
        pshm.put(0,1);
        int sum=0;
        for(int num:nums){
            sum+=num;
            if(pshm.containsKey(sum-k)){
                count+=pshm.get(sum-k);
            }
            pshm.put(sum,pshm.getOrDefault(sum,0)+1);
        }
        return count;
    }
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

    public static int maxProduct(int[] nums) {
        int maxRet = Integer.MIN_VALUE;
        int negNUm = 0;
        int accumulate = 1;
        int suffix=Integer.MIN_VALUE;
        int prefix=Integer.MIN_VALUE;
        int midfix=Integer.MIN_VALUE;
        boolean firstNeg=false;
        for (int i = 0; i <= nums.length; i++) {
            if(i>=nums.length || nums[i]==0){

            }else{
                accumulate*=nums[i];
                if(nums[i]<0){
                    if(!firstNeg){
                        firstNeg=true;
                        prefix=accumulate;
                        accumulate=1;
                    }
                }
                if((firstNeg && nums[i+1]<0)){
                    midfix=accumulate;
                }
            }
        }
        return maxRet;
    }
    public static int maxProductBackpack(int[] nums) {
        int maxRet= nums[0];
        int tempMax=1;
        int tempMin=1;
        for(int num:nums){
            int max=Math.max(num*tempMax,num*tempMin);
            int min=Math.min(num*tempMax,num*tempMin);
            tempMin=Math.min(num,min);
            tempMax=Math.max(num,max);
            maxRet=Math.max(tempMax,maxRet);
        }
        return maxRet;
    }
    public boolean validPalindromeRecursive(String s) {
        int tolerance=1;
        String[] strlist={s};
        return validSubPalindrome(strlist,0,strlist[0].length()-1,tolerance);
    }
    public boolean validSubPalindrome(String[] strlist,int begin,int end,int tolerance) {
        int i=begin;
        int j=end;
        while(i<j){
            if(strlist[0].charAt(i)==strlist[0].charAt(j)){
                return validSubPalindrome(strlist,i+1,j-1,tolerance);
            }else{
                if(tolerance==0){
                    return false;
                }else{
                    tolerance--;
                    return (validSubPalindrome(strlist,i,j-1,tolerance) || validSubPalindrome(strlist,i+1,j,tolerance));
                }
            }
        }
        return true;
    }
    public static boolean validPalindrome(String s) {
        int i=0;
        int j=s.length()-1;
        while(i<j && s.charAt(i)==s.charAt(j)){
            i++;
            j--;
        }
        if(i>=j){
            return true;
        }
        int m=i+1;
        int n=j;
        while(m<n && s.charAt(m)==s.charAt(n)){
            m++;
            n--;
        }
        if(m>=n){
            return true;
        }
        m=i;
        n=j-1;
        while(m<n && s.charAt(m)==s.charAt(n)){
            m++;
            n--;
        }
        if(m>=n){
            return true;
        }
        return false;
    }
    public static String longestPalindromeN2(String s) {
        if(null==s || s.length()<=0){
            return "";
        }
        int len=s.length();
        int begin=0;
        int end=0;
        int maxSubLen=0;
        for(int i=0;i<len-1;i++){
            int times=0;
            if(s.charAt(i)==s.charAt(i+1)){
                times=1;
            }
            for(int j=i;j<=i+times;j++){
                int m=i;
                int n=j;
                while(0<m && n<len-1 && s.charAt(m-1)==s.charAt(n+1)){
                    m--;
                    n++;
                }
                int tempLen=n-m+1;
                if(tempLen>maxSubLen){
                    maxSubLen=tempLen;
                    begin=m;
                    end=n;
                }
            }
        }
        return s.substring(begin,end+1);
    }
    public static String longestPalindrome(String s) {
        if(null==s && s.length()<=0){
            return "";
        }
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<s.length();i++){
            sb.append("#");
            sb.append(s.charAt(i));
        }
        sb.append("#");
        char[] charArr=sb.toString().toCharArray();
        int[] redius=new int[charArr.length];
        int c=0;//center
        int r=0;//right
        int maxRediu=Integer.MIN_VALUE;
        int maxCenter=0;
        for(int p=0;p<charArr.length;p++){
            redius[p]=p>r?1:Math.min(redius[2*c-p],r-p+1);
            while(0<=(p-redius[p]) && p+redius[p]<charArr.length && charArr[p-redius[p]]==charArr[p+redius[p]]){
                redius[p]++;
            }
            if(p+redius[p]-1>r){
                c=p;
                r=p+redius[p]-1;
            }
            if(maxRediu<redius[p]){
                maxRediu=redius[p];
                maxCenter=p;
            }
        }
        return s.substring((maxCenter-maxRediu+1)/2,(maxCenter+maxRediu-1)/2);
    }


}
