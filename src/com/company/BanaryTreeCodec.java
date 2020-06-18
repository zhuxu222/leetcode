package com.company;

import java.util.Stack;

public class BanaryTreeCodec {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        return null;
    }

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
}

