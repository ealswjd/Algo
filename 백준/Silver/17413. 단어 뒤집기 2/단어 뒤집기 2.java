import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// https://www.acmicpc.net/problem/17413
public class Main {
    private static char[] S; // 문자열 S


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() {
        StringBuilder ans = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        char c;

        for(int i=0; i<S.length; i++) {
            c = S[i];

            // 공백이나 태그가 시작되면 이전 단어 뒤집기
            if(c == ' ' || c == '<') {
                while(!stack.isEmpty()) {
                    ans.append(stack.pop());
                }
            }
            
            // 태그 시작. 태그 안의 단어는 안 뒤집음
            if(c == '<') {
                while(S[i] != '>') {
                    ans.append(S[i++]);
                }
                ans.append(S[i]);
                continue;
            }
           
            if(c != ' ') stack.add(c); // 공백 아니면 뒤집기
            else ans.append(c); 
        }

        // 남아있는 단어 뒤집기
        while(!stack.isEmpty()) {
            ans.append(stack.pop());
        }

        System.out.print(ans);
    }//sol


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine().toCharArray();

        br.close();
    }//init
    

}//class