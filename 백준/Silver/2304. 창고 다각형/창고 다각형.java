import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/2304
public class Main {
      
    private static class Pillar implements Comparable<Pillar> {
        int position; // 위치
        int height;   // 높이
        Pillar(int position, int height) {
            this.position = position;
            this.height = height;
        }
        @Override
        public int compareTo(Pillar p) {
            return this.position - p.position; // 위치 기준 오름차순
        }
    }//Pillar
    

    private static int N, maxH; // 기둥 개수, 가장 높은 기둥의 높이
    private static Pillar[] pillars; // 기둥 정보


    public static void main(String[] args) throws IOException {
        init();
        int area = getArea();

        System.out.print(area);
    }//main


    private static int getArea() {
        // 1. 가장 높은 기둥 구간 구하기
        int leftMaxIdx = 0, rightMaxIdx = 0;
        
        // 왼쪽
        for(int i=0; i<N; i++) {
            if(pillars[i].height == maxH) {
                leftMaxIdx = i;
                break;
            }
        }
        // 오른쪽
        for(int i=N-1; i>=leftMaxIdx; i--) {
            if(pillars[i].height == maxH) {
                rightMaxIdx = i;
                break;
            }
        }

        int cnt = pillars[rightMaxIdx].position - pillars[leftMaxIdx].position + 1;
        int area = maxH * cnt; // 총 면적
        int prevMax, prevPos;

        // 2. 왼쪽 -> leftMaxIdx 기둥까지
        prevPos = pillars[0].position;
        prevMax = pillars[0].height;
        for(int i=1; i<=leftMaxIdx; i++) {
            if(pillars[i].height >= prevMax) {
                cnt = pillars[i].position - prevPos;
                area += prevMax * cnt;

                prevPos = pillars[i].position;
                prevMax = pillars[i].height;
            }
        }

        // 3. 오른쪽 -> rightMaxIdx 기둥까지
        prevPos = pillars[N-1].position;
        prevMax = pillars[N-1].height;
        for(int i=N-2; i>=rightMaxIdx; i--) {
            if(pillars[i].height >= prevMax) {
                cnt = prevPos - pillars[i].position;
                area += prevMax * cnt;

                prevPos = pillars[i].position;
                prevMax = pillars[i].height;
            }
        }

        return area;
    }//getArea


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 기둥 개수
        pillars = new Pillar[N]; // 기둥 정보

        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int position = Integer.parseInt(st.nextToken()); // 각 기둥의 왼쪽 면의 위치
            int height = Integer.parseInt(st.nextToken()); // 각 기둥의 높이

            pillars[i] = new Pillar(position, height);
            maxH = Math.max(maxH, height);
        }

        Arrays.sort(pillars);
        br.close();
    }//init


}//class