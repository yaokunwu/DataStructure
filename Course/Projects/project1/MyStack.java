import java.util.ArrayList;
import java.util.List;

public class MyStack {

    private List<Character> stack;

    public MyStack() {
        stack = new ArrayList<>();
    }

    public void push(char chr) {
        stack.add(chr);
    }

    public char pop() {
        return stack.remove(stack.size() - 1);
    }

    public char peek() {
        return stack.get(stack.size() - 1);
    }

    public int size() {
        return stack.size();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

}

class TestStack {

    /**
     *
     * @param str
     * @return whether the str is balanced symbols
     */
    public static boolean balanceingSymbols(String str) {
        MyStack stack = new MyStack();
        for (char chr : str.toCharArray()) {
            if (chr == '{' || chr == '[' || chr == '('){
                stack.push(chr);
            } else if (!stack.isEmpty()) {
                char top = stack.peek();
                if ((top == '{' && chr == '}' )
                        || (top == '[' && chr == ']')
                        || (top == '(' && chr == ')')) {
                    stack.pop();
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String[] testSymbols = new String[] {"[({}{})]", "[({}{})","[({}{}]","[({}{)]","[({{})]","[{}{})]", "[]{}" ,"(]","{"};
        boolean[] testResults = new boolean[] {true, false, false, false, false, false, true, false, false};

        boolean[] res = new boolean[testSymbols.length];
        for (int i = 0; i < testSymbols.length; i++) {
            res[i] = balanceingSymbols(testSymbols[i]);
        }

        for (int i = 0; i < res.length; i++) {
            System.out.println("True result     Algorithm result");
            System.out.print(testResults[i]);
            System.out.print("              ");
            System.out.println(res[i]);
        }
    }

}