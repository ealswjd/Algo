import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/13335
public class Main {
    static int N, W, L;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 다리를 건너는 트럭의 수
        W = Integer.parseInt(st.nextToken()); // 다리의 길이
        L = Integer.parseInt(st.nextToken()); // 다리의 최대하중

        Queue<Integer> trucks = new LinkedList<>();
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            trucks.offer(Integer.parseInt(st.nextToken()));
        }
        br.close();

        int time = getTime(trucks);
        System.out.print(time);
    }//main

    private static int getTime(Queue<Integer> trucks) {
        Queue<Integer> bridge = new LinkedList<>();
        int time = W;
        int sum = 0;

        while(!trucks.isEmpty()) {
            // 다리에 자리 있음
            if(bridge.size() < W) {
                if(sum + trucks.peek() <= L) { // 트럭 올라갈 수 있음
                    sum += trucks.peek();
                    bridge.offer(trucks.poll());
                }
                else { // 못올라감
                    for(int i=bridge.size(); i<W; i++) {
                        bridge.offer(0);
                    }
                }
            }
            else { // 다리 꽉 참 -> 트럭 빠져나가기
                sum -= bridge.poll();
                time++;
            }
        }


        return time + bridge.size();
    }//getTime

}//class