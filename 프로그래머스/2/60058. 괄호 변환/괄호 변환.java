import java.util.Stack;

class Solution {
    
    public String solution(String p) {
        if(p.length() == 0) return ""; // 1

        // 2
        StringBuilder u = new StringBuilder( getU(p) );
        StringBuilder v = new StringBuilder( getV(p, u.length()) );

        if(isRight(u)) {  // 3
            return String.valueOf(u) + solution(String.valueOf(v)); // 3-1
        }
        else {          // 4
            StringBuilder tmp = new StringBuilder("("); // 4-1
            tmp.append(solution(String.valueOf(v)));    // 4-2
            tmp.append(")");                            // 4-3
            tmp.append(change(u));                      // 4-4
            return String.valueOf(tmp);                 // 4-5
        }
    }//solution

    
    // 올바른 괄호 문자열인지 확인
    private boolean isRight(StringBuilder u) {
        String str = new String(u);
        if(str.charAt(0) == ')') return false;
        Stack<Character> check = new Stack<>();
        for(char c : str.toCharArray()) {
            if(c == '(') check.push(c);
            else {
                if(check.isEmpty()) return false;
                check.pop();
            }
        }
        return true;
    }//isRight
 
    
    // 문자열 u를 반환
    private String getU(String s) {
        Stack<Character> check = new Stack<>();
        check.push(s.charAt(0));
        int idx = 1;
        
        while(!check.isEmpty()) {
            if(check.peek() == s.charAt(idx)) check.push(s.charAt(idx));
            else check.pop();
            idx++;
        }
        
        return s.substring(0, idx);
    }//getU

    
    // 문자열 v를 반환
    private static String getV(String s, int idx) {
        if(idx == s.length()) return "";
        return s.substring(idx);
    }//getV

    
    // 괄호의 방향을 뒤집어서 반환
    private String change(StringBuilder u) {
        if(u.length() <= 2) return "";
        
        String str = u.length() > 2 ? String.valueOf(u).substring(1, u.length()-1) : String.valueOf(u);
        StringBuilder changeStr = new StringBuilder();
        
        for(char c : str.toCharArray()) {
            if(c == ')') changeStr.append("(");
            else changeStr.append(")");
        }
        
        return String.valueOf(changeStr);
    }//change

    
} //class