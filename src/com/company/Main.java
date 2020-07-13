package com.company;

import com.company.ArrayOperation.ArrayOperation;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Solution solution=new Solution();
        StringOperation so=new StringOperation();
        ArrayOperation ao=new ArrayOperation();

//        int s=7;
//        int c=ao.minSubArrayLen(s,a);
        //TreeNode b =BanaryTreeCodec.recoverFromPreorder(a);
        //String b =solution.longestCommonPrefix(a);

        int[] a={1,2,2,1};
        int[] b={2,2};
        int[] c=ao.intersect(a,b);
        System.out.println(c);
    }
}
