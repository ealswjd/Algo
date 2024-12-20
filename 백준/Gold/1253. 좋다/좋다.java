import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1253
public class Main {
    private static int N;
    private static int[] numbers; // N개의 수


    public static void main(String[] args) throws IOException {
        init();

        int cnt = getCnt();
        System.out.print(cnt);
    }//main


    private static int getCnt() {
        int cnt = 0;

        for(int i=0; i<N; i++) {
            int left = 0;
            int right = N - 1;

            while(left < right) {
                int sum = numbers[left] + numbers[right];

                if(sum < numbers[i]) left++;
                else if(sum > numbers[i]) right--;
                else {
                    if(left == i) left++;
                    else if(right == i) right--;
                    else {
                        cnt++;
                        break;
                    }
                }
            }
            
        }

        return cnt;
    }//getCnt


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        numbers = new int[N]; // N개의 수

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(numbers);
        br.close();
    }//init


}//class