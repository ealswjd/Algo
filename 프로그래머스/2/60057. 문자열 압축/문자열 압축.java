class Solution {
    public int solution(String s) {
        int min = s.length(); // 문자열 s의 길이로 초기화
        int len = min/2; // 길이의 반
        for(int i = 1; i <= len; i++) {
            String prev = ""; // 이전 문자열
            int sum = 0; // 문자열 길이
            int cnt = 1; // 반복되는 문자 수
            for(int j = 0; j < s.length();) {               
                int start = j;
                j = (j+i > s.length()) ? s.length() : j+i;
                String now = s.substring(start, j); // 이전 문자열과 비교할 현재 문자열
                if(now.equals(prev)) { // 이전 문자열과 현재 문자열이 같다면
                    cnt++; // cnt 증가
                } else { // 다르다면
                    if(cnt != 1) { // cnt가 1이 아닐 경우
                        sum += (int)Math.log10(cnt)+1; // cnt의 자릿수만큼 더해주기
                    }
                    sum += prev.length(); // 이전 문자열의 길이만큼 더해주기
                    prev = now; 
                    cnt = 1; 
                }
            }// for j
            
            sum += prev.length(); // 이전 문자열의 길이만큼 더해주기
            if(cnt != 1) { // cnt가 1이 아닐 경우
                sum += (int)Math.log10(cnt)+1; // cnt의 자릿수만큼 더해주기
            }
            min = (min > sum) ? sum : min; 
        }// for i

        return min; // 가장 짧은 길이 return
    }
}