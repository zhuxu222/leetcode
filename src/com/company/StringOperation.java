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

    public String addBinary(String a, String b) {
        int lena=a.length();
        int lenb=b.length();
        int len=Math.max(lena,lenb);
        int i=0;
        StringBuilder sb=new StringBuilder();
        int carry=0;
        while(i<len || carry>0){
            int ca=0;
            if(i<lena){
                ca=a.charAt(lena-1-i)-'0';
            }
            int cb=0;
            if(i<lenb){
                cb=b.charAt(lenb-1-i)-'0';
            }
            int sum=ca+cb+carry;
            int rest=sum%2;
            carry=sum/2;
            sb.insert(0,rest);
            ++i;
        }
        return sb.toString();
    }





















    //面试题 16.18. 模式匹配
















    
    public boolean patternMatching(String pattern, String value) {
        int count_a = 0, count_b = 0;
        for (char ch: pattern.toCharArray()) {
            if (ch == 'a') {
                ++count_a;
            } else {
                ++count_b;
            }
        }
        if (count_a < count_b) {
            int temp = count_a;
            count_a = count_b;
            count_b = temp;
            char[] array = pattern.toCharArray();
            for (int i = 0; i < array.length; i++) {
                array[i] = array[i] == 'a' ? 'b' : 'a';
            }
            pattern = new String(array);
        }
        if (value.length() == 0) {
            return count_b == 0;
        }
        if (pattern.length() == 0) {
            return false;
        }
        for (int len_a = 0; count_a * len_a <= value.length(); ++len_a) {
            int rest = value.length() - count_a * len_a;
            if ((count_b == 0 && rest == 0) || (count_b != 0 && rest % count_b == 0)) {
                int len_b = (count_b == 0 ? 0 : rest / count_b);
                int pos = 0;
                boolean correct = true;
                String value_a = "", value_b = "";
                for (char ch: pattern.toCharArray()) {
                    if (ch == 'a') {
                        String sub = value.substring(pos, pos + len_a);
                        if (value_a.length() == 0) {
                            value_a = sub;
                        } else if (!value_a.equals(sub)) {
                            correct = false;
                            break;
                        }
                        pos += len_a;
                    } else {
                        String sub = value.substring(pos, pos + len_b);
                        if (value_b.length() == 0) {
                            value_b = sub;
                        } else if (!value_b.equals(sub)) {
                            correct = false;
                            break;
                        }
                        pos += len_b;
                    }
                }
                if (correct && !value_a.equals(value_b)) {
                    return true;
                }
            }
        }
        return false;
    }



}
