import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);		
		mixSolution(arr, n);		
	}//main
    
    static void mixSolution(int[] arr, int n) {
		int[] answer = new int[2];
		int start = 0;
		int end = n-1;
		int sum = 0;		
		int min = arr[start] + arr[start+1];
		
		while (start < end) {
            sum = arr[start] + arr[end];

            if(sum == 0) {
            	answer[0] = arr[start];
            	answer[1] = arr[end];
            	break;
            }
            if (Math.abs(sum) <= Math.abs(min)) {
            	answer[0] = arr[start];
            	answer[1] = arr[end];
            	min = sum;
            }
            
            if(Math.abs(arr[start+1] + arr[end]) <= Math.abs(arr[start] + arr[end-1])) {
            	start++;
            }else {
            	end--;
            }
        }
		
		for(int num : answer) {
			System.out.print(num + " ");
		}
	}//mixSolution
	
}// Main class