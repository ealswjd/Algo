import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/3156
public class Main {
    private static final int M = 100;
    private static Map<String, Integer> songMap;

    public static void main(String[] args) throws IOException {
        init();
        findPosition();
    }//main

    
    private static void findPosition() {
        StringBuilder ans = new StringBuilder();
        List<String> songKeySet = new ArrayList<>(songMap.keySet());
        songKeySet.sort((o1, o2) -> songMap.get(o1) - songMap.get(o2));
        
        int[] cnt = new int[M+1];
        int[] sum = new int[M+1];

        for(String song : songKeySet) {
            int position = songMap.get(song);
            cnt[position]++;
        }

        for(int i=1; i<=M; i++) {
            sum[i] += sum[i-1] + cnt[i];
        }

        for(String song : songKeySet) {
            int position = songMap.get(song);
            if(cnt[position] == 1 && sum[position] == position) {
                ans.append(position).append(' ').append(song).append('\n');
            }
        }


        System.out.print(ans);
    }//findPosition

    
    private static void init() throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        songMap = new HashMap<>();

        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken()); // 노래 개수
            st.nextToken(); // od
            int B = Integer.parseInt(st.nextToken()); // top B에 속한다는 의미

            for(int j=0; j<cnt; j++) {
                String song = st.nextToken();
                songMap.put(song, Math.min(songMap.getOrDefault(song, B), B));
            }
        }

    }//init

    
}//class