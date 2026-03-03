package 문제풀이.BFS.보물섬_2589;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * MAX = Integer.MIN_VALUE;
 * MAP = new char[N][M];
 * VISITED = new boolean[N][M];
 *
 * for( 0 부터 N ) {
 *     for( 0 부터 M ) {
 *         // 지도 정보 set
 *     }
 * }
 *
 * for( 0 부터 N ) {
 *      for( 0 부터 M ) {
 *          if( 'L' 인 경우 )
 *          bfs(row, col, 0);
 *      }
 * }
 *
 *
 *
 * // MAX 출력
 * */
public class Main_홍창모 {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int N, M, MAX = Integer.MIN_VALUE;
    static char[][] MAP;
    static boolean[][] VISITED;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        MAP = new char[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                MAP[i][j] = str.charAt(j);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if( MAP[i][j] == 'L' ) {
                    bfs(i, j, 0);
                }
            }
        }

        System.out.println(MAX);
    }

    private static int bfs(int row, int col, int depth) {
        VISITED = new boolean[N][M];

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(row, col, depth));
        VISITED[row][col] = true;

        while (!q.isEmpty()) {
            Node curr = q.poll();

            int currRow = curr.row;
            int currCol = curr.col;
            int currDepth = curr.depth;

            MAX = Math.max(MAX, currDepth);

            for (int i = 0; i < 4; i++) {
                int nextRow = currRow + dx[i];
                int nextCol = currCol + dy[i];
                int nextDepth = currDepth + 1;

                if( nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M) continue;
                if( VISITED[nextRow][nextCol] ) continue;

                if( MAP[nextRow][nextCol] == 'L' ) {
                    VISITED[nextRow][nextCol] = true;
                    q.add(new Node(nextRow, nextCol, nextDepth));
                }
            }
        }

        return depth;
    }

    private static class Node {
        int row;
        int col;
        int depth;

        public Node(int row, int col, int depth) {
            this.row = row;
            this.col = col;
            this.depth = depth;
        }
    }
}
