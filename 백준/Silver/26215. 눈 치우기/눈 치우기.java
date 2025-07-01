import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/26215
public class Main {
    private static final int MAX = 1440;
    private static int N; // 집의 수
    private static PriorityQueue<Integer> snow; // 각각의 집 앞에 쌓여 있는 눈의 양


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() {
        int totalTime = 0;
        int max, min, time;

        while(!snow.isEmpty()) {

            if(snow.size() < 2) {
                totalTime += snow.poll();
            }
            else {
                max = snow.poll();
                min = snow.poll();

                if(snow.isEmpty()) {
                    totalTime += min;
                    max -= min;
                    if(max > 0) snow.add(max);
                }
                else {
                    time = 1;
                    while(snow.peek() <= min - time) {
                        time++;
                    }

                    max -= time;
                    min -= time;
                    totalTime += time;

                    if(max > 0) snow.add(max);
                    if(min > 0) snow.add(min);
                }

            }

            // 24시간(1440분)이 넘게 걸릴 경우 중단
            if(totalTime > MAX) {
                totalTime = -1;
                break;
            }
        }

        // 24시간(1440분)이 넘게 걸릴 경우 -1을 출력
        System.out.print(totalTime);
    }//sol


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 집의 수

        snow = new PriorityQueue<>(Comparator.reverseOrder());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            // 집 앞에 쌓여 있는 눈의 양을 나타내는 정수
            int a = Integer.parseInt(st.nextToken());
            snow.add(a);
        }

        br.close();
    }//init


}//class