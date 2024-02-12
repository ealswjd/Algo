import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		int[] arr = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		br.close();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(getLength(arr, n, s) + "\n");
		bw.flush();
		bw.close();
	}//main
    
    static int getLength(int[] arr, int n, int s) {
        int min = Integer.MAX_VALUE;
		int length = 0;
		int start = 0;
		int end = 0;
		int sum = 0;
        
		while(true) {			
			if(sum >= s) {
				sum -= arr[start++];
				min = Math.min(min, (end-start) + 1);
				length = min;
			}
			else if(end == n) break;
			else sum += arr[end++];
		}//while	
        
		return length;
	}//getLength
	
}// Main class