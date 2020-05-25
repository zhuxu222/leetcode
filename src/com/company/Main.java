package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Solution solution=new Solution();
        int[] a={2,7,11,5};
        int t=9;
        int[] b =solution.twoSum(a,t);
        System.out.println(b);
    }
}
