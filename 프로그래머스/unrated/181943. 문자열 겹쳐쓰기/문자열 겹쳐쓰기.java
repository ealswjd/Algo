class Solution {
    public String solution(String my_string, String overwrite_string, int s) {
        StringBuilder sb = new StringBuilder();
        sb.append(my_string.substring(0, s));
        sb.append(overwrite_string);
        int oLen = overwrite_string.length();
        int mLen = my_string.length();
        
        if(oLen+s < mLen) sb.append(my_string.substring(oLen+s));
        
        return String.valueOf(sb);
    }
}