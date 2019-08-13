package com.gjt.jdk8.methodref;

public class MyStringOps {
    private int val;

    public MyStringOps() {
        this.val = 0;
    }
    
    public MyStringOps(int val) {
        this.val = val;
    }
    
    public static String staticStrReverse(String str) {
        String result = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            result += str.charAt(i);
        }
        return result;
    }

    public String strReverse(String str) {
        String result = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            result += str.charAt(i);
        }
        return result;
    }
    
    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }
    
    public void printVal() {
        System.out.println(this.val);
    }
    
    
}
