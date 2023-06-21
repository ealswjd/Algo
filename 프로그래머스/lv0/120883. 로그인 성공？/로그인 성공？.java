class Solution {
    public String solution(String[] id_pw, String[][] db) {
        for(String[] arr : db) {
            if(arr[0].equals(id_pw[0])) {
                if(arr[1].equals(id_pw[1])) return "login";
                else return "wrong pw";
            }
        }//for
        return "fail";
    }
}