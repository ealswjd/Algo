import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/2469
public class Main {
	static int K, N, lineQ;
	static char[] order, startChar;
	static char[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder ans = new StringBuilder();

        K = Integer.parseInt(br.readLine());
        N = Integer.parseInt(br.readLine());

        startChar = new char[K];
        for(int i = 0 ; i < K ; i++){
            startChar[i] = (char)('A' + i);
        }//for
        
        order = br.readLine().toCharArray();

        map = new char[N][K-1];
        lineQ = 0; // ? 행
        for(int i = 0 ; i < N ; i++){
            map[i] = br.readLine().toCharArray();

            if(map[i][0] == '?') lineQ = i;
        }//for
        br.close();

        topDown();
        bottomUp();
        getLine(ans);

        System.out.print(ans);
    }//main

	private static void getLine(StringBuilder ans) {
		
		for(int i = 0 ; i < K-1; i++){
            if(startChar[i] == order[i]){
                ans.append('*');
            }else if(startChar[i] == order[i+1] || startChar[i+1] == order[i]){
                ans.append('-');
                char tmp = startChar[i];
                startChar[i] = startChar[i+1];
                startChar[i+1] = tmp;
            }else{ // 두 칸 이상 떨어져 있다면 불가능
            	ans.delete(0, ans.length());
                for(int j = 0 ; j < K-1 ; j++) {
                	ans.append("x");                  
                }//for
                break;
            }//else
        }//for
		
	}//getLine

	private static void bottomUp() {
		
		for(int i = N-1; i > lineQ ; i--){
            for(int j = 0 ; j < K-1 ; j++){
                if(map[i][j] == '-'){
                    char tmp = order[j];
                    order[j] = order[j+1];
                    order[j+1] = tmp;
                }//if
            }//for j
        }//for i
		
	}//bottomUp

	private static void topDown() {
		
		for(int i = 0 ; i < lineQ ; i++){
            for(int j = 0 ; j < K-1 ; j++){
                if(map[i][j] == '-'){
                    char tmp = startChar[j];
                    startChar[j] = startChar[j+1];
                    startChar[j+1] = tmp;
                }//if
            }//for j
        }//for i
		
	}//topDown
}//class