import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/9202
// 각각의 Boggle에 대해, 얻을 수 있는 최대 점수, 가장 긴 단어, 찾은 단어의 개수를 출력
public class Main {
    private static final int[] SCORE = {0, 0, 0, 1, 1, 2, 3, 5, 11};
    private static final int[] DR = {-1, 1, 0, 0, -1, -1, 1, 1};
    private static final int[] DC = {0, 0, -1, 1, -1, 1, -1, 1};
    private static final int N = 4, MAX_IDX = N * N, MAX_LEN = 8;

    private static Trie trie; // 단어 사전에 들어있는 단어 트라이
    private static int maxScore, maxLen; // 얻을 수 있는 최대 점수
    private static char[][] boggle; // Boggle 보드
    private static Set<String> findWords; // 찾은 단어
    private static String longestWord; // 가장 긴 단어


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder ans = new StringBuilder();

        // 사전에 들어있는 단어의 수 w가 주어진다
        int w = Integer.parseInt(br.readLine());
        trie = new Trie();

        // w개 줄에는 단어가 주어진다
        while(w-- > 0) {
            String word = br.readLine();
            trie.insert(word);
        }
        // 빈 줄이 하나 주어진다
        br.readLine();

        // Boggle 보드의 개수 b가 주어진다
        int b = Integer.parseInt(br.readLine());
        for(int i = 0; i< b; i++) {
            boggle = new char[4][4];
            for(int r=0; r<4; r++) {
                boggle[r] = br.readLine().toCharArray();
            }
            if (i < b -1) br.readLine();

            // boggle 게임 진행
            game();

            // 얻을 수 있는 최대 점수, 가장 긴 단어, 찾은 단어의 개수를 출력
            ans.append(maxScore).append(' ');
            ans.append(longestWord).append(' ');
            ans.append(findWords.size()).append('\n');
        }
        br.close();

        System.out.print(ans);
    }//main

    private static void game() {
        maxScore = 0;
        findWords = new HashSet<>();
        maxLen = 0;
        longestWord = "";

        for(int idx=0; idx<MAX_IDX; idx++) {
            int r = idx / N;
            int c = idx % N;
            char key = boggle[r][c];

            if (trie.root.childNodes.containsKey(key)) {
                dfs(idx, 1, 1 << idx, trie.root.childNodes.get(key));
            }
        }

    }//game

    private static void dfs(int idx, int cnt, int mask, TrieNode curNode) {
        // 현재 글자가 단어의 마지막
        if (curNode.isEndOfWord) {
            // 같은 단어를 여러 번 찾은 경우에는 한 번만 찾은 것
            if (!findWords.contains(curNode.word)) {
                int len = curNode.word.length();
                maxScore += SCORE[len];
                findWords.add(curNode.word);

                if (maxLen < len
                        || (maxLen == len && curNode.word.compareTo(longestWord) < 0)
                ) {
                    longestWord = curNode.word;
                    maxLen = len;
                }
            }
        }
        // 단어는 최대 8글자
        if (cnt == MAX_LEN || idx == MAX_IDX) {
            return;
        }

        int r = idx / N;
        int c = idx % N;

        for(int i=0; i<8; i++) {
            int nr = r + DR[i];
            int nc = c + DC[i];
            if (!inRange(nr, nc)) continue;

            char key = boggle[nr][nc];
            int nIdx = nr * N + nc;
            if (curNode.childNodes.containsKey(key) && (mask & (1 << nIdx)) == 0) {
                TrieNode nextNode = curNode.childNodes.get(key);
                dfs(nIdx, cnt + 1, mask | (1 << nIdx), nextNode);
            }
        }

    }//dfs

    private static boolean inRange(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }//inRange

    private static class TrieNode {
        Map<Character, TrieNode> childNodes = new HashMap<>();
        boolean isEndOfWord; // 단어 끝
        String word;
    }//TrieNode

    private static class Trie {
        TrieNode root = new TrieNode();

        public void insert(String word) {
            TrieNode cur = root;

            for(char c : word.toCharArray()) {
                cur.childNodes.putIfAbsent(c, new TrieNode());
                cur = cur.childNodes.get(c);
            }

            cur.isEndOfWord = true;
            cur.word = word;
        }
    }//Trie

}//class