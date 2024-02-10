import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static final int INF = 1_000_000_000;
    static int N;
    static int[] snow;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        snow = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            snow[i] = Integer.parseInt(st.nextToken());
        }//for
        br.close();

        int min = getMin();
        System.out.print(min);
    }//main

    private static int getMin() {
        Arrays.sort(snow);
        int min = INF;
        int snowMan1, snowMan2, start, end;

        for(int i=0; i<N; i++) {
            for(int j=i+1; j<N; j++) {
                snowMan1 = snow[i] + snow[j];
                start = 0;
                end = N-1;
                while(start < end) {
                    if(start == i || start == j) {
                        start++;
                        continue;
                    }
                    if(end == i || end == j) {
                        end--;
                        continue;
                    }
                    snowMan2 = snow[start] + snow[end];
                    min = Math.min(min, Math.abs(snowMan1 - snowMan2));

                    if(snowMan1 < snowMan2) end--;
                    else if(snowMan1 > snowMan2) start++;
                    else return 0;
                }//while
            }//j
        }//i

        return min;
    }//getMin

}//class