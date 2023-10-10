import org.w3c.dom.Node;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String str = st.nextToken();

        Stack<Character> stack = new Stack<>();

        int ans = 0;

        int tmp = 1;

        boolean flag = false;

        for(int i=0;i<str.length();i++){
            if (str.charAt(i) == '(') {
                tmp *= 2;
                stack.push('(');
            } else if (str.charAt(i) == '[') {
                tmp *= 3;
                stack.push('[');
            } else if (str.charAt(i) == ')') {
                if(stack.isEmpty() || stack.peek() != '('){
                    flag = true;
                    break;
                }

                if(str.charAt(i-1) == '('){
                    ans += tmp;
                }

                stack.pop();
                tmp /= 2;
            } else if (str.charAt(i) == ']') {
                if(stack.isEmpty() || stack.peek() != '['){
                    flag = true;
                    break;
                }

                if(str.charAt(i-1) == '['){
                    ans += tmp;
                }

                stack.pop();
                tmp /= 3;
            } else{
                flag = true;
                break;
            }
        }

        if (flag || !stack.isEmpty()) {
            bw.write(0+"\n");
        }else{
            bw.write(ans+"\n");
        }

        bw.flush();


    }
}