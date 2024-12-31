import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2024
public class Main {
    private static int M;
    private static List<Line> lines;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        M = Integer.parseInt(br.readLine());

        StringTokenizer st;
        int start, end;
        lines = new ArrayList<>();

        while(true) {
            st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());

            if(start == 0 && end == 0) break;
            if(end <= 0) continue;

            start = Math.max(start, 0);
            lines.add(new Line(start, end));
        }

        int cnt = getCnt();
        System.out.print(cnt);
    }//main


    private static int getCnt() {
        if(lines.isEmpty()) return 0;
        Collections.sort(lines);

        int cnt = 0;
        int end = 0;
        int max = 0;

        for(Line line : lines) {
            if(max >= M) return ++cnt;

            if(end < line.start) {
                if(max == end) return 0;

                end = max;
                cnt++;
            }

            max = Math.max(max, line.end);
        }

        if(max >= M) return ++cnt;

        return 0;
    }//getCnt


    private static class Line implements Comparable<Line> {
        int start;
        int end;

        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Line o) {
            return this.start - o.start;
        }
    }//Line


}//class