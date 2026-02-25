package 문제풀이.BFS.뱀과사다리게임_16928;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * // 사다리 정보를 저장할 배열
 * List<Integer>[] LADDERS;
 * // 뱀의 정보를 저장할 배열
 * List<Integer>[] SNAKES;
 *
 * LADDERS = new ArrayList[101];
 * SNAKES = new ArrayList[101];
 * VISITED = new boolean[101];
 *
 * for ( 1 부터 N ) {
 *     LADDERS[i] = new ArrayList<>();
 * }
 *
 * for ( 1 부터 M ) {
 *      SNAKES[i] = new ArrayList<>();
 * }
 *
 * for ( 0 부터 N ) {
 *     // 사다리 칸의 정보 set ( 단방향 )
 *     // x -> y
 * }
 *
 * for ( 0 부터 M ) {
 *     // 뱀 칸의 정보 set ( 단방향 )
 *     // u -> v
 * }
 *
 * bfs(1, 0);
 *
 * void bfs( int point, int depth ) {
 *     // Queue 에 저장
 *     // 방문 체크
 *
 *     while( !q.isEmpty() ) {
 *
 *         if( 현재 칸이 100 ) {
 *             System.out.println(currDepth);
 *             return;
 *         }
 *
 *         if( 현재 칸이 사다리 칸이라면 칸이동 ) {
 *              // Queue 에 저장, 방문 체크
 *         } else if( 현재 칸이 뱀 칸이라면 칸이동 ) {
 *              // Queue 에 저장, 방문 체크
 *         } else {
 *             for( 1 부터 6 ) {
 *  *             // 주사위를 굴렸을 때 나온 수
 *  *             if( nextPoint > 0 && nextPoint <= 100 && !VISITED[nextPoint] ) {
 *  *                 // Queue 에 저장, 방문 체크
 *  *             }
 *  *         }
 *         }
 *     }
 * }
 * */

public class Main_홍창모 {

    static int N, M;
    static int[] LADDERS;
    static int[] SNAKES;
    static boolean[] VISITED;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        LADDERS = new int[101];
        SNAKES = new int[101];
        VISITED = new boolean[101];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            LADDERS[x] = y;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            SNAKES[u] = v;
        }

        bfs(1, 0);


    }

    private static void bfs(int point, int depth) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(point, depth));
        VISITED[point] = true;

        while (!q.isEmpty()) {
            Point curr = q.poll();

            int currPoint = curr.point;
            int currDepth = curr.depth;

            if( currPoint == 100 ) {
                System.out.println(currDepth);
                return;
            }

            for (int i = 1; i <= 6; i++) {
                int nextPoint = currPoint + i;

                if( nextPoint <= 100 && !VISITED[nextPoint] ) {
                    // 사다리 칸의 정보
                    int ladder = LADDERS[nextPoint];
                    // 뱀 칸의 정보
                    int snake = SNAKES[nextPoint];

                    if( ladder > 0 ) {
                        VISITED[ladder] = true;
                        q.add(new Point(ladder, currDepth + 1));
                    } else if( snake > 0 ) {
                        VISITED[snake] = true;
                        q.add(new Point(snake, currDepth + 1));
                    } else {
                        VISITED[nextPoint] = true;
                        q.add(new Point(nextPoint, currDepth + 1));
                    }

                }

            }

        }

    }

    private static class Point {
        int point;
        int depth;

        public Point(int point, int depth) {
            this.point = point;
            this.depth = depth;
        }
    }
}
