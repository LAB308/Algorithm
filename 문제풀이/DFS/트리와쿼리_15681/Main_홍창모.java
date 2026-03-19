package 문제풀이.DFS.트리와쿼리_15681;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * GRAPH = new ArrayList[N+1];
 *
 * for ( 1 부터 N ) {
 *     GRAPH[i] = new ArrayList<>();
 * }
 *
 * for( 1 부터 N ) {
 *     // u, v 입력값
 *     GRAPH[u].add(v);
 *     GRAPH[v].add(u);
 * }
 *
 * for( 0 부터 Q ) {
 *     VISITED = new boolean[N+1];
 *     dfs(q);
 * }
 * */

public class Main_홍창모 {

    static int N, R, Q;
    static List<Integer>[] GRAPH;
    static boolean[] VISITED;
    static int[] SUBTREE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        GRAPH = new ArrayList[N+1];
        SUBTREE = new int[N+1];
        VISITED = new boolean[N+1];

        for( int i = 1; i <= N; i++ ) {
            GRAPH[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            GRAPH[u].add(v);
            GRAPH[v].add(u);
        }

        dfs(R);

        for (int i = 0; i < Q; i++) {
            int q = Integer.parseInt(br.readLine());

            System.out.println(SUBTREE[q]);
        }
    }

    private static void dfs(int node) {
        VISITED[node] = true;
        SUBTREE[node] = 1;

        for( int next : GRAPH[node] ) {
            if( !VISITED[next] ) {
                dfs(next);
                SUBTREE[node] += SUBTREE[next];
            }
        }
    }
}
