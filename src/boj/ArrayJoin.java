package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
// https://www.acmicpc.net/problem/11728
// 11728번) 두 포인터 > 배열 합치기
public class ArrayJoin {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 배열 A의 크기
        int m = Integer.parseInt(st.nextToken()); // 배열 B의 크기
        
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
        
        joinArray(arrA, arrB);  // 두 배열 합치기
    }//main

    static void joinArray(int[] arrA, int[] arrB) throws IOException {
    	int idxA = 0; // A의 인덱스
    	int idxB = 0; // B의 인덱스
    	int lengthA = arrA.length; // A의 길이
    	int lengthB = arrB.length; // B의 길이
    	int[] arr = new int[lengthA+lengthB]; // 합칠 배열
    	
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
    		bw.write(num + " "); // 결과 출력
    	}
    	bw.flush();
    	bw.close();
    }//joinArray
    
}//class
