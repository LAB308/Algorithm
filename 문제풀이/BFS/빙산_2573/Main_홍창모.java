package 문제풀이.BFS.빙산_2573;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * MAP = new int[N][M];
 *
 * for( 0 부터 N ) {
 *     for( 0 부터 M ) {
 *         // 빙산 높이 정보 set
 *     }
 * }
 *
 * int year = 0;
 * while(true) {
 *      VISITED = new boolean[N][M];
 *
 *      // 1. 빙산 덩어리 개수 확인(bfs)
 *      - if( 빙산 덩어리가 없다면 ) {
 *          return;
 *      }
 *     - if( 빙산 덩어리 개수가 2 이상 ) {
 *         return;
 *     }
 *
 *     // 2. 빙산을 녹임
 *
 *     year++;
 *
 *
 * }
 * */
public class Main_홍창모 {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int N, M;
    static int[][] MAP;
    static boolean[][] VISITED;

    public static void main( String[] args ) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        MAP = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                MAP[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int year = 0;

        while (true) {
            int[][] metings = new int[N][M];
            VISITED = new boolean[N][M];

            int icebergCnt = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if( !VISITED[i][j] && MAP[i][j] > 0 ) {
                        icebergCnt++;
                        bfs(i, j);
                    }
                }
            }

            if( icebergCnt == 0 ) {
                System.out.println(0);
                return;
            }

            if (icebergCnt >= 2) {
                System.out.println(year);
                return;
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if( MAP[i][j] > 0 ) {
                        icebergCnt++;
                        melting(i, j, metings);
                    }
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    // 동시에 빙산을 녹임
                    MAP[i][j] = Math.max(0, MAP[i][j] - metings[i][j]);
                }
            }

            year++;
        }

    }

    private static void bfs(int row, int col) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(row, col));
        VISITED[row][col] = true;

        while (!q.isEmpty()) {
            Node curr = q.poll();

            for (int i = 0; i < 4; i++) {
                int nr = curr.row + dx[i];
                int nc = curr.col + dy[i];

                if( nr >= 0 && nr < N && nc >= 0 && nc < M && !VISITED[nr][nc]) {
                    if( MAP[nr][nc] != 0 ) {
                        VISITED[nr][nc] = true;
                        q.add(new Node(nr, nc));
                    }
                }
            }
        }
    }

    private static int[][] melting(int row, int col, int[][] metings) {

        int cnt = 0;

        for (int i = 0; i < 4; i++) {
            int nr = row + dx[i];
            int nc = col + dy[i];

            if( nr >= 0 && nr < N && nc >= 0 && nc < M) {
                if( MAP[nr][nc] == 0 ) {
                    cnt++;
                }
            }
        }

        metings[row][col] = cnt;

        return metings;
    }

    private static class Node {
        int row;
        int col;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
