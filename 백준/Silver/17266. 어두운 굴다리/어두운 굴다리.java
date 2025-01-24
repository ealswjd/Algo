import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17266
public class Main {
    private static int N; // 굴다리의 길이
    private static int M; // 가로등의 개수
    private static int[] positions; // 가로등의 위치


    public static void main(String[] args) throws IOException {
        init();

        // 굴다리의 길이 N을 모두 비추기 위한 가로등의 최소 높이를 출력
        int height = getHeight();
        System.out.print(height);
    }//main


    private static int getHeight() {
        int height = N; // 가로등의 최소 높이
        int start = 1;
        int end = N;
        int mid;

        while(start <= end) {
            mid = (start + end) / 2;

            if(isPossible(mid)) {
                end = mid - 1;
                height = mid;
            }
            else {
                start = mid + 1;
            }
        }

        return height;
    }//getHeight


    private static boolean isPossible(int height) {
        int start = positions[0] - height; // 불빛 시작
        int end = positions[0] + height; // 불빛 끝

        // 0부터 밝아야 함
        if(start > 0) return false;

        for(int i=1; i<M; i++) {
            int curStart = positions[i] - height;
            int curEnd = positions[i] + height;

            // 중간에 공백 있으면 불가능
            if(curStart > end) return false;
            end = curEnd;
        }

        // 끝까지 밝힐 수 있는지 확인
        return end >= N;
    }//isPossible


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 굴다리의 길이
        M = Integer.parseInt(br.readLine()); // 가로등의 개수

        positions = new int[M]; // 가로등의 위치

        // 가로등의 위치는 오름차순으로 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) {
            positions[i] = Integer.parseInt(st.nextToken());
        }

        br.close();
    }//init


}//class