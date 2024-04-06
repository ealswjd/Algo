import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        int[] arrA = new int[n];
        int[] arrB = new int[m];
        for(int i=0; i<n; i++) {
        	arrA[i] = Integer.parseInt(st.nextToken());
        }        
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<m; i++) {
        	arrB[i] = Integer.parseInt(st.nextToken());
        }
        br.close();
        
        joinArray(arrA, arrB);    
    }//main

    static void joinArray(int[] arrA, int[] arrB) throws IOException {
    	int idxA = 0;
    	int idxB = 0;
    	int lengthA = arrA.length;
    	int lengthB = arrB.length;
    	int[] arr = new int[lengthA+lengthB];
    	
    	for(int i=0; i<arr.length; i++) {
    		if(idxB == lengthB || (idxA < lengthA && arrA[idxA] <= arrB[idxB])) {
    			arr[i] = arrA[idxA++];  
    		}
    		else if(idxA == lengthA || (idxB < lengthB && arrA[idxA] >= arrB[idxB])) {
    			arr[i] = arrB[idxB++];
    		}
    	}
    	
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	for(int num : arr) {
    		bw.write(num + " ");
    	}
    	bw.flush();
    	bw.close();
    }//joinArray
    
}//class