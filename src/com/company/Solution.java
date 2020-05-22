package com.company;


import javax.script.AbstractScriptEngine;
import java.security.KeyPair;
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
        int i=0;
        int j=0;
        double medium=0;
        boolean flag=((nums1.length+nums2.length)%2==0);
        while(begin<=end){
            i=(begin+end)/2;
            j=(nums1.length+nums2.length)/2-i;
            int iRight=i<nums1.length?nums1[i]:Integer.MAX_VALUE;
            int iLeft=i>0?nums1[i-1]:Integer.MIN_VALUE;
            int jRight=j<nums2.length?nums2[j]:Integer.MAX_VALUE;
            int jLeft=j>0?nums2[j-1]:Integer.MIN_VALUE;
            if(iRight>=jLeft && jRight>=iLeft){
                medium=Math.min(iRight,jRight);
                medium=flag?(Math.max(iLeft,jLeft)+medium)/2.0:medium;
                break;
            }else if(iRight<jLeft){
                begin=i+1;
            }else if(jRight<iLeft){
                end=i-1;
            }
        }
        return medium;
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
    public static int findTheLongestSubstring(String s) {
        int[] pos=new int[1<<5];
        Arrays.fill(pos,-1);
        int status=0;
        int ans=0;
        pos[0]=0;
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            if(c=='a'){
                status^=(1<<0);
            }else if(c=='e'){
                status^=(1<<1);
            }else if(c=='i'){
                status^=(1<<2);
            }else if(c=='o'){
                status^=(1<<3);
            }else if(c=='u'){
                status^=(1<<4);
            }
            if(pos[status]>=0){
                ans=Math.max(ans,i+1-pos[status]);
            }else{
                pos[status]=i+1;
            }
        }
        return ans;
    }
    public static int findKthNumber(int m, int n, int k) {
        int low=1;
        int high=m*n;
        while(low<high){
            int medium=(low+high)/2;
            int count=0;
            for(int i=1;i<=m;i++){
                count+=Math.min(medium/i,n);
            }
            if(count>=k){
                high=medium;
            }else{
                low=medium+1;
            }
        }
        return high;
    }

    public static int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int low = 0;
        int high = nums[nums.length-1] - nums[0];
        while (low < high) {
            int middle = (low + high) / 2;
            int count = 0;
            for (int right = 0; right < nums.length; right++) {
                int left = 0;
                while (nums[right] - nums[left] > middle) {
                    left++;
                }
                count += right - left;
            }
            if (count >= k) {
                high = middle;
            } else {
                low = middle + 1;
            }
        }
        return high;
    }
    public static String convert(String s, int numRows) {
        if(null==s || s.length()<=0){
            return "";
        }
        if(numRows<2){
            return s;
        }
        StringBuilder sb=new StringBuilder();
        int len=s.length();
        int t=2*numRows-2;
        for(int i=0;i<numRows;i++){
            int m=i;
            int n=t-i;
            while(m<len){
                sb.append(s.charAt(m));
                if(i>0 && i<numRows-1 && n<len){
                    sb.append(s.charAt(n));
                }
                m+=t;
                n+=t;
            }
        }
        return sb.toString();
    }
    public  static int reverse(int x) {

        return x;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Stack<TreeNode>treeNodeStack=new Stack<>();
        Stack<Integer>beginPre=new Stack<>();
        Stack<Integer>endPre=new Stack<>();
        Stack<Integer>beginIn=new Stack<>();
        Stack<Integer>endIn=new Stack<>();
        if(null==preorder || null==inorder || preorder.length<=0 || inorder.length<=0){
            return null;
        }
        TreeNode head=new TreeNode(0);
        treeNodeStack.push(head);
        beginPre.push(0);
        endPre.push(preorder.length-1);
        beginIn.push(0);
        endIn.push(inorder.length-1);
        while(!treeNodeStack.empty()){
            int bi=beginIn.pop();
            int ei=endIn.pop();
            int bp=beginPre.pop();
            int ep=endPre.pop();
            TreeNode tempNode=treeNodeStack.pop();
            tempNode.val=preorder[bp];
            int ti=bi;
            for(ti=bi;ti<=ei;ti++){
                if(inorder[ti]==tempNode.val){
                    break;
                }
            }
            if(ti>bi){
                beginIn.push(bi);
                endIn.push(ti-1);
                beginPre.push(bp+1);
                endPre.push(bp+ti-bi);
                tempNode.left=new TreeNode(0);
                treeNodeStack.push(tempNode.left);
            }
            if(ti<ei){
                beginIn.push(ti+1);
                endIn.push(ei);
                beginPre.push(bp+ti-bi+1);
                endPre.push(ep);
                tempNode.right=new TreeNode(0);
                treeNodeStack.push(tempNode.right);
            }
        }
        return head;
    }

}
