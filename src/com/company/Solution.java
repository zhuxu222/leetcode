package com.company;


import javax.script.AbstractScriptEngine;
import java.lang.reflect.Array;
import java.lang.reflect.MalformedParameterizedTypeException;
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

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
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
    public static String minWindow(String s, String t) {
        if(s.length()<t.length()){
            return "";
        }
        Map<Character,Integer>needChar=new HashMap<>();
        Map<Character,Integer>hasChar=new HashMap<>();
        for(int i=0;i<t.length();i++){
            needChar.put(t.charAt(i),needChar.getOrDefault(t.charAt(i),0)+1);
        }
        int minBegin=-1;
        int minEnd=s.length()-1;
        int minLen=Integer.MAX_VALUE;

        int begin=-1;
        int end=-1;

        boolean endFlag=false;
        while(!endFlag){
            while(end<s.length()-1 && !checkMapContian(hasChar,needChar)){
                end++;
                char tc=s.charAt(end);
                hasChar.put(tc,hasChar.getOrDefault(tc,0)+1);
            }
            while(checkMapContian(hasChar,needChar)){
                begin++;
                char tc=s.charAt(begin);
                hasChar.put(tc,hasChar.get(tc)-1);
            }
            int len=end-begin+1;
            if(len<minLen){
                minBegin=begin;
                minEnd=end;
                minLen=len;
            }
            if(end==s.length()-1){
                endFlag=true;
            }
        }
        return minBegin==-1?"":s.substring(minBegin,minEnd+1);
    }
    public static boolean checkMapContian(Map<Character,Integer>s,Map<Character,Integer>t){
        Iterator iter=t.entrySet().iterator();
        while(iter.hasNext()){
            Map.Entry entry=(Map.Entry) iter.next();
            Character key=(Character) entry.getKey();
            Integer val=(Integer)entry.getValue();
            if(s.getOrDefault(key,0)<val){
                return false;
            }
        }
        return true;
    }
    public static int reverse(int x) {
        int[] xL={0,0,0,0,0,0,0,0,0,0};
        int i=-1;
        while(x!=0){
            i++;
            xL[i]=x%10;
            x/=10;
        }
        long ret=0;
        long bit=1;
        for(int k=i;k>=0;k--){
            ret+=xL[k]*bit;
            bit*=10;
        }
        if(ret >Integer.MAX_VALUE || ret<Integer.MIN_VALUE){
            ret=0;
        }
        return (int)ret;
    }
    public static int myAtoi(String str) {
        int i=0;
        int len=str.length();
        while(i<len && str.charAt(i)==32){
            i++;
        }
        if(i>len-1 || len==0){
            return 0;
        }
        char fc=str.charAt(i);
        int sign=1;
        if(fc==43){
            sign=1;
            i++;
        }else if(fc==45){
            sign=-1;
            i++;
        }else if(48>fc || fc>57 ){
            return 0;
        }
        while(i<len && str.charAt(i)==48){
            i++;
        }
        Stack<Integer>numList=new Stack<>();
        int numBit=0;
        while( i<len && 47<str.charAt(i) && str.charAt(i)<58){
            numList.push((int)str.charAt(i)-48);
            numBit++;
            i++;
        }
        if(numBit>10){
            return sign>0?Integer.MAX_VALUE:Integer.MIN_VALUE;
        }

        long num=0;
        long bit=1;
        while(!numList.empty()){
            num+=((long)numList.pop())*bit;
            bit*=10;
        }
        num=sign*num;
        num=Math.max(num,Integer.MIN_VALUE);
        num=Math.min(num,Integer.MAX_VALUE);
        return (int)num;
    }
    public static boolean isPalindrome(int x) {
        if(x<0){
            return false;
        }
        int[] bitList=new int[10];
        int len=0;
        while(x!=0){
            bitList[len]=x%10;
            x/=10;
            len++;
        }
        if(len<=1){
            return true;
        }
        int i=0;
        int j=len-1;
        while(j>i && bitList[i]==bitList[j]){
            i++;
            j--;
        }
        if(i>=j){
            return true;
        }else{
            return false;
        }
    }

    public static boolean isPalindromeFast(int x) {
        if(x<0){
            return false;
        }
        if(x>0 && x%10==0){
            return false;
        }
        int y=0;
        while(y<x){
            y=y*10+x%10;
            x/=10;
        }
        return x==y || x==y/10;
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer>map=new HashMap<>();
        for(int i=0;i<nums.length;i++){
            int differ=target-nums[i];
            if(map.containsKey(differ)){
                return new int[]{map.get(differ),i};
            }else{
                map.put(nums[i],i);
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }
    public int findDuplicate0(int[] nums) {
        int n=nums.length-1;
        int l=1;
        int r=n;
        while(l<r){
            int m=(l+r)/2;
            int cnt=0;
            for(int i=0;i<=n;i++){
                if(nums[i]<=m){
                    cnt++;
                }
            }
            if(cnt>m){
                r=m;
            }else{
                l=m+1;
            }
        }
        return r;
    }
    public int findDuplicate1(int[] nums) {
        int ret=-1;
        for(int i=0;i<nums.length;i++){
            if(nums[Math.abs(nums[i])]<0){
                ret=Math.abs(nums[i]);
            }
            nums[Math.abs(nums[i])]=-nums[Math.abs(nums[i])];
        }
        for(int i=0;i<nums.length;i++){
            nums[i]=Math.abs(nums[i]);
        }
        return ret;
    }
    public int findDuplicate2(int[] nums) {
        int n=nums.length-1;
        int bitMax=31;
        int ans=0;
        while((n>>bitMax)==0){
            bitMax--;
        }
        for(int bit=0;bit<=bitMax;bit++){
            int reBit=(1<<bit);
            int x=0;
            int y=0;
            for(int i=0;i<=n;i++){
                if((nums[i]&reBit)!=0){
                    x++;
                }
                if(i>0 && (i&reBit)!=0){
                    y++;
                }
            }
            if(x>y){
                ans |= reBit;
            }
        }
        return ans;
    }
    public int findDuplicate(int[] nums) {
        int slow=0;
        int fast=0;
        do{
            slow=nums[slow];
            fast=nums[nums[fast]];
        }while(slow!=fast);
        slow=0;
        while(slow!=fast){
            slow=nums[slow];
            fast=nums[fast];
        }
        return slow;
    }


    public int findSecondMinimumValue(TreeNode root) {
        int ret=-1;
        Queue<TreeNode>level=new LinkedList<>();
        level.offer(root);
        int num=1;
        int low=root.val;
        while(!level.isEmpty()){
            int tempNum=0;
            while(num>0){
                TreeNode temp=level.poll();
                if(temp.val>low){
                    ret=Math.min((ret==-1?Integer.MAX_VALUE:ret),temp.val);
                }else if(temp.val==low){
                    if(null!=temp.left){
                        level.offer(temp.left);
                        tempNum++;
                    }
                    if(null!=temp.right){
                        level.offer(temp.right);
                        tempNum++;
                    }
                }
                num--;
            }
            num=tempNum;
        }
        return ret;
    }
    public int subarraysDivByK(int[] A, int K) {
        Map<Integer,Integer>rem=new HashMap<>();
        int count=0;
        int sum=0;
        rem.put(0,1);
        for(int i:A){
            sum+=i;
            int remainder=sum%K;
            remainder=remainder<0?(remainder+K):remainder;
            rem.put(remainder,rem.getOrDefault(remainder,0)+1);
        }
        for(Map.Entry entry:rem.entrySet()){
            int val= (int) entry.getValue();
            count+=(val*(val-1))/2;
        }
        return count;
    }
    private String LinkedListToString(LinkedList<String>linkedString){
        StringBuffer sb=new StringBuffer();
        for(String s:linkedString){
            sb.append(s);
        }
        return sb.toString();
    }
    public String decodeString(String s) {
        LinkedList<String>stack=new LinkedList<>();
        int p=0;
        while(p<s.length()){
            char cur=s.charAt(p);
            if(Character.isDigit(cur)){
                StringBuffer dig=new StringBuffer();
                while(Character.isDigit(s.charAt(p))){
                    dig.append(s.charAt(p++));
                }
                stack.add(dig.toString());
            }else if(Character.isLetter(cur) || cur=='['){
                stack.add(String.valueOf(cur));
                p++;
            }else{
                p++;
                LinkedList<String>sub=new LinkedList<>();
                while(!"[".equals(stack.getLast())){
                    sub.add(stack.removeLast());
                }
                stack.removeLast();
                int timeRe=Integer.parseInt(stack.removeLast());
                Collections.reverse(sub);
                String strSub=LinkedListToString(sub);
                StringBuffer sb=new StringBuffer();
                while(timeRe-->0){
                    sb.append(strSub);
                }
                stack.add(sb.toString());
            }
        }
        return LinkedListToString(stack);
    }
    public int rob(int[] nums) {
        int max=0;
        int lMax=0;
        int llMax=0;
        for(int i=0;i<nums.length;i++){
            int tempMax=Math.max(llMax,lMax)+nums[i];
            llMax=lMax;
            lMax=max;
            max=tempMax;
        }
        return Math.max(max,lMax);
    }
    public int largestRectangleArea(int[] heights) {
        int[] maxList=new int[heights.length];
        int max=Integer.MIN_VALUE;
        for(int i=0;i<heights.length;i++){
            maxList[i]=0;
            boolean linked=true;
            for(int j=i-1;j>=0;j--){
                if(heights[i]>=heights[j]){
                    maxList[j]=maxList[j]+heights[j];
                    linked=false;
                    max=Math.max(max,maxList[j]);
                }else{
                    maxList[j]=0;
                    if(linked){
                        maxList[i]+=heights[i];
                    }
                }
            }
            max=Math.max(max,maxList[i]);
        }
        return max;
    }
    public boolean isSymmetric(TreeNode root) {
        Queue<TreeNode>l=new LinkedList<>();
        Queue<TreeNode>r=new LinkedList<>();
        l.add(root);
        r.add(root);
        while(!l.isEmpty() && !r.isEmpty()){
            TreeNode lTop=l.poll();
            TreeNode rTop=r.poll();
            if(null!=lTop && null!=rTop){
                if(lTop.val==rTop.val){
                    l.add(lTop.right);
                    l.add(lTop.left);
                    r.add(rTop.left);
                    r.add(rTop.right);
                }else{
                    return false;
                }
            }else{
                if(null==lTop && null==rTop){

                }else{
                    return false;
                }
            }
        }
        return true;
    }
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        List<Boolean>ret=new LinkedList<>();
        int max=0;
        for(int i:candies){
            max=Math.max(i,max);
        }
        int min=max-extraCandies;
        for(int i:candies){
            if(min>i){
                ret.add(false);
            }else{
                ret.add(true);
            }
        }
        return ret;
    }
    static class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    };
    public Node flatten(Node head) {
        Node left=head;
        while(null!=left){
            if(null!=left.child){
                Node right=left.next;
                Node childNode=left.child;
                left.next=childNode;
                childNode.prev=left;
                left.child=null;
                while(null!=childNode.next){
                    childNode=childNode.next;
                }
                if(null!=right){
                    childNode.next=right;
                    right.prev=childNode;
                }
            }
            left=left.next;
        }
        return head;
    }

    public void flatten(TreeNode root) {
        if(null==root){
            return;
        }
        Stack<TreeNode>rest=new Stack<>();
        TreeNode tail=root;
        while(null!=tail.left || null!=tail.right || !rest.isEmpty()){
            while(null!=tail){
                if(null!=tail.right){
                    rest.add(tail.right);
                }
                if(null!=tail.left){
                    tail.right=tail.left;
                    tail.left=null;
                }else{
                    tail.right=null;
                    break;
                }
                tail=tail.right;
            }
            if(!rest.isEmpty()){
                tail.right=rest.pop();
                tail=tail.right;
            }
        }
    }

    public int sumNums(int n) {
        int ans = 0, A = n, B = n + 1;
        boolean flag;

        flag = ((B & 1) > 0) && (ans += A) > 0;
        A <<= 1;
        B >>= 1;

        flag = ((B & 1) > 0) && (ans += A) > 0;
        A <<= 1;
        B >>= 1;

        flag = ((B & 1) > 0) && (ans += A) > 0;
        A <<= 1;
        B >>= 1;

        flag = ((B & 1) > 0) && (ans += A) > 0;
        A <<= 1;
        B >>= 1;

        flag = ((B & 1) > 0) && (ans += A) > 0;
        A <<= 1;
        B >>= 1;

        flag = ((B & 1) > 0) && (ans += A) > 0;
        A <<= 1;
        B >>= 1;

        flag = ((B & 1) > 0) && (ans += A) > 0;
        A <<= 1;
        B >>= 1;

        flag = ((B & 1) > 0) && (ans += A) > 0;
        A <<= 1;
        B >>= 1;

        flag = ((B & 1) > 0) && (ans += A) > 0;
        A <<= 1;
        B >>= 1;

        flag = ((B & 1) > 0) && (ans += A) > 0;
        A <<= 1;
        B >>= 1;

        flag = ((B & 1) > 0) && (ans += A) > 0;
        A <<= 1;
        B >>= 1;

        flag = ((B & 1) > 0) && (ans += A) > 0;
        A <<= 1;
        B >>= 1;

        flag = ((B & 1) > 0) && (ans += A) > 0;
        A <<= 1;
        B >>= 1;

        flag = ((B & 1) > 0) && (ans += A) > 0;
        A <<= 1;
        B >>= 1;

        return ans >> 1;
    }

    public int numWays(int n) {
        int max=1000000007;
        int l=1;
        int ll=0;
        for(int i=1;i<=n;i++){
            int temp=(l+ll)%max;
            ll=l;
            l=temp;
        }
        return n<0?0:l;
    }

    double new21Game(int N, int K, int W) {
        if(K<=0){
            return 1.0;
        }
        if(K-1+W<=N){
            return 1.0;
        }
        if(K>N){
            return 0.0;
        }
        double f=1.0/(double)W;
        double[] dp = new double[K+W];
        for(int i=K;i<=N && i<K+W-1;i++){
            dp[i]=1.0;
        }
        dp[K-1]=f*Math.min((N-K+1),W);
        for(int i=K-2;i>=0;i--){
            dp[i]=dp[i+1]+f*(dp[i+1]-dp[i+W+1]);
        }
        return dp[0];
    }

    public int[] productExceptSelf0(int[] nums) {
        int len=nums.length;
        int[] prefix=new int[len];
        int[] suffix=new int[len];
        prefix[0]=nums[0];
        suffix[len-1]=nums[len-1];
        for(int i=1;i<len;i++){
            prefix[i]=prefix[i-1]*nums[i];
            suffix[len-1-i]=suffix[len-i]*nums[len-i-1];
        }
        int[] ret=new int[len];
        for(int i=0;i<len;i++){
            ret[i]=(i>0?prefix[i-1]:1)*(i<len-1?suffix[i+1]:1);
        }
        return ret;
    }
    public int[] productExceptSelf(int[] nums) {
        int prt=1;
        int[] ret=new int[nums.length];
        for(int i=0;i<nums.length;i++){
            ret[i]=prt;
            prt*=nums[i];
        }
        prt=1;
        for(int i=nums.length-1;i>=0;i--){
            ret[i]*=prt;
            prt*=nums[i];
        }
        return ret;
    }
    public int minIncrementForUnique(int[] A) {
        Arrays.sort(A);
        int num=0;
        for(int i=1;i<A.length;i++){
            while(A[i]<=A[i-1]){
                num++;
                A[i]++;
            }
        }
        return num;
    }

    public int[] spiralOrder(int[][] matrix) {
        if(null==matrix || matrix.length<=0 || matrix[0].length<=0){
            return new int[0];
        }
        int iMax=matrix.length-1;
        int iMin=0;
        int jMin=0;
        int jMax=matrix[0].length-1;
        int len=(iMax+1)*(jMax+1);
        int[] ret =new int[len];
        int num=0;
        int i=iMin;
        int j=jMin-1;
        while(num<len){
            if(i==iMin+1 && j==jMin && jMin!=jMax){
                iMin++;
                jMin++;
                iMax--;
                jMax--;
            }
            if(i==iMin && j<jMax){
                j++;
            }else if(i<iMax && j==jMax){
                i++;
            }else if(i==iMax && j>jMin){
                j--;
            }else if(i>iMin && j==jMin){
                i--;
            }
            ret[num]=matrix[i][j];
            num++;
        }
        return ret;
    }

    public int longestConsecutive(int[] nums) {
        Map<Integer,Integer>len=new LinkedHashMap<>();
        Map<Integer, Boolean>visited=new LinkedHashMap<>();
        int lenMax=0;
        for(int i:nums){
            len.put(i,len.getOrDefault(i,0)+1);
            visited.put(i,false);
        }
        for(int i:nums){
            if(visited.get(i)){
                continue;
            }
            visited.put(i,true);
            int lenTemp=1;
            int l=i;
            while(visited.containsKey(--l)){
                visited.put(l,true);
                lenTemp+=len.get(i);
            }
            int r=i;
            while(visited.containsKey(++r)){
                visited.put(r,true);
                lenTemp+=len.get(i);
            }
            lenMax=Math.max(lenMax,lenTemp);
        }
        return lenMax;
    }

    public int longestConsecutiveWithoutRepeat(int[] nums) {
        Map<Integer, Boolean>visited=new LinkedHashMap<>();
        int lenMax=0;
        for(int i:nums){
            visited.put(i,false);
        }
        for(int i:nums){
            if(visited.get(i)){
                continue;
            }
            visited.put(i,true);
            int lenTemp=1;
            int l=i;
            while(visited.containsKey(--l)){
                visited.put(l,true);
                lenTemp++;
            }
            int r=i;
            while(visited.containsKey(++r)){
                visited.put(r,true);
                lenTemp++;
            }
            lenMax=Math.max(lenMax,lenTemp);
        }
        return lenMax;
    }
    public boolean equationsPossible(String[] equations) {
        int[] record=new int[26];
        Map<Integer,Set<Integer>>set=new HashMap<>();
        int num=1;
        for(String s:equations){
            if(s.charAt(1)=='!'){
                continue;
            }
            int a=s.charAt(0)-97;
            int b=s.charAt(3)-97;
            if(record[a]==0 && record[b]==0){
                record[a]=num;
                record[b]=num;
                Set<Integer>list =new LinkedHashSet<>();
                list.add(a);
                list.add(b);
                set.put(num,list);
                num++;
            }else if(record[a]!=0 && record[b]!=0 && record[a]!=record[b]){
                Set<Integer> l1,l2;
                l1=set.get(record[a]);
                l2=set.get(record[b]);
                int key=record[a];
                int lost=record[b];
                if(l1.size()<l2.size()){
                    lost=key;
                    key=record[b];
                    Set<Integer> t=l2;
                    l2=l1;
                    l1=t;
                }
                l1.addAll(l2);
                for(int i:l2){
                    record[i]=key;
                }
                set.remove(lost);
            }else{
                int in,out;
                if(record[a]==0){
                    out=a;
                    in=b;
                }else{
                    out=b;
                    in=a;
                }
                set.get(record[in]).add(out);
                record[out]=record[in];
            }
        }
        for(String s:equations){
            if(s.charAt(1)=='='){
                continue;
            }
            int a=s.charAt(0)-97;
            int b=s.charAt(3)-97;
            if(a==b){
                return false;
            }
            if(record[a]!=0 && record[b]!=0 && record[a]==record[b]){
                return false;
            }
        }
        return true;
    }
    public int translateNum(int num) {
        int[] dp=new int[2];
        int[] last=new int[2];
        last[0]=26;
        last[1]=26;
        dp[0]=1;
        while(num!=0){
            int re=num%10;
            num/=10;
            int temp=dp[0];
            if(re!=0 && re*10+last[0]<26){
                temp+=dp[1];
            }
            dp[1]=dp[0];
            dp[0]=temp;
            last[1]=last[0];
            last[0]=re;
        }
        return dp[0];
    }
    public int[] dailyTemperatures(int[] T) {
        int len=T.length;
        int[] ret=new int[len];
        //Stack<Integer>back=new Stack<>();
        Deque<Integer>back=new LinkedList<>();
        for(int i=0;i<len;i++){
            while(!back.isEmpty() && T[i]>T[back.peek()]){
                int j=back.pop();
                ret[j]=i-j;
            }
            back.push(i);
        }
        return ret;
    }

}
