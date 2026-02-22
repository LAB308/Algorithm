package 문제풀이.BFS.인구이동_16234;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * // 도시의 정보를 담을 배열
 * MAP = new int[N][N];
 * VISITED = new boolean[N][N];
 *
 * // 이동이 가능한 도시들의 집합을 담을 List
 * List<List<Possible>> POSSIBLE_CITY;
 *
 * for( 0 부터 N 까지 ) {
 *     for( 0 부터 N 까지 ) {
 *        // 도시 인구 정보
 *     }
 * }
 *
 * int days = 0;
 *
 * while(true) {
 *      // 일자마다 초기화
 *      POSSIBLE_CITY = new ArrayList<>();
 *
 *      int cnt = 0;
 *     // 1. 각 도시 마다 L명 이상, R명 이하를 만족하는 경계 해제
 *     for( 0 부터 N ) {
 *         for( 0 부터 N ) {
 *             if( !VISITED[i][j] )
 *             cnt += bfs(i,j);
 *         }
 *     }
 *      - 경계 해제된 값이 없다면 break;
 *     // 2. 인구 이동
 *       - 각 칸의 인구수 초기화 (연합의 인구수) / (연합을 이루고 있는 칸의 개수) 계산
 *       - 경계가 해제된 도시 인구 초기화
 *
 *     // days++;
 * }
 *
 * */
public class Main_홍창모 {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int N, L, R;
    static int[][] CITY;
    static boolean[][] VISITED;
    static List<List<Possible>> POSSIBLE_CITY;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        CITY = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                CITY[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int days = 0;

        while (true) {
            VISITED = new boolean[N][N];
            POSSIBLE_CITY = new ArrayList<>();

            int cnt = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if( !VISITED[i][j] ) {
                        List<Possible> possibles = new ArrayList<>();

                        cnt = bfs(i,j,possibles);

                        if( cnt > 1 ) {
                            // 처음 시작 도시 정보 추가
                            possibles.add(new Possible(i,j,CITY[i][j]));
                            POSSIBLE_CITY.add(possibles);
                        }
                    }
                }
            }

            if( POSSIBLE_CITY.isEmpty() ) break;

            for (int i = 0; i < POSSIBLE_CITY.size(); i++) {
                int sum = POSSIBLE_CITY.get(i).stream()
                        .mapToInt(p -> p.value)
                        .sum();

                int result = sum / POSSIBLE_CITY.get(i).size();

                for( Possible city : POSSIBLE_CITY.get(i) ) {
                    CITY[city.row][city.col] = result;
                }
            }

            days++;
        }

        System.out.println(days);
    }

    private static int bfs(int row, int col, List<Possible> possibles) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(row, col));
        VISITED[row][col] = true;

        int cnt = 0;

        while (!q.isEmpty()) {
            Node curr = q.poll();

            int cr = curr.row;
            int cc = curr.col;

            // 연합의 수
            cnt++;

            for (int i = 0; i < 4; i++) {
                int nr = curr.row + dx[i];
                int nc = curr.col + dy[i];

                if( nr >= 0 && nr < N && nc >= 0 && nc < N && !VISITED[nr][nc] ) {
                    int diff = Math.abs(CITY[cr][cc] - CITY[nr][nc]);

                    if( diff >= L && diff <= R ) {
                        VISITED[nr][nc] = true;
                        q.add(new Node(nr, nc));
                        // 이동 가능한 도시 정보 add
                        possibles.add(new Possible(nr, nc, CITY[nr][nc]));
                    }
                }
            }
        }

        return cnt;
    }

    private static class Node {
        int row;
        int col;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private static class Possible {
        int row;
        int col;
        int value;

        public Possible(int row, int col, int value) {
            this.row = row;
            this.col = col;
            this.value = value;
        }
    }

}
