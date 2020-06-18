package com.company;

public class StringOperation {
    //125. 验证回文串
    public boolean isDigitOrCase(char c){
        if(('A'<=c && c<='Z') || ('a'<=c && c<='z') || ('0'<=c && c<='9')){
            return true;
        }
        return false;
    }
    public boolean isPalindrome(String s) {
        int i=0;
        int j=s.length()-1;
        while(i<=j){
            while(i<=j && !isDigitOrCase(s.charAt(i))){
                i++;
            }
            while(i<=j && !isDigitOrCase(s.charAt(j))){
                j--;
            }
            if(i>=j){
                return true;
            }
            if(Character.isDigit(s.charAt(i))){
                if(s.charAt(i)!=s.charAt(j)){
                    return false;
                }
            }else{
                if(Character.toLowerCase(s.charAt(i))!=Character.toLowerCase(s.charAt(j))){
                    return false;
                }
            }
            i++;
            j--;
        }
        return true;
    }

}
