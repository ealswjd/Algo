import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/20006
public class Main {
    private static int P, M;
    private static List<List<Player>> roomList;
    private static List<int[]> roomLevel;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        P = Integer.parseInt(st.nextToken()); // 플레이어의 수
        M = Integer.parseInt(st.nextToken()); // 방의 정원

        roomList = new ArrayList<>();
        roomLevel = new ArrayList<>();

        for(int i=0; i<P; i++) {
            st = new StringTokenizer(br.readLine());
            int level = Integer.parseInt(st.nextToken());
            String name = st.nextToken();

            enterRoom(new Player(level, name));
        }

        print();
    }//main

    
    private static void enterRoom(Player player) {
        if(roomList.isEmpty()) {
            makeRoom(player);
        }
        else {
            boolean flag = false;

            for(int i=0; i<roomList.size(); i++) {
                if(roomList.get(i).size() == M) continue;
                if(!levelCheck(i, player.level)) continue;

                flag = true;
                roomList.get(i).add(player);
                break;
            }

            if(!flag) {
                makeRoom(player);
            }
        }
    }//enterRoom

    
    private static void makeRoom(Player player) {
        List<Player> room = new ArrayList<>();
        room.add(player);

        roomList.add(room);
        roomLevel.add(new int[] {player.level - 10, player.level + 10});
    }//makeRoom

    
    private static boolean levelCheck(int idx, int level) {
        return roomLevel.get(idx)[0] <= level && level <= roomLevel.get(idx)[1];
    }//levelCheck

    
    private static void print() {
        StringBuilder ans = new StringBuilder();

        for(List<Player> room : roomList) {
            room.sort(Comparator.comparing(player -> player.name));

            if(room.size() == M) ans.append("Started!").append("\n");
            else ans.append("Waiting!").append("\n");

            for(Player player : room) {
                ans.append(player).append("\n");
            }
        }

        System.out.print(ans);
    }//print

    
    private static class Player {
        int level;
        String name;

        public Player(int level, String name) {
            this.level = level;
            this.name = name;
        }

        @Override
        public String toString() {
            return level + " " + name;
        }
    }//Player

    
}//class