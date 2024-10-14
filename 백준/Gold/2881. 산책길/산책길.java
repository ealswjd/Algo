import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

// https://www.acmicpc.net/problem/2881
public class Main {
    static int N;
    static Map<Integer, List<Integer>> xMap;
    static Map<Integer, List<Integer>> yMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        xMap = new HashMap<>();
        yMap = new HashMap<>();

        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            xMap.putIfAbsent(x, new ArrayList<>());
            yMap.putIfAbsent(y, new ArrayList<>());

            xMap.get(x).add(y);
            yMap.get(y).add(x);
        }

        // 정렬
        for(int n : xMap.keySet()) {
            Collections.sort(xMap.get(n));
        }
        for(int n : yMap.keySet()) {
            Collections.sort(yMap.get(n));
        }

        StringBuilder ans = new StringBuilder();
        int P = Integer.parseInt(br.readLine());

        while(P-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            
            int total = getTotal(x1, y1, x2, y2);

            ans.append(total).append('\n');
        }

        System.out.print(ans);
    }//main
    
    
    private static int getTotal(int x1, int y1, int x2, int y2) {
        int total = 0;

        // 좌
        total = upperBound(y2, x1, xMap) - lowerBound(y1, x1, xMap);
        // 우
        total += upperBound(y2, x2, xMap) - lowerBound(y1, x2, xMap);
        // 상
        total += upperBound(x2-1, y2, yMap) - lowerBound(x1+1, y2, yMap);
        // 하
        total += upperBound(x2-1, y1, yMap) - lowerBound(x1+1, y1, yMap);

        
        return total;
    }//getTotal

    
    private static int lowerBound(int n, int key, Map<Integer, List<Integer>> map) {
        if(!map.containsKey(key)) return 0;

        List<Integer> list = map.get(key);
        int start = 0;
        int end = list.size();
        int mid;

        while(start < end) {
            mid = (start + end) / 2;

            if(n > list.get(mid)) start = mid + 1;
            else end = mid;
        }

        return start;
    }//lowerBound

    
    private static int upperBound(int n, int key, Map<Integer, List<Integer>> map) {
        if(!map.containsKey(key)) return 0;

        List<Integer> list = map.get(key);
        int start = 0;
        int end = list.size();
        int mid;

        while(start < end) {
            mid = (start + end) / 2;

            if(n >= list.get(mid)) start = mid + 1;
            else end = mid;
        }

        return start;
    }//upperBound


}//class