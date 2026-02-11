import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/21776
public class Main {
    private static int N, C; // 사람의 수, 카드의 수
    private static List<List<Card>> cards; // 카드 정보
    private static int[][] orders; // 각 사람이 내는 카드 순서
    private static Set<String> result; // 게임의 결과로 나올 수 있는 문자열
    private static boolean[] used; // 카드 사용 여부
    private static int[] oIdx; // 플레이어가 낼 카드 인덱스

    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main

    private static void sol() {
        // 게임의 결과로 나올 수 있는 문자열 탐색
        dfs(0, new int[C]);

        // 게임의 결과로 나올 수 있는 문자열을 아스키 코드 기준 사전순으로 출력.
        StringBuilder ans = new StringBuilder();
        for(String str : result) {
            ans.append(str).append('\n');
        }
        System.out.print(ans);
    }//sol
    

    private static void dfs(int cnt, int[] seq) {
        // 게임 끝
        if (cnt == C) {
            // 현재 정한 카드 순서대로 문자열 만들기
            playGame(seq);
            return;
        }

        int idx, card;
        for(int i=0; i<N; i++) {
            idx = oIdx[i]; // i번 플레이어가 낼 카드 인덱스

            if (idx < orders[i].length) {
                card = orders[i][idx]; // 현재 사용할 카드
                if (used[card]) continue; // 이미 누가 사용

                seq[cnt] = card;
                oIdx[i]++;
                used[card] = true;

                dfs(cnt + 1, seq);

                oIdx[i]--;
                used[card] = false;
            }
        }
    }//dfs
    

    private static void playGame(int[] seq) {
        StringBuilder sb = new StringBuilder();

        for(int cardNum : seq) {
            for(Card card : cards.get(cardNum)) {
                if (card.isAdd) { // 추가 연산
                    // 문자 c를 추가
                    sb.append(card.c);
                } else { // 삭제 연산
                    // 문자를 삭제할 수 없는 경우 오류 발생
                    if (card.idx >= sb.length()) {
                        // 오류가 발생하면 문자열은 "ERROR"가 되고, 즉시 게임이 종료
                        result.add("ERROR");
                        return;
                    } else {
                        // x번째 위치에 있는 글자를 삭제
                        sb.deleteCharAt(card.idx);
                    }
                }
            }
        }

        // 빈 문자열이라면, 문자열은 "EMPTY"
        if (sb.length() == 0) {
            result.add("EMPTY");
        } else {
            result.add(sb.toString());
        }
    }//playGame


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // N, C가 공백으로 구분되어 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        orders = new int[N][]; // 사람들이 내는 카드 순서
        cards = new ArrayList<>(C+1); // 카드 정보
        result = new TreeSet<>(); // 게임의 결과로 나올 수 있는 문자열
        used = new boolean[C+1]; // 카드 사용 여부
        oIdx = new int[N]; // 플레이어가 낼 카드 인덱스

        // 1번 사람부터 N번 사람까지 낸 카드의 갯수와 카드를 낸 순서가 주어짐
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            orders[i] = new int[cnt];
            for(int j=0; j<cnt; j++) {
                orders[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<=C; i++) {
            cards.add(new ArrayList<>());
        }

        // 1번 카드부터 C번 카드에 적혀져 있는 1개 이상의 연산이 주어짐
        for(int i=1; i<=C; i++) {
            String[] tmp = br.readLine().split(",");
            for(String card : tmp) {
                cards.get(i).add(new Card(card));
            }
        }

        br.close();
    }//init


    private static class Card {
        boolean isAdd; // 추가 연산
        char c; // 문자열 뒤에 문자 c를 추가
        int idx; // 문자열의 idx번째 위치에 있는 글자를 삭제

        Card(String str) {
            if (str.startsWith("ADD")) {
                isAdd = true;
                c = str.charAt(4);
            } else {
                isAdd = false;
                idx = Character.getNumericValue(str.charAt(4));
            }
        }
    }//Card

}//class