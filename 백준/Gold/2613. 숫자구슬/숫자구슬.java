import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2613
public class Main {
    private static int N, M; // 구슬의 개수, 그룹의 수
    private static int min, total; // 가장 작은 수, 구슬 수의 총합
    private static int[] numbers; // 각 구슬에 적혀진 숫자
    private static int[] counts; // 각 그룹을 구성하는 구슬의 개수


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() {
        StringBuilder ans = new StringBuilder();

        int result = getMin();

        // M개의 그룹으로 나누었을 때 그 최댓값을 첫째 줄에 출력
        ans.append(result).append('\n');
        // 둘째 줄에는 각 그룹을 구성하는 구슬의 개수를 왼쪽부터 순서대로 출력
        for(int i=0; i<M; i++) {
            ans.append(counts[i]).append(' ');
        }

        System.out.print(ans);
    }//sol


    private static int getMin() {
        int start = min;
        int end = total;
        int result = total;
        int mid;

        while(start <= end) {
            mid = (start + end) / 2;

            if(isAvailable(mid)) {
                end = mid - 1;
                result = mid;
            }
            else {
                start = mid + 1;
            }
        }

        return result;
    }//getMin


    private static boolean isAvailable(int mid) {
        int[] tmpCounts = new int[M];
        int group = 1; // 현재 그룹 개수
        int cnt = 0; // 구슬 개수
        int sum = 0; // 현재 그룹의 합

        for(int i=0; i<N; i++) {
            // 그룹의 개수가 M 개보다 많거나 구슬 숫자가 mid 보다 큰 경우
            if(group > M || numbers[i] > mid) return false;

            // 합이 mid 보다 크거나 남은 구슬이 남은 그룹 개수와 같음
            if(sum + numbers[i] > mid || N-i <= M - group) {
                // 현재 그룹 마감
                tmpCounts[group - 1] = cnt;
                sum = numbers[i];
                group++;
                cnt = 1;
            }
            else {
                // 그룹에 현재 구슬 추가
                sum += numbers[i];
                cnt++;
            }
        }

        // M개로 불가능
        if(group != M) return false;

        tmpCounts[M-1] = cnt;
        for(int i=0; i<M; i++) {
            counts[i] = tmpCounts[i];
        }

        return true;
    }//isAvailable


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 구슬의 개수
        M = Integer.parseInt(st.nextToken()); // 그룹의 수

        numbers = new int[N]; // 각 구슬에 적혀진 숫자
        counts = new int[M]; // 각 그룹을 구성하는 구슬의 개수
        min = 100; // 구슬에 적혀진 숫자는 100 이하의 자연수

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());

            total += numbers[i];
            min = Math.min(min, numbers[i]);
        }

        br.close();
    }//init


}//class