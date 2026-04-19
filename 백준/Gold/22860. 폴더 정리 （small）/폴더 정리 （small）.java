import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/*
 https://www.acmicpc.net/problem/22860
 쿼리 순서대로 한 줄씩 폴더 하위에 있는 파일의 종류의 개수와 파일의 총 개수를 출력한다.
 */
public class Main {
    private static final int MAX = 2000;
    private static final String FOLDER = "1", FILE = "0";
    private static int N, M; // 폴더의 총 개수, 파일의 총 개수
    private static int categoryCnt, fileCnt;
    private static String[] queries;
    private static Item[] items;
    private static Map<String, Integer> indexMap;

    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main

    private static void sol() {
        StringBuilder ans = new StringBuilder();
        boolean[] visited;
        int cur, len;

        // 파일의 종류의 개수와 파일의 총 개수를 출력
        for(String query : queries) {
            categoryCnt = fileCnt = 0;
            visited = new boolean[MAX];
            String[] q = query.split("/");
            len = q.length;
            cur = indexMap.get(q[len-1]);

            dfs(cur, false, visited);

            ans.append(categoryCnt).append(' ').append(fileCnt);
            ans.append('\n');
        }

        System.out.print(ans);
    }//sol

    private static void dfs(int idx, boolean isFile, boolean[] visited) {
        if(isFile) {
            if (!visited[idx]) {
                visited[idx] = true;
                categoryCnt++;
            }
            fileCnt++;
        }

        for(Item item=items[idx]; item!=null; item=item.parent) {
            dfs(item.id, item.isFile, visited);
        }
    }//dfs

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 폴더의 총 개수
        M = Integer.parseInt(st.nextToken()); // 파일의 총 개수

        int total = N + M; // 폴더 + 파일의 총 개수
        indexMap = new HashMap<>();
        items = new Item[MAX];

        int idx = 0;
        int parent, child;
        while(total-- > 0) {
            st = new StringTokenizer(br.readLine());
            String folder = st.nextToken(); // 상위 폴더의 이름
            String sub = st.nextToken(); // 폴더 또는 파일의 이름
            boolean isFile = st.nextToken().equals(FILE); // 폴더인지 아닌지

            // 상위 폴더 확인
            if(indexMap.containsKey(folder)) {
                parent = indexMap.get(folder);
            }
            else {
                parent = idx;
                indexMap.put(folder, idx++);
            }

            // 하위 폴더(파일) 확인
            if(indexMap.containsKey(sub)) {
                child = indexMap.get(sub);
            }
            else {
                child = idx;
                indexMap.put(sub, idx++);
            }

            items[parent] = new Item(child, items[parent], isFile);
        }

        int Q = Integer.parseInt(br.readLine()); // 쿼리 개수
        queries = new String[Q]; // 쿼리

        for(int i=0; i<Q; i++) {
            queries[i] = br.readLine();
        }
        br.close();
    }//init


    private static class Item {
        int id;
        Item parent;
        boolean isFile;

        Item(int id, Item parent, boolean isFile) {
            this.id = id;
            this.parent = parent;
            this.isFile = isFile;
        }
    }//Item


}//class