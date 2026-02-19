import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/25330
public class Main {
    private static int N, K; // 몬스터의 수, 초기 체력
    private static int max; // 해방시킬 수 있는 주민들의 최대 수
    private static Village[] villages; // 각 마을에 있는 몬스터의 공격력, 주민의 수

    private static class Village implements Comparable<Village> {
        int a, p;
        Village(int a, int p) {
            this.a = a;
            this.p = p;
        }
        @Override
        public int compareTo(Village o) {
            return this.a - o.a; // 공격력 오름차순 정렬
        }
    }//Village

    public static void main(String[] args) throws IOException {
        init();
        sol(0, 0, 0, 0);

        System.out.print(max);
    }//main

    static void sol(int cur, int damage, int prev, int people) {
        max = Math.max(max, people);

        for(int i=cur; i<N; i++) {
            int sum = prev + villages[i].a; // 이전 공격들 합 + 현재 공격
            int nDamage = damage + sum; // 지금까지 입은 공격 + 이번 마을에서의 공격
            int nPeople = people + villages[i].p; // 구할 수 있는 주민의 수

            if (nDamage <= K) {
                sol(i + 1, nDamage, sum, nPeople);
            } else {
                break;
            }
        }
    }//sol

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 몬스터의 수 N, 초기 체력 K가 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[] a = new int[N]; // 공격력
        int[] p = new int[N]; // 주민의 수
        villages = new Village[N]; // 각 마을의 공격력, 주민의 수

        // 각 마을에 있는 몬스터의 공격력
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        // 각 마을에 있는 주민의 수
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            p[i] = Integer.parseInt(st.nextToken());
        }
        br.close();

        for(int i=0; i<N; i++) {
            villages[i] = new Village(a[i], p[i]);
        }

        Arrays.sort(villages);
    }//init

}//class