package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
// https://programmers.co.kr/learn/courses/30/lessons/12978
// Summer/Winter Coding(~2018) > 배달
class Node implements Comparable<Node> {
    private final int index; // 노드번호
    private final int cost; // 거리(비용)

    public Node(int index, int cost) {
        this.index = index;
        this.cost = cost;
    }

    public int getIndex() {
        return index;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public int compareTo(Node o) {
        return this.cost - o.cost;
    }
}//Node

class Delivery {
	static List<List<Node>> list = new ArrayList<>(); // 연결 정보
    static int[] minArr; // 1번에서 갈 수 있는 최단거리 배열
    static int INF = Integer.MAX_VALUE; // 최대값

	public static void main(String[] args) {
//		int n = 5;
//		int[][] road = {{1, 2, 4}, {1, 3, 1}, {3, 4, 1}, {4, 2, 1}, {2, 5, 1}};
//		int k = 4;
		
//		int n = 5;
//		int[][] road = {{1,2,1},{2,3,3},{5,2,2},{1,4,2},{5,3,1},{5,4,2}};
//		int k = 3;
		
		int n = 6;
		int[][] road = {{1,2,1},{1,3,2},{2,3,2},{3,4,3},{3,5,2},{3,5,3},{5,6,1}};
		int k = 4;

		System.out.println( solution(n, road, k));
	}//main

    public static int solution(int N, int[][] road, int K) {
    	minArr = new int[N + 1]; // 인덱스 1부터 사용하기 위해 +1
        Arrays.fill(minArr, INF); // 모두 INF로 채워주기
        for (int i = 0 ; i <= N; i++) {
            list.add(new ArrayList<>());
        }

        // 연결 정보 입력
        for (int[] r : road) {
            int a = r[0];
            int b = r[1];
            int cost = r[2];

            list.get(a).add(new Node(b, cost));
            list.get(b).add(new Node(a, cost));
        }

        dijkstra(1); // 최단 거리 배열 변경

        int cnt = 0;
        for (int num : minArr) {
            if (num <= K) cnt++; // K번 이하면 cnt 증가
        }
        return cnt;
    }//solution

    static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        minArr[start] = 0; // 첫시작점 0으로 설정

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int nIdx = node.getIndex();
            int nCost = node.getCost();
            // 기존값이 더 작으면 continue
            if (minArr[nIdx] < nCost) continue;

            List<Node> nodes = list.get(nIdx);
            for (Node next : nodes) {
                int cost = nCost + next.getCost(); // 현재 거리 + 다음 거리
                // 현재 경로 값(cost)이 작다면 
                if (cost < minArr[next.getIndex()]) {
                	minArr[next.getIndex()] = cost; // 변경
                    pq.offer(new Node(next.getIndex(), cost)); // pq에 담아주기
                }
            }
        }
    }//dijkstra
}
