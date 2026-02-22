package 문제풀이.BFS.아기상어_16236;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_홍창모 {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int N, SHARK_EAT, SHARK_SIZE = 2;
    static int[][] MAP;
    static boolean[][] VISITED;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        MAP = new int[N][N];

        int sharkR = 0;
        int sharkC = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int target = Integer.parseInt(st.nextToken());
                if( target == 9 ) {
                    // 상어의 위치 저장
                    sharkR = i;
                    sharkC = j;
                    MAP[i][j] = 0;
                } else {
                    MAP[i][j] = target;
                }
            }
        }

        int totalTime = 0;

        while( true ) {
            VISITED = new boolean[N][N];

            Target best = bfs(sharkR, sharkC);

            if( best == null ) break;

            totalTime += best.depth;
            sharkR = best.row;
            sharkC = best.col;

            MAP[sharkR][sharkC] = 0;
            SHARK_EAT++;

            if (SHARK_EAT == SHARK_SIZE) {
                SHARK_SIZE++;
                SHARK_EAT = 0;
            }

        }

        System.out.println(totalTime);

    }

    private static Target bfs(int row, int col) {
        Queue<Shark> q = new LinkedList<>();
        q.add(new Shark(row, col, 0));

        VISITED[row][col] = true;

        // 최단 거리를 저장할 객체
        Target best = null;
        int bestDepth = Integer.MAX_VALUE;

        while (!q.isEmpty()) {
            Shark curr = q.poll();

            if( curr.depth > bestDepth ) break;

            for (int i = 0; i < 4; i++) {
                int nr = curr.row + dx[i];
                int nc = curr.col + dy[i];
                int nextDepth = curr.depth + 1;

                if( nr >= 0 && nr < N && nc >= 0 && nc < N && !VISITED[nr][nc] && MAP[nr][nc] <= SHARK_SIZE) {
                    VISITED[nr][nc] = true;
                    q.add(new Shark(nr, nc, nextDepth));

                    if( MAP[nr][nc] > 0 && MAP[nr][nc] < SHARK_SIZE ) {
                        if (best == null || nextDepth < bestDepth) {
                            bestDepth = nextDepth;
                            best = new Target(nr, nc, nextDepth);
                        } else if (nextDepth == bestDepth) {
                            if (nr < best.row || (nr == best.row && nc < best.col)) {
                                best = new Target(nr, nc, nextDepth);
                            }
                        }
                    }
                }
            }
        }

        return best;

    }

    private static class Target {
        int row;
        int col;
        int depth;

        public Target(int row, int col, int depth) {
            this.row = row;
            this.col = col;
            this.depth = depth;
        }
    }

    private static class Shark {
        int row;
        int col;
        int depth;

        public Shark(int row, int col, int depth) {
            this.row = row;
            this.col = col;
            this.depth = depth;
        }
    }
}
