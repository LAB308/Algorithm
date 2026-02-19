package 문제풀이.BFS.연구소_14502;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 *  dx = {-1, 0, 1, 0};
 *  dy = {0, -1, 0, 1};
 *  List<Point> emptyArea = new ArrayList();
 *  List<Point> virusArea = new ArrayList();
 *
 *  int[][] MAP = new int[N][M];
 *  boolean[][] VISITED = new boolean[N][M];
 *
 *  for(0 부터 N) {
 *      for( 0 부터 M ) {
 *          // 연구소 정보 저장
 *          // 0 : 빈 공간
 *          // 1 : 벽
 *          // 2 : 바이러스
 *          // 빈 공간 , 바이러스 좌표 별도 저장( emptyArea, virusArea )
 *      }
 *  }
 *
 *
 *
 *  for()
 *
 *
 *  void bfs(row, col) {
 *
 *  }
 *
 *  // 반복문을 돌려 빈 공간에 대한 리스트 크기만큼
 *  for( int i = 0 ; ... ) {
 *      if( 새로운 벽 리스트 크기 == 3 ) {
 *          for( Point virus : virusArea 크기 ) {
 *  *           // 바이러스 위치만 움직일 수 있음
 *  *           bfs( virus.row, virus.col );
 *  *       }
 *          // 초기화
 *
 *      } else {
 *          // 새로운 벽 리스트 조합을 만들어
 *          for( int j = i+1; ... ) {
 *              for( int k = j + 1; ... ) {
 *                  newWall.add(new Point(j, k));
 *              }
 *          }
 *      }
 *  }
 * */

public class Main_홍창모 {

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static List<Point> emptyArea = new ArrayList<>();
    static List<Point> virusArea = new ArrayList<>();

    static int N, M, MAX = Integer.MIN_VALUE;
    static int[][] MAP;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        MAP = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int target = Integer.parseInt(st.nextToken());
                MAP[i][j] = target;

                if( target == 0 ) {
                    // 빈 공간
                    emptyArea.add(new Point(i, j));
                }else if( target == 2 ) {
                    // 바이러스 공간
                    virusArea.add(new Point(i, j));
                }

            }
        }
        for (int i = 0; i < emptyArea.size(); i++) {
            for (int j = i + 1; j < emptyArea.size(); j++) {
                for (int k = j + 1; k < emptyArea.size(); k++) {

                    Point w1 = emptyArea.get(i);
                    Point w2 = emptyArea.get(j);
                    Point w3 = emptyArea.get(k);

                    int safe = bfs(w1, w2, w3);
                    MAX = Math.max(MAX, safe);
                }
            }

        }

        System.out.println(MAX);

    }

    private static int bfs( Point w1, Point w2, Point w3 ) {
        int[][] map = copyMap(MAP);

        Queue<Point> q = new LinkedList<>();

        for ( Point virus : virusArea ) {
            q.add(virus);
        }

        // 새로운 벽의 위치를 세팅
        map[w1.row][w1.col] = 1;
        map[w2.row][w2.col] = 1;
        map[w3.row][w3.col] = 1;


        while (!q.isEmpty()) {
            Point curr = q.poll();

            for (int i = 0; i < 4; i++) {
                int nextRow = curr.row + dx[i];
                int nextCol = curr.col + dy[i];

                if( nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < M ) {
                    if(  map[nextRow][nextCol] == 0 ) {
                        map[nextRow][nextCol] = 2;
                        q.add(new Point(nextRow, nextCol));
                    }
                }
            }
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if( map[i][j] == 0 ) cnt++;
            }
        }

        return cnt;
    }

    private static int[][] copyMap(int[][] origin) {
        int[][] copy = new int[N][M];

        for (int i = 0; i < N; i++) {
            System.arraycopy(origin[i], 0, copy[i], 0, M);
        }

        return copy;
    }

    private static class Point {
        int row;
        int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
