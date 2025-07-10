import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/5464
public class Main {
    private static int N, M;
    private static int[] charge, weight; // 주차 공간들의 단위 무게당 요금, 차량의 무게
    private static int[] order; // 차량들의 주차장 출입 순서


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() {
        int total = 0;
        int[] parking = new int[M+1]; // i번 차량이 주차한 공간
        PriorityQueue<Integer> empty = new PriorityQueue<>(); // 비어있는 주차 공간
        Queue<Integer> waiting = new LinkedList<>(); // 대기 차량

        for(int i=1; i<=N; i++) {
            empty.offer(i);
        }

        for(int car : order) {
            car = Math.abs(car);

            if(parking[car] == 0) { // 출입
                waiting.offer(car);
            }
            else { // 출차
                empty.offer(parking[car]);
                parking[car] = 0;
            }

            // 대기 차량 주차
            while(!waiting.isEmpty() && !empty.isEmpty()) {
                int num = empty.poll(); // 빈 주차 공간
                int prevCar = waiting.poll(); // 대기 차량

                total += weight[prevCar] * charge[num];
                parking[prevCar] = num;
            }
        }

        // 대기 차량 주차
        while(!waiting.isEmpty() && !empty.isEmpty()) {
            int num = parking[empty.poll()]; // 빈 주차 공간
            int prevCar = waiting.poll(); // 대기 차량

            total += weight[prevCar] * charge[num];
            parking[prevCar] = num;
        }

        System.out.print(total);
    }//sol


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 주차 공간 개수
        M = Integer.parseInt(st.nextToken()); // 차량 개수

        charge = new int[N+1]; // 주차 공간들의 단위 무게당 요금
        weight = new int[M+1]; // 차량들의 무게
        order = new int[M*2]; // 차량들의 주차장 출입 순서

        // 주차 공간들의 단위 무게당 요금을 나타내는 정수들이 주어진다.
        for(int i=1; i<=N; i++) {
            charge[i] = Integer.parseInt(br.readLine());
        }

        // 차량들의 무게를 나타내는 정수들이 주어진다
        for(int i=1; i<=M; i++) {
            weight[i] = Integer.parseInt(br.readLine());
        }

        // 차량들의 주차장 출입 순서를 나타내는 정수들이 한 줄에 하나씩 주어진다.
        for(int i=0; i<M*2; i++) {
            order[i] = Integer.parseInt(br.readLine());
        }

        br.close();
    }//init


}//class