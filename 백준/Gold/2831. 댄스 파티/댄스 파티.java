import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2831
public class Main {
    static int N;
    static int[] man, woman;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        man = new int[N];
        woman = new int[N];

        init(man, new StringTokenizer(br.readLine()));
        init(woman, new StringTokenizer(br.readLine()));
        br.close();

        int cnt = getCnt();
        System.out.print(cnt);
    }//main

    private static int getCnt() {
        int cnt = 0;
        int s=0, e=N-1;

        while(s < N && e >= 0) {
            if(man[s] < 0 && woman[e] > 0) {
                if(Math.abs(man[s]) <= woman[e]) e--;
                else {
                    cnt++;
                    s++;
                    e--;
                }
            }else if(man[s] > 0 && woman[e] < 0) {
                if(man[s] >= Math.abs(woman[e])) e--;
                else {
                    cnt++;
                    s++;
                    e--;
                }
            }else {
                if(man[s] < 0 && woman[e] < 0) s++;
                else e--;
            }
        }//while

        return cnt;
    }//getCnt

    private static void init(int[] arr, StringTokenizer st) {
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }//for
        Arrays.sort(arr);
    }//init

}//class