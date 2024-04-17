import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = new int[10];
        for (int i = 1; i <= 9; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        int max = arr[1];
        int idx = 1;
        for (int i = 2; i <= 9; i++) {
            if (max < arr[i]) {
                max = arr[i];
                idx = i;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(max).append("\n").append(idx);
        System.out.println(sb);
    }
}