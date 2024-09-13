import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/1138
public class Main {
    static int N; // 사람 수
    static int[] leftCount; // 왼쪽에 있는 나보다 큰 사람 수
    static int[] result; // 줄 결과

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        init();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            leftCount[i] = Integer.parseInt(st.nextToken());
        }

        getResult();
        print();
    }//main


    private static void print() {
        StringBuilder ans = new StringBuilder();

        for(int n : result) {
            ans.append(n).append(' ');
        }

        System.out.print(ans);
    }//print


    private static void getResult() {
        int cnt;

        for(int i=0; i<N; i++) {
            cnt = leftCount[i];

            for(int j=0; j<N; j++) {
                if(result[j] == 0) { // 자리 비어있음
                    if(cnt == 0) {
                        result[j] = i+1;
                        break;
                    }

                    cnt--;
                }
            }
        }

    }//getResult


    private static void init() {
        leftCount = new int[N]; // 왼쪽에 있는 나보다 큰 사람 수
        result = new int[N]; // 줄 결과
    }//init

    
}//class