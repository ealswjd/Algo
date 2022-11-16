package sewa;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_2382 {

   static int N,M,K;//크기, 격리시간, 군집의 개수 
   static int [][] map;  //뭉쳤을 때 군집 숫자 합산 
   static int [][] microbeNumber; // 뭉쳤을 때 가장 큰 군집 번호 저장.  
   static int [] dx = {0,0,0,-1,1}; 
   static int [] dy = {0,-1,1,0,0}; 
   static ArrayList <Microbe> microbeList; 
   
   public static void main(String[] args) throws NumberFormatException, IOException {
      
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); 
      StringTokenizer st = null; 
      StringBuilder sb = new StringBuilder(); 
      int testCase = Integer.parseInt(br.readLine()); 
      
      for(int t = 1; t<=testCase; t++) {
         
         st = new StringTokenizer(br.readLine()); 
         
         N = Integer.parseInt(st.nextToken()); // 지도 크기 
         M = Integer.parseInt(st.nextToken()); // 격리시간
         K = Integer.parseInt(st.nextToken()); // 군집의 개수 
         
         map = new int [N][N]; // 미생물 누적 개수 
         microbeNumber = new int [N][N]; // 최대 개수를 가진 미생물 번호 기록
         microbeList = new ArrayList<>(); // 미생물 리스트 
         
         for(int i=0; i<K; i++) { // 미생물 정보 리스트 담기 
            
            st = new StringTokenizer(br.readLine()); 
            int r = Integer.parseInt(st.nextToken()); 
            int c = Integer.parseInt(st.nextToken()); 
            int n = Integer.parseInt(st.nextToken()); 
            int d = Integer.parseInt(st.nextToken());
            
            microbeList.add(new Microbe(r, c, n, d, false)); 
         }
         
         
         while(M-->0) moveMicrobe();
         // 미생물 M번 움직여    
         
         int sum = 0; 
         // 미생물 전체 누적 개수 합산 
         
         for(int i=0; i<microbeList.size(); i++) {
            if(microbeList.get(i).isEaten==false) {// 미생물 중 먹히지 않은 것들만 
               sum+=microbeList.get(i).n;  
            }
         }
   
         sb.append("#").append(t).append(" ").append(sum).append("\n"); 
      }
      
      bw.write(sb.toString());
      bw.flush();
      bw.close();
      br.close();
      
   }
   
   static boolean lineCheck(int line) {
   
      if(line == 0 || line == N-1)return true; 
      // 약품이 칠해진 구역일 때 
      return false; 
      // 약품이 칠해져있지 않은 구역일 때 
   }
   
   
   static void moveMicrobe() {
      
      map = new int [N][N]; 
      microbeNumber = new int [N][N]; 
   
      for(int i=0; i<N; i++) {
         Arrays.fill(map[i], 0); // map 에다가 미생물 개수 합산 누적하기 
         Arrays.fill(microbeNumber[i], -1); // 미생물 번호 0부터 K-1 까지니까 -1 로 초기값 세팅
      }
            
      for(int i=0; i<microbeList.size(); i++) {
         Microbe m = microbeList.get(i); 
         if(m.isEaten==true)continue; // 먹힌거는 무시 
         
         
         // 아래 코드는 상, 하, 좌, 우로 이동하는 코드이며 
         // lineCheck 메소드를 통해 약품이 칠해진 라인일 경우 true를 반환하며 
         // 해당 조건문에서 미생물의 수를 절반으로 만들어주고 방향을 바꾼다. 
         if(m.d == 1) { //상
            m.r --; 
            if(lineCheck(m.r)) {
               m.n /=2; 
               m.d = 2;
            }
         }
         else if (m.d == 2) { //하
            m.r ++;  
            if(lineCheck(m.r)) {
               m.n /=2; 
               m.d = 1; 
            }
         }
         else if (m.d == 3) { //좌
            m.c --; 
            if(lineCheck(m.c)) {
               m.n /=2; 
               m.d = 4; 
            }
         }
         else { // 우 
            m.c ++; 
            if(lineCheck(m.c)) {
               m.n /=2; 
               m.d = 3;
            } 
         }
            
         map[m.r][m.c] += m.n;  // 해당 행과 열 위치에 현재 미생물 값 누적
         
         if(microbeNumber[m.r][m.c]==-1) microbeNumber[m.r][m.c] = i; 
         // 현재 미생물 번호가 저장되어 있찌 않으면 저장. 
         else {
            // 이전에 미생물 번호가 저장되어 있으면 미생물이 지금 겹친다는 의미 
            Microbe prevMicrobe = microbeList.get(microbeNumber[m.r][m.c]); 
            // 이전 미생물 정보 들고와 
            if(prevMicrobe.n<m.n) {
               // 만약 이전 미생물 개수가 현재 미생물 보다 작다면 잡아 먹혀야지 
               prevMicrobe.isEaten = true; 
               // 이전 미생물 정보 중 잡아 먹혔다고 갱신 
               microbeNumber[m.r][m.c] = i; 
               // 현재 미생물 번호로 갱신하자 
            }      
            else {
               m.isEaten = true; 
               // 이전 미생물의 개수가 더 많다면 바로 잡아 먹혀야지 
            }
         }
      }
      
      for(int i=0; i<N; i++) {
         for(int j=0; j<N; j++) {
            if(map[i][j]!=0) {
               // 미생물 개수가 0이 아니라면 
               microbeList.get(microbeNumber[i][j]).n = map[i][j]; 
               // map 위치 인덱스에 찍혀있는 미생물 번호를 들고와서 누적된 미생물 개수로 갱신해준다. 
            }
         }
      }
   }
   
   
   
   static class Microbe{
      
      int r; //행
      int c; //열
      int n; //개수
      int d; //방향
      boolean isEaten; //먹혔냐
      
      public Microbe(int r, int c, int n, int d, boolean isEaten) {
         super();
         this.r = r;
         this.c = c;
         this.n = n;
         this.d = d;
         this.isEaten = isEaten;
      } 
   }
   

}