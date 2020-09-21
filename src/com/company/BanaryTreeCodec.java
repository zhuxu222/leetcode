package com.company;

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

    public TreeNode convertBST2(TreeNode root) {
        if (null==root){
            return null;
        }
        return null;
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


