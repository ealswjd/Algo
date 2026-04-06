import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// https://www.acmicpc.net/problem/11899
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] S = br.readLine().toCharArray(); // 올바르지 않은 괄호열

        br.close();

        Stack<Character> open = new Stack<>(); // 여는 괄호 저장
        int cnt = 0; // 앞과 뒤에 붙여야 할 괄호의 최소 개수

        for(char c : S) {
            if (c == '(') { // 여는 괄호
                open.add(c);
            } else { // 닫는 괄호
                if (open.isEmpty()) cnt++; // 여는 괄호 없으면 괄호 추가
                else open.pop(); // 올바른 괄호
            }
        }

        // 올바른 괄호열으로 만들기 위해 앞과 뒤에 붙여야 할 괄호의 최소 개수를 출력
        System.out.print(cnt + open.size());
    }//main

}//class