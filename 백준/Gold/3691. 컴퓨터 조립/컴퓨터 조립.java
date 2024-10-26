import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 https://www.acmicpc.net/problem/3691
 컴퓨터의 성능은 가장 안 좋은 부품의 성능과 같다.
 예산을 초과하지 않으면서 가장 안 좋은 부품의 성능을 최대로 하려고 한다.
 */
public class Main {
    private static final int P = 0, Q = 1; // 가격, 성능
    private static final int INF = 1_000_000_000;
    private static int B; // 상근이의 예산
    private static int min, max; // 낮은 성능, 높은 성능
    private static Map<String, List<int[]>> parts; // 컴퓨터 부품

    
    public static void main(String[] args) throws IOException {
        StringBuilder ans = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            init(br);

            int quality = getMaxQuality();
            ans.append(quality).append('\n');
        }

        System.out.print(ans);
    }//main


    private static int getMaxQuality() {
        int quality = 0;
        int start = min;
        int end = max + 1;
        int mid;

        while(start < end) {
            mid = (start + end) / 2;

            if(isPossible(mid)) {
                start = mid + 1;
                quality = mid;
            }
            else end = mid;
        }

        return quality;
    }//getMaxQuality


    private static boolean isPossible(int quality) {
        int cnt = 0; // 조립 가능한 부품 개수
        int total = 0; // 총 가격

        for(String type : parts.keySet()) {
            int minPrice = INF;

            for(int[] part : parts.get(type)) {
                if(part[Q] < quality) continue;
                minPrice = Math.min(minPrice, part[P]);
            }

            // 해당 부품 조립 불가능
            if(minPrice == INF || total + minPrice > B) return false;

            total += minPrice;
            cnt++;
        }

        return cnt == parts.size();
    }//isPossible


    private static void init(BufferedReader br) throws IOException  {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 부품의 개수
        B = Integer.parseInt(st.nextToken()); // 상근이의 예산

        parts = new HashMap<>(); // 부품
        min = INF;  // 가장 낮은 성능
        max = 0;    // 가장 높은 성능

        while(N-- > 0) {
            st = new StringTokenizer(br.readLine());
            String type = st.nextToken(); // 부품의 종류
            st.nextToken(); // 부품의 이름
            int price = Integer.parseInt(st.nextToken()); // 가격
            int quality = Integer.parseInt(st.nextToken()); // 성능

            parts.putIfAbsent(type, new ArrayList<>());
            parts.get(type).add(new int[] {price, quality});

            min = Math.min(min, quality);
            max = Math.max(max, quality);
        }

        // 성능 오름차순 정렬
        for(String type : parts.keySet()) {
            parts.get(type).sort((o1, o2) -> o1[Q] - o2[Q]);
        }
    }//init

    
}//class