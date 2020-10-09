package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BanaryTreeCodec {
    //genarate tree
//    public TreeNode generateTree(Integer[] numList){
//
//    }
    // Encodes a tree to a single string.
//    public String serialize(TreeNode root) {
//        Stack<TreeNode>upNode=new Stack<>();
//        StringBuilder res=new StringBuilder();
//        upNode.push(root);
//        res.append(root.val);
//        while(){
//            while(null!=upNode.peek()){
//                upNode.push(upNode.peek().left);
//                if(null!=upNode.peek().left){
//                    res.append(upNode.peek().val);
//                }else{
//                    res.append("null");
//                }
//            }
//            while(null==upNode.peek().right){
//                res.append("null");
//                upNode.pop();
//            }
//
//
//        }
//        return null;
//    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        return null;
    }

    //    1028. 从先序遍历还原二叉树
    public static TreeNode recoverFromPreorder(String S) {
        if(null==S || S.length()<=0){
            return null;
        }
        Stack<TreeNode>upNode=new Stack<>();
        int len=S.length();
        int[] deepList=new int[len];
        int i=0;
        int j=-1;
        while(i<len){
            int deep=i;
            while(i<len && S.charAt(i)=='-'){
                i++;
            }
            deep=i-deep;
            int num=0;
            while(i<len && S.charAt(i)!='-'){
                num=num*10+(S.charAt(i)-48);
                i++;
            }
            //int num= Integer.parseInt(S.substring(i,j));
            while(j>=0 && deep<=deepList[j]){
                j--;
                upNode.pop();
            }
            deepList[++j]=deep;
            TreeNode temp=new TreeNode(num);
            if(!upNode.isEmpty()){
                if( null==upNode.peek().left){
                    upNode.peek().left=temp;
                }else{
                    upNode.peek().right=temp;
                }
            }
            upNode.push(temp);
        }
        return upNode.firstElement();
    }


    //112. 路径总和
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root==null){
            return false;
        }
        if(root.left==null && root.right==null){
            return sum==root.val;
        }
        return (hasPathSum(root.left,sum-root.val) || hasPathSum(root.right,sum-root.val));
    }
    public boolean hasPathSumBuffer(TreeNode root, int sum) {
        Stack<Integer>sumStatck=new Stack<>();
        return false;
    }

    //面试题 16.11. 跳水板
    public int[] divingBoard(int shorter, int longer, int k) {
        if(k<=0){
            int[] a={};
            return a;
        }
        int dif=longer-shorter;
        if(dif==0){
            int[] a={shorter*k};
            return a;
        }
        int[] res=new int[k+1];
        res[0]=k*shorter;
        for(int i=1;i<=k;i++){
            res[i]=res[i-1]+dif;
        }
        return res;
    }

    //538. 把二叉搜索树转换为累加树
    //给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，使得每个节点的值是原来的节点值加上所有大于它的节点值之和。
    public TreeNode convertBST(TreeNode root) {
        if (null==root){
            return null;
        }
        Stack<TreeNode> nodeStack=new Stack<>();
        nodeStack.push(root);
        int sum=0;

        while(null!=nodeStack.peek().right){
            nodeStack.push(nodeStack.peek().right);
        }

        while(!nodeStack.isEmpty()){
            TreeNode tempNode=nodeStack.pop();
            sum+=tempNode.val;
            tempNode.val=sum;
            if (null!=tempNode.left){
                nodeStack.push(tempNode.left);
                while(null!=nodeStack.peek().right){
                    nodeStack.push(nodeStack.peek().right);
                }
            }
        }
        return root;
    }

    int convertBST1Sum=0;
    public TreeNode convertBST1(TreeNode root) {
        if (null==root){
            return null;
        }
        convertBST1(root.right);
        convertBST1Sum+=root.val;
        root.val=convertBST1Sum;
        convertBST1(root.left);
        return root;
    }
//    501. 二叉搜索树中的众数
//    给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。
//    假定 BST 有如下定义：
//    结点左子树中所含结点的值小于等于当前结点的值 结点右子树中所含结点的值大于等于当前结点的值左子树和右子树都是二叉搜索树
//    public int[] findMode(TreeNode root) {
//
//    }
//    public void checkNode(modeData data,TreeNode root){
//        if (null==root.left){
//            checkNode(data,root.left);
//        }
//        if (root.val==data.tempValue){
//            data.tempTimes++;
//            if (data.tempTimes>maxTimesValue){
//
//            }
//        }
//
//        if (null==root.right){
//            checkNode(data,root.right);
//        }
//
//
//    }

//    235. 二叉搜索树的最近公共祖先
//    给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
//    百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，
//    最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大
//    （一个节点也可以是它自己的祖先）。”
//    例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        int min,max;
        if (p.val==q.val){
            return p;
        }else if (p.val>q.val){
            min=q.val;
            max=p.val;
        }else{
            min=p.val;
            max=q.val;
        }
        TreeNode tempNode=root;
        while(null!=tempNode){
            if (tempNode.val>max){
                tempNode=tempNode.left;
            }else if (tempNode.val<min){
                tempNode=tempNode.right;
            }else{
                return tempNode;
            }
        }
        return null;
    }


    class modeData{
        int tempValue=0;
        int tempTimes=0;
        int maxTimes=0;
        int maxTimesValue=0;
        int[] mode;
    }


//    617. 合并二叉树
//    给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
//    你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，
//    否则不为 NULL 的节点将直接作为新二叉树的节点。
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (null==t1 && null==t2){
            return null;
        }
        TreeNode merged=new TreeNode(0);
        if (null==t1){
            merged.val=t2.val;
            merged.right=t2.right;
            merged.left=t2.left;
            return merged;
        }
        if (null==t2){
            merged.val=t1.val;
            merged.right=t1.right;
            merged.left=t1.left;
            return merged;
        }
        merged.val=t1.val+t2.val;
        merged.left=mergeTrees(t1.left,t2.left);
        merged.right=mergeTrees(t1.right,t2.right);
        return merged;
    }


}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}


