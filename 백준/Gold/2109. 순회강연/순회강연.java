import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2109
public class Main {
    private static final int DAY = 10_000;
    private static int N; // 대학 개수
    private static University[] university;

    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main

    private static void sol() {
        boolean[] checked = new boolean[DAY+1];
        int total = 0;
        int pay, day;

        for(int i=0; i<N; i++) {
            pay = university[i].pay;
            day = university[i].day;

            while (day > 1 && checked[day]) {
                day--;
            }

            if (!checked[day]) {
                checked[day] = true;
                total += pay;
            }
        }

        System.out.print(total);
    }//sol

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 대학 개수

        university = new University[N]; // 각 대학별 강연료, 날짜

        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            university[i] = new University(p, d);
        }

        Arrays.sort(university);
        br.close();
    }//init

    private static class University implements Comparable<University> {
        int pay;
        int day;

        University(int pay, int day) {
            this.pay = pay;
            this.day = day;
        }

        @Override
        public int compareTo(University u) {
            if (this.pay == u.pay) {
                return u.day - this.day;
            }
            return u.pay - this.pay;
        }
    }//University

}//class
/*
20
85 8
56 11
58 12
28 20
36 12
45 9
55 4
1 3
71 6
72 15
38 9
76 20
67 5
78 2
48 18
100 3
16 2
7 10
95 5
42 14
---
1050
 */