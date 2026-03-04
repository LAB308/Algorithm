package 문제풀이.BFS.탈출_3055;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * MAP = new char[R][C];
 * VISITED = new boolean[R][C];
 *
 * for( 0 부터 R ) {
 *     for( 0 부터 C ) {
 *         // 숲의 정보 set
 *     }
 * }
 *
 * while( true ) {
 *      // 1. 물에 대한 정보를 큐에 쌓음
 *      for( 0 부터 R ) {
 *          for( 0 부터 C ) {
 *              if( MAP[i][j] == '*' ) {
 *                  q.add(// 물의 위치);
 *              }
 *          }
 *      }
 *
 *      // 2. 물이 동시에 차오름
 *      flooding(// 큐);
 *
 *      boolean isMove = false;
 *
 *      // 3. 고슴도치가 이동(물아 차오를 곳으로 이동이 불가)
 *      for( 0 부터 R ) {
 *          for( 0 부터 C ) {
 *              if( MAP[i][j] == 'S' ) {
 *                  isMove = move(i, j);
 *              }
 *          }
 *      }
 *
 *      if( !isMove ) return time;
 *      time++;
 * }
 *
 * */

public class Main_홍창모 {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int R, C;
    static char[][] MAP;
    static boolean[][] VISITED;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        MAP = new char[R][C];
        VISITED = new boolean[R][C];

        Queue<Node> waterQ = new LinkedList<>();
        Queue<Node> hedgehogQ = new LinkedList<>();

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                char c = str.charAt(j);
                MAP[i][j] = c;
                if( c == '*' ) {
                    waterQ.add(new Node(i, j));
                } else if( c == 'S' ) {
                    hedgehogQ.add(new Node(i,j));
                    VISITED[i][j] = true;
                    MAP[i][j] = '.';
                }
            }
        }

        int time = 0;

        while( true ) {
            // 1. 물을 이동
            flooding(waterQ);


            int hedgeSize = hedgehogQ.size();

            if( hedgeSize == 0 ) {
                System.out.println("KAKTUS");
                break;
            }


            // 2. 고슴도치 이동
            if(moveHedge(hedgehogQ)) {
                System.out.println(time+1);
                break;
            }

            time++;
        }

    }

    private static boolean moveHedge(Queue<Node> hedgehogQ) {
        boolean isCave = false;
        int qSize = hedgehogQ.size();

        for (int q = 0; q < qSize; q++) {
            Node curr = hedgehogQ.poll();

            for (int i = 0; i < 4; i++) {
                int nr = curr.row + dx[i];
                int nc = curr.col + dy[i];

                if( nr >= 0 && nr < R && nc >= 0 && nc < C ) {
                    if( MAP[nr][nc] == '.' && !VISITED[nr][nc] ) {
                        hedgehogQ.add(new Node(nr, nc));
                        VISITED[nr][nc] = true;
                    } else if(MAP[nr][nc] == 'D') {
                        isCave = true;
                    }
                }
            }
        }

        return isCave;
    }

    private static void flooding(Queue<Node> waterQ) {
        int qSize = waterQ.size();

        for ( int q = 0; q < qSize; q++ ) {
            Node curr = waterQ.poll();

            for (int i = 0; i < 4; i++) {
                int nr = curr.row + dx[i];
                int nc = curr.col + dy[i];

                if( nr >= 0 && nr < R && nc >= 0 && nc < C ) {
                    if( MAP[nr][nc] == '.' ) {
                        MAP[nr][nc] = '*';
                        waterQ.add(new Node(nr, nc));
                    }
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
