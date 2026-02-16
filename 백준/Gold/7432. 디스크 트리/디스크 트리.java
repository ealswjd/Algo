import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

// https://www.acmicpc.net/problem/7432
public class Main {

    private static class TrieNode {
        Map<String, TrieNode> childNodes = new TreeMap<>();
    }

    private static class Trie {
        TrieNode root = new TrieNode();

        public void insert(String[] dir) {
            TrieNode cur = root;

            for(String s : dir) {
                cur.childNodes.putIfAbsent(s, new TrieNode());
                cur = cur.childNodes.get(s);
            }
        }
    }

    private static Trie rootTrie;

    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main

    private static void sol() {
        StringBuilder ans = new StringBuilder();
        print(ans, rootTrie.root, 0);

        System.out.print(ans);
    }//sol

    private static void print(StringBuilder ans, TrieNode cur, int blank) {
        for(String d : cur.childNodes.keySet()) {
            for(int i=0; i<blank; i++) {
                ans.append(' ');
            }
            ans.append(d).append('\n');

            print(ans, cur.childNodes.get(d), blank+1);
        }
    }//print

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 디렉토리 경로의 개수
        rootTrie = new Trie();

        while(N-- > 0) {
            String dir = br.readLine();
            rootTrie.insert(dir.split("\\\\"));
        }

        br.close();
    }//init

}//class