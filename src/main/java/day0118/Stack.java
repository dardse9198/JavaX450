package day0118;

import java.util.ArrayList;

public class Stack {
    private ArrayList<String> stackArray = new ArrayList<String>();

    public void push(String data) {
        stackArray.add(data);
    }

    public String pop() {
        int len = stackArray.size();
        if(len==0) {
            System.out.println("빈 스택입니다");
            return null;
        }
        return (stackArray.remove(len-1));
    }

    public void print() {
        int totalElements = stackArray.size();
        for (int index = 0; index < totalElements; index++) {
            System.out.println(stackArray.get(index));
        }
    }
}
