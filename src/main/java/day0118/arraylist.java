package day0118;

public class arraylist {
    public static void main(String[] args) {
        Stack stack = new Stack();

        stack.push("A");
        stack.push("B");

        System.out.println(stack.pop());
        System.out.println(stack.pop());

        qoduf s = new qoduf(5);
        s.push(5);
        s.pop();
    }
}
