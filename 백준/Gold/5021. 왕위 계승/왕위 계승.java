import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/5021
public class Main {
    private static Map<String, List<String>> family; // 가족 정보
    private static Map<String, Integer> inDegree; 
    private static String[] candidate; // 왕위 계승 후보
    private static String king; // 유토피아를 세운 사람

    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main

    private static void sol() {
        Map<String, Double> result = new HashMap<>(); // 혈통 점수
        Queue<String> q = new LinkedList<>();

        // 왕 먼저 넣기
        q.offer(king);
        result.put(king, 1.0);

        // 왕족 아닌 사람들 넣기
        for(String name : inDegree.keySet()) {
            if (inDegree.get(name) == 0 && !name.equals(king)) {
                q.offer(name);
                result.put(name, 0.0);
            }
        }

        while(!q.isEmpty()) {
            String cur = q.poll();

            for(String child : family.get(cur)) {
                if (inDegree.get(child) == 0) continue;

                // 자식한테 혈통 물려주기
                double score = result.getOrDefault(child, 0.0) + result.get(cur) / 2.0;
                result.put(child, score);
                inDegree.put(child, inDegree.get(child) - 1);

                // 부모에게 모두 물려받았고, 자식이 있다면 큐에 넣기
                if (inDegree.get(child) == 0 && family.get(child) != null) {
                    q.offer(child);
                }
            }
        }

        double score = -1.0; // 혈통 점수
        String ans = ""; // 왕위 계승자

        for(String name : candidate) {
            // 현재 후보의 혈통 점수
            double cur = result.getOrDefault(name, 0.0);

            if (score < cur) {
                ans = name;
                score = cur;
            }
        }

        System.out.print(ans);
    }//sol

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 가족 정보 개수
        int M = Integer.parseInt(st.nextToken()); // 왕위 계승 후보 수

        family = new HashMap<>(); // 가족 정보
        inDegree = new HashMap<>();
        candidate = new String[M]; // 왕위 계승 후보

        king = br.readLine(); // 초기 왕

        // N개 줄에는 가족 정보가 주어진다.
        while(N-- > 0) {
            st = new StringTokenizer(br.readLine());
            String child = st.nextToken();
            String p1 = st.nextToken();
            String p2 = st.nextToken();

            inDegree.put(child, 2);
            inDegree.putIfAbsent(p1, 0);
            inDegree.putIfAbsent(p2, 0);

            family.putIfAbsent(p1, new ArrayList<>());
            family.putIfAbsent(p2, new ArrayList<>());
            family.get(p1).add(child);
            family.get(p2).add(child);
        }

        // M개 줄에는 왕위를 계승받기를 주장하는 사람의 이름이 주어진다
        for(int i=0; i<M; i++) {
            candidate[i] = br.readLine();
        }

        br.close();
    }//init

}//class