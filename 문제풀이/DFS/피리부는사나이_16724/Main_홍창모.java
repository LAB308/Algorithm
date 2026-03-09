package 문제풀이.DFS.피리부는사나이_16724;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SAFE_ZONE = 0;
 * MAP = new char[N][M];
 * VISITED = new boolean[N][M];
 * FINISHED = new boolean[N][M];
 *
 * for( 0 부터 N ) {
 *     for( 0 부터 M ) {
 *         // 방향 정보 setting
 *     }
 * }
 *
 * for( 0 부터 N ) {
 *     for( 0 부터 M ) {
 *         if( !VISITED[i][j] ) dfs(i, j);
 *     }
 * }
 *
 * void dfs(int row, int col) {
 *     VISITED[row][col] = true;
 *
 *     char next = MAP[row][col];
 *
 *     int nr = 0;
 *     int nc = 0;
 *
 *     if( next == 'L' ) {
 *         nr = row;
 *         nc = col - 1;
 *     } else if( next == 'R' ) {
 *         nr = row;
 *         nc = col + 1;
 *     } else if( next == 'U' ) {
 *         nr = row - 1;
 *         nc = col;
 *     } else if( next == 'D' ) {
 *         nr = row + 1;
 *         nc = col;
 *     }
 *
 *     // nr, nc 범위 체크 로직
 *
 *     if( !VISITED[nr][nc] ) {
 *         dfs(nr, nc);
 *     } else if( !FINISHED[nr][nc] ) {
 *         SAFE_ZONE++;
 *     }
 *
 * }
 *
 * */

public class Main_홍창모 {

    static int N, M, SAFE_ZONE = 0;
    static char[][] MAP;
    static boolean[][] VISITED, FINISHED;

    public static void main( String[] args ) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        MAP = new char[N][M];
        VISITED = new boolean[N][M];
        FINISHED = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                MAP[i][j] = str.charAt(j);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(!VISITED[i][j]) dfs(i, j);
            }
        }

        System.out.println(SAFE_ZONE);
    }

    private static void dfs(int row, int col) {
        VISITED[row][col] = true;

        char next = MAP[row][col];

        int nr = 0;
        int nc = 0;

        if( next == 'L' ) {
            nr = row;
            nc = col - 1;
        } else if( next == 'R' ) {
            nr = row;
            nc = col + 1;
        } else if( next == 'U' ) {
            nr = row - 1;
            nc = col;
        } else if( next == 'D' ) {
            nr = row + 1;
            nc = col;
        }

        if( nr < 0 || nr >= N || nc < 0 || nc >= M ) return;

        if( !VISITED[nr][nc] ) {
            dfs(nr, nc);
        } else if( !FINISHED[nr][nc] ) {
            SAFE_ZONE++;
        }

        FINISHED[row][col] = true;
    }
}
