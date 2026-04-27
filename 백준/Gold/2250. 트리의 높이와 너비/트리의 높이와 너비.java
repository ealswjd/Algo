import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2250
public class Main {
    private static final int MAX = Integer.MAX_VALUE;
    private static final int MIN = Integer.MIN_VALUE;
    
    private static int N; // 노드의 개수
    private static Node[] tree;
    private static int[] min; // 각 레벨의 가장 왼쪽 열 번호
    private static int[] max; // 각 레벨의 가장 오른쪽 열 번호
    private static int colIdx = 1; // 순회 시 할당할 열 번호
    private static int maxDepth = 1; // 트리의 최대 깊이

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }//main

    private static void solve() {
        int root = -1;
        for(int i=1; i<=N; i++) {
            if (tree[i].parent == -1) {
                root = i;
                break;
            }
        }

        // 중위 순회
        inOrder(root, 1);

        // 최대 너비 레벨 찾기
        int maxLevel = 1;
        int width = 0;

        // 1레벨부터 트리의 최대 깊이까지 탐색
        for(int lv=1; lv<=maxDepth; lv++) {
            int w = max[lv] - min[lv] + 1;

            if (w > width) {
                width = w;
                maxLevel = lv;
            }
        }

        System.out.printf("%d %d", maxLevel, width);
    }//solve

    private static void inOrder(int cur, int level) {
        // 자식 노드 없으면 종료
        if (cur == -1) return;

        // 최대 깊이 갱신
        maxDepth = Math.max(maxDepth, level);

        // 왼쪽 서브트리 방문
        inOrder(tree[cur].left, level + 1);

        // 현재 노드 방문 처리
        min[level] = Math.min(min[level], colIdx);
        max[level] = Math.max(max[level], colIdx);
        colIdx++; // 열 번호 1 증가

        // 오른쪽 서브 트리 방문
        inOrder(tree[cur].right, level + 1);
    }//inOrder

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 노드의 개수

        tree = new Node[N+1];
        min = new int[N+1];
        max = new int[N+1];

        // 트리 및 레벨별 최소/최대 열 번호 배열 초기화
        for(int i=1; i<=N; i++) {
            tree[i] = new Node(-1, -1, -1);
            min[i] = MAX;
            max[i] = MIN;
        }

        // 트리 입력
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());

            tree[num].left = left;
            tree[num].right = right;

            // 자식 노드가 존재한다면 자식 노드의 부모를 현재 노드로 설정
            if (left != -1) tree[left].parent = num;
            if (right != -1) tree[right].parent = num;
        }

        br.close();
    }//init

    private static class Node {
        int parent;
        int left;
        int right;
        Node(int parent, int left, int right) {
            this.parent = parent;
            this.left = left;
            this.right = right;
        }
    }//Node

}//class
/*

19
1 2 3
2 4 5
3 6 7
4 8 -1
5 9 10
6 11 12
7 13 -1
8 -1 -1
9 14 15
10 -1 -1
11 16 -1
12 -1 -1
13 17 -1
14 -1 -1
15 18 -1
16 -1 -1
17 -1 19
18 -1 -1
19 -1 -1
------------
3 18

 */