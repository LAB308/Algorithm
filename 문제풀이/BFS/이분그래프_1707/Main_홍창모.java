package 문제풀이.BFS.이분그래프_1707;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 *
 * for( 0 부터 K ) {
 *     // V, E 입력값 set
 *     GRAPH = new ArrayList[V+1];
 *
 *     for( 1 부터 V ) {
 *          // 그래프 배열 초기화
 *         GRAPH[i] = new ArrayList<>();
 *     }
 *
 *     for( 0 부터 E ) {
 *       // 인접한 정점 정보 set
 *     }
 *
 *     // 완전탐색으로 1번 정점부터 분할할 수 있는지 확인
 *     for( 1 부터 V ) {
 *          VISITED = new boolean[V+1];
 *          dfs(i);
 *     }
 * }
 *
 * */

public class Main_홍창모 {

    static int K, V, E;
    static int[] COLOR;
    static boolean[] VISITED;
    static List<Integer>[] GRAPH;

    public static void main( String[] args ) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        K = Integer.parseInt(br.readLine());

        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());

            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            GRAPH = new ArrayList[V+1];
            VISITED = new boolean[V+1];
            COLOR = new int[V+1];

            for (int i = 1; i <= V; i++) {
                GRAPH[i] = new ArrayList<>();
            }

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());

                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                // 양방향 정보 set
                GRAPH[u].add(v);
                GRAPH[v].add(u);
            }

            boolean isBipartite = true;

            for (int i = 1; i <= V; i++) {
                if( !VISITED[i] ) {
                    if( !dfs(i) ) {
                        isBipartite = false;
                        break;
                    }
                }
            }

            System.out.println(isBipartite ? "YES" : "NO");

        }
    }

    private static boolean dfs(int idx) {
        Stack<Integer> s = new Stack<>();

        s.add(idx);
        VISITED[idx] = true;
        COLOR[idx] = 1;

        while (!s.isEmpty()) {
            int curr = s.pop();

            for( int next : GRAPH[curr] ) {
                if( !VISITED[next] ) {
                    VISITED[next] = true;
                    // 아직 방문 안 한 정점의 경우 반대 색을 저장
                    COLOR[next] = -COLOR[curr];
                    s.add(next);
                } else if( COLOR[next] == COLOR[curr] ) {
                    return false;
                }
            }

        }

        return true;
    }
}
