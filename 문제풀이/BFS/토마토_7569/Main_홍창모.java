package 문제풀이.BFS.토마토_7569;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_홍창모 {

    static int[] DX = {1, -1, 0, 0, 0, 0};
    static int[] DY = {0, 0, 1, -1, 0, 0};
    static int[] DZ = {0, 0, 0, 0, 1, -1};

    static int N, M, H;
    static int[][][] TOMATOES;
    static boolean[][][] VISITED;

    public static void main( String[] args ) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        TOMATOES = new int[H][N][M];
        VISITED = new boolean[H][N][M];

        for (int h = 0; h < H; h++) {
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    TOMATOES[h][i][j] = Integer.parseInt(st.nextToken());
                }
            }
        }

        Queue<Tomato> q = new LinkedList<>();

        for (int h = 0; h < H; h++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if( TOMATOES[h][i][j] == 1 ) {
                        q.add(new Tomato(h,i,j, 0));
                        VISITED[h][i][j] = true;
                    }
                }
            }
        }

        int minDay = bfs(q);

        for (int h = 0; h < H; h++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if( TOMATOES[h][i][j] == 0 ) {
                        System.out.println(-1);
                        return;
                    }
                }
            }
        }

        System.out.println(minDay);

    }

    private static int bfs(Queue<Tomato> q) {
        int minDay = 0;

        while (!q.isEmpty()) {
            Tomato curr = q.poll();

            int currH = curr.height;
            int currR = curr.row;
            int currC = curr.col;
            int currDay = curr.day;

            minDay = Math.max(currDay, minDay);

            for (int i = 0; i < 6; i++) {
                int nextH = currH + DZ[i];
                int nextR = currR + DX[i];
                int nextC = currC + DY[i];

                if( nextH >= 0 && nextH < H && nextR >= 0 && nextR < N && nextC >= 0 && nextC < M ) {
                    if( !VISITED[nextH][nextR][nextC] && TOMATOES[nextH][nextR][nextC] == 0 ) {
                        TOMATOES[nextH][nextR][nextC] = 1;
                        q.add(new Tomato(nextH, nextR, nextC, currDay + 1));
                        VISITED[nextH][nextR][nextC] = true;
                    }
                }
            }
        }

        return minDay;
    }

    private static class Tomato {
        int height;
        int row;
        int col;
        int day;

        public Tomato(int height, int row, int col, int day) {
            this.height = height;
            this.row = row;
            this.col = col;
            this.day = day;
        }
    }

}
