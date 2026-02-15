import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

// https://www.acmicpc.net/problem/16934
public class Main {
    private static class TrieNode {
        Map<Character, TrieNode> childNodes = new HashMap<>();
        boolean isEndOfWord;
        int cnt;
    }

    private static class Trie {
        TrieNode root = new TrieNode();

        public String insert(String nickname) {
            StringBuilder sb = new StringBuilder();
            int len = nickname.length();
            boolean isPossible = false;
            TrieNode cur = root;

            for(int i=0; i<len; i++) {
                char c = nickname.charAt(i);
                if (!isPossible) {
                    sb.append(c);
                }
                if (!cur.childNodes.containsKey(c)) {
                    cur.childNodes.put(c, new TrieNode());
                    isPossible = true;
                }
                cur = cur.childNodes.get(c);
            }

            cur.isEndOfWord = true;
            cur.cnt++;

            if (!isPossible && cur.cnt > 1) {
                sb.append(cur.cnt);
            }

            return sb.toString();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder ans = new StringBuilder();

        int N = Integer.parseInt(br.readLine()); // 유저의 수
        Trie trie = new Trie();

        while(N-- > 0) {
            String nickname = br.readLine();

            ans.append(trie.insert(nickname)).append('\n');
        }
        br.close();

        System.out.print(ans);
    }//main

}//class