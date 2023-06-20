import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] origin = br.readLine().toCharArray();
        br.close();
        
        StringBuilder ans = new StringBuilder();
        for(char c : origin) {
            if(c>='a') ans.append((char)(c-32));
            else ans.append((char)(c+32));
        }//for
        
        System.out.print(ans);
    }//main
}//class