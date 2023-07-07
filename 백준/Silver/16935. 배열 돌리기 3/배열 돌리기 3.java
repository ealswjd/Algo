import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 배열의 크기 N, M
		int M = Integer.parseInt(st.nextToken()); // 배열의 크기 N, M
		int R = Integer.parseInt(st.nextToken()); // 수행해야 하는 회전의 수
		int[][] arr = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<R; i++) {
			switch (st.nextToken()) {
			case "1": arr = rotation1(arr);
				break;
			case "2": arr = rotation2(arr);
				break;
			case "3": arr = rotation3(arr);
				break;
			case "4": arr = rotation4(arr);
				break;
			case "5": arr = rotation5(arr);
				break;
			case "6": arr = rotation6(arr);
				break;
			}
		}
		br.close();
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[0].length; j++) {
				sb.append(arr[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);

	}//main
	
	// 배열을 상하 반전
	private static int[][] rotation1(int[][] arr) {
		int N = arr.length;
		int M = arr[0].length;
		int[][] tmp = new int[N][M];
		
		for(int i=0,x = N-1; i<N; i++, x--) {
			tmp[i] = arr[x];
		}
		return tmp;		
	}
	
	// 배열을 좌우 반전
	private static int[][] rotation2(int[][] arr) {
		int N = arr.length;
		int M = arr[0].length;
		int[][] tmp = new int[N][M];
		
		for(int i=0; i<N; i++) {
			for(int j=0,y = M-1; j<M; j++, y--) {
				tmp[i][j] = arr[i][y];
			}
		}
		return tmp;
	}
	
	// 오른쪽으로 90도 회전
	private static int[][] rotation3(int[][] arr) {
		int N = arr.length;
		int M = arr[0].length;
		int[][] tmp = new int[M][N];
		
		for(int i=0; i<M; i++) {
			for(int j=0, y=N-1; j<N; j++, y--) {
				tmp[i][j] = arr[y][i];
			}
		}
		
		return tmp;
	}
	
	// 왼쪽으로 90도 회전
	private static int[][] rotation4(int[][] arr) {
		int N = arr.length;
		int M = arr[0].length;
		int[][] tmp = new int[M][N];
		
		for(int i=0, y=M-1; i<M; i++, y--) {
			for(int j=0; j<N; j++) {
				tmp[i][j] = arr[j][y];
			}
		}
		
		return tmp;
	}
	
	// 1번 그룹의 부분 배열을 2번 그룹 위치로, 2번을 3번으로, 3번을 4번으로, 4번을 1번으로 이동
	private static int[][] rotation5(int[][] arr) {
		int N = arr.length;
		int M = arr[0].length;
		int[][] tmp = new int[N][M];
		int xH = N/2;
		int yH = M/2;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				switch (getGroup(i, j, arr)) {
				case 1: tmp[i][j] = arr[xH+i][j];
					break;
				case 2: tmp[i][j] = arr[i][j-yH];					
					break;
				case 3: tmp[i][j] = arr[i-xH][j];						
					break;
				case 4: tmp[i][j] = arr[i][j+yH];						
					break;
				}//switch
			}//for j
		}// for i
		return tmp;
	}
	
	// 1번 그룹의 부분 배열을 4번 그룹 위치로, 4번을 3번으로, 3번을 2번으로, 2번을 1번으로 이동
	private static int[][] rotation6(int[][] arr) {
		int N = arr.length;
		int M = arr[0].length;
		int[][] tmp = new int[N][M];
		int xH = N/2;
		int yH = M/2;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				switch (getGroup(i, j, arr)) {
				case 1: tmp[i][j] = arr[i][j+yH];
					break;
				case 2: tmp[i][j] = arr[i+xH][j];					
					break;
				case 3: tmp[i][j] = arr[i][j-yH];						
					break;
				case 4: tmp[i][j] = arr[i-xH][j];						
					break;
				}//switch
			}//for j
		}// for i
		return tmp;
	}
	
	private static int getGroup(int i, int j, int[][] arr) {
		int xH = arr.length/2;
		int yH = arr[0].length/2;
		int g=1;
		
		if(i < xH && j < yH) g=1;
		else if(i < xH && j >= yH) g=2;
		else if(i >= xH && j >= yH) g=3;
		else if(i >= xH && j < yH) g=4;		
		return g;
	}

}//class