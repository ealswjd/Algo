import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/14713
public class Main {
    private static Map<String, Integer> wordMap; // 단어를 말한 앵무새
    private static List<Queue<String>> birds; // 각 앵무새가 말한 문장
    private static String[] L; // cseteram이 받아 적은 문장 L


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() {
        boolean isPossible = false;
        int idx;

        for(String word : L) {
            isPossible = false;
            // 현재 단어를 말한 앵무새 번호 (없으면 -1)
            idx = wordMap.getOrDefault(word, -1);
            if(idx == -1) break; // 없음

            // 해당 단어를 말할 순서인 앵무새가 존재
            if(!birds.get(idx).isEmpty() && birds.get(idx).peek().equals(word)) {
                birds.get(idx).poll();
                isPossible = true;
            }

            if(!isPossible) break;
        }

        // cseteram이 받아 적은 문장 L이 끝나도 앵무새들이 문장 다 끝내야 됨
        if(isPossible) {
            for(Queue<String> bird : birds) {
                // 앵무새가 할 말이 남았음
                if(!bird.isEmpty()) {
                    isPossible = false;
                    break;
                }
            }
        }

        // 문장 L이 가능한 문장이라면 Possible, 불가능한 문장이라면 Impossible 출력
        System.out.print(isPossible ? "Possible" : "Impossible");
    }//sol


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 앵무새의 수

        birds = new ArrayList<>(); // 각 앵무새가 말한 문장
        wordMap = new HashMap<>(); // 각 단어를 말한 앵무새 번호

        // N개의 줄에 걸쳐 각 앵무새가 말한 문장 Si (1 ≤ i ≤ N) 가 주어진다.
        for(int i=0; i<N; i++) {
            Queue<String> q = new LinkedList<>();
            String[] s = br.readLine().split(" ");

            for(String word : s) {
                q.offer(word);
                wordMap.put(word, i);
            }

            birds.add(q);
        }

        // cseteram이 받아 적은 문장 L이 주어진다.
        L = br.readLine().split(" ");

        br.close();
    }//init


}//class