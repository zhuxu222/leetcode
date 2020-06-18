package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Solution solution=new Solution();

        String a="1-2--3--4-5--6--7";
        TreeNode b =BanaryTreeCodec.recoverFromPreorder(a);
        //String b =solution.longestCommonPrefix(a);
        System.out.println(b);
        System.out.println((-6)%5);
    }
}
