import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

// https://www.acmicpc.net/problem/5670
public class Main {
    private static final String PATTERN = "0.00"; // 소수점 둘째 자리까지 반올림
    private static int N; // 단어의 개수
    private static double total; // 버튼을 눌러야 하는 총 횟수
    private static Trie trie; // 단어 사전

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        DecimalFormat df = new DecimalFormat(PATTERN); 
        StringBuilder ans = new StringBuilder();
        String input;

        while((input = br.readLine()) != null) {
            N = Integer.parseInt(input); // 단어 개수
            trie = new Trie(); // 단어 사전
            total = 0; // 버튼을 눌러야 하는 총 횟수

            for(int i=0; i<N; i++) {
                trie.insert(br.readLine());
            }

            double avg = getAverage();
            ans.append(df.format(avg)).append('\n');
        }

        br.close();
        System.out.print(ans);
    }//main

    private static double getAverage() {
        TrieNode cur;

        for(char c : trie.root.childNodes.keySet()) {
            cur = trie.root.childNodes.get(c);
            cur.cnt = 1;
            
            getCnt(cur);
        }

        double avg = total / N;
        return Math.round(avg * 100) / 100.0;
    }//getAverage

    private static void getCnt(TrieNode cur) {
        int nCnt = cur.cnt;
        if (cur.isEnd) {
            total += cur.cnt;
        }

        // 현재 단어 외에 다른 단어가 존재함
        if (cur.childNodes.size() > 1 || cur.isEnd) {
            nCnt++;
        }

        for(char c : cur.childNodes.keySet()) {
            TrieNode next = cur.childNodes.get(c);
            next.cnt = nCnt;
            getCnt(next);
        }
    }//getCnt

    private static class TrieNode {
        Map<Character, TrieNode> childNodes = new HashMap<>();
        int cnt; // 버튼 누르는 횟수
        boolean isEnd; // 단어 끝
    }//TrieNode

    private static class Trie {
        TrieNode root = new TrieNode();

        public void insert(String word) {
            TrieNode cur = root;
            int len = word.length(); // 단어 길이

            for(int i=0; i<len; i++) {
                char c = word.charAt(i);
                cur.childNodes.putIfAbsent(c, new TrieNode());
                cur = cur.childNodes.get(c);
            }

            cur.isEnd = true;
        }
    }//Trie

}//class