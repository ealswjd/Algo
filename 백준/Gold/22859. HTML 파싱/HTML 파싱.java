import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 문제 이름(난이도) : HTML 파싱 (골드 3)
 * 링크 : https://www.acmicpc.net/problem/22859
 * */
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] html = br.readLine().split("<p>|</p>|</div>");
        br.close();

        StringBuilder ans = new StringBuilder();
        parsing(html, ans);
        System.out.print(ans);
    }//main

    private static void parsing(String[] html, StringBuilder ans) {
        for(String s : html) {
            if(s.isEmpty()) continue;

            if(s.contains("<div ")) { // 제목
                ans.append("title : ");
                ans.append(s.substring(s.indexOf('"')+1, s.lastIndexOf('"'))).append('\n');
            }else { // 내용
                ans.append(s.replaceAll("<.*?>", "")
                        .replaceAll("\\s{2,}", " ")).append('\n');
            }
        }//for
    }//parsing

}//class