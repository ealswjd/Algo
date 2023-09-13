import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb;
	static int[] numbers;
	static boolean[] isSelected;
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		numbers = new int[N+1];
		isSelected = new boolean[N+1];
		sb = new StringBuilder();
		
		f(0);
		System.out.println(sb);
	}
	
	private static void f(int cnt) {
		if(cnt == M) {
			for(int i=0; i<M; i++) {
				sb.append(numbers[i] + " ");
			}
			sb.append("\n");
			return;
		}

		for(int i=1; i<=N; i++) {
			if(isSelected[i]) continue;
            
			numbers[cnt] = i;
			isSelected[i] = true;

			f(cnt+1);
			isSelected[i] = false;
		}
		
	}

}