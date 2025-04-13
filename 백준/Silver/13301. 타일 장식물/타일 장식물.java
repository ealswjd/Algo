import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/13301
public class Main {

    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 타일의 개수
        br.close();

        // 타일 장식물 직사각형의 둘레를 출력
        long perimeter = getPerimeter(N);
        System.out.print(perimeter);
    }//main


    private static long getPerimeter(int N) {
        long[] size = new long[N+1];
        size[1] = 1;

        for(int i=2; i<=N; i++) {
            size[i] = size[i-1] + size[i-2];
        }

        return (size[N] + size[N-1] + size[N]) * 2;
    }//getPerimeter

    
}//class