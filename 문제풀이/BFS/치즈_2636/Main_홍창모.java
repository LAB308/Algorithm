package 문제풀이.BFS.치즈_2636;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * // 상하좌우가 모두 치즈인 경우 - 치즈의 구멍을 둘러싼 치즈
 *
 * dx = {-1, 1, 0, 0};
 * dy = {0, 0, -1, 1};
 *
 * MAP = new int[N][M];
 * VISITED = new boolean[N][M];
 *
 * for( 0 부터 N ) {
 *     for( 0 부터 M ) {
 *         // 치즈 정보 set
 *     }
 * }
 *
 * while(true) {
 *  // 1. 가장자리인 치즈의 정보를 저장
 *      - if( 가장자리 치즈가 없음 ) {
 *          break;
 *      }
 *  // 2. 가장자리 치즈를 녹임
 *  // 3. 시간 + 1
 *  // 4. 남은 치즈의 칸의 개수 저장
 * }
 *
 *
 * */
public class Main_홍창모 {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int N, M;
    static int[][] MAP;
    static boolean[][] VISITED;

    public static void main(String[] args) throws IOException {
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

        int hour = 0;
        int lastCnt = 0;

        while (true) {
            List<Node> cheeses = new ArrayList<>();
            VISITED = new boolean[N][M];

            bfs( 0, 0, cheeses );

            if( cheeses.isEmpty() ) {
                System.out.println(hour);
                System.out.println(lastCnt);
                break;
            }

            lastCnt = cheeses.size();

            for( Node cheese : cheeses ) {
                int row = cheese.row;
                int col = cheese.col;

                MAP[row][col] = 0;
            }

            hour++;

        }

    }

    private static void bfs(int row, int col, List<Node> cheeses) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(row, col));
        VISITED[row][col] = true;

        while (!q.isEmpty()) {
            Node curr = q.poll();

            for (int d = 0; d < 4; d++) {
                int nr = curr.row + dx[d];
                int nc = curr.col + dy[d];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                if (VISITED[nr][nc]) continue;

                // 공기면 계속 확장
                if (MAP[nr][nc] == 0) {
                    VISITED[nr][nc] = true;
                    q.add(new Node(nr, nc));
                }
                // 치즈면 "이번 시간에 녹을 대상"으로만 추가
                else if (MAP[nr][nc] == 1) {
                    VISITED[nr][nc] = true;
                    cheeses.add(new Node(nr, nc));
                }
            }
        }
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
