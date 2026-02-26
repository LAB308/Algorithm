package 문제풀이.BFS.DSLR_9019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * StringBuilder sb = new StringBuilder();
 * VISITED = new boolean[10001];
 *
 * for( 0 부터 T ) {
 *     // A, B 의 정보를 받아옴
 *     bfs( A, B );
 *     sb.append("\n");
 * }
 *
 * System.out.println(sb);
 *
 * void bfs( int a, int b ) {
 *     Queue<Node> q = new LinkedList<>();
 *     // Queue에 저장
 *
 *     while(q 존재할 때까지) {
 *          StringBuilder str = new StringBuilder();
 *
 *          if( currA == b ) {
 *              // Node에 저장된 문자열
 *              sb.append(currStr);
 *              return;
 *          }
 *
 *         int D = currA * 2 > 10000 ? currA * 2 % 10000 : currA * 2;
 *         int S = currA - 1 < 0 ? 9999 : currA - 1;
 *         int thousand = currA / 1000;
 *         int hundred = (currA - (thousand * 1000)) / 100;
 *         int tens = (currA - (thousand * 1000) - (hundred * 100)) / 10;
 *         int ones = (currA - (thousand * 1000) - (hundred * 100) - (tens * 10));
 *         int L = Integer.parseInt(str.append( hundred ).append(tens).append(ones).append(thousand));
 *         int R = Integer.parseInt(str.append( ones ).append(thousand).append(hundred).append(tens));
 *
 *         if( !VISITED[D] ) {
 *          // 큐에 추가
 *         } ...
 *     }
 * }
 * */

public class Main_홍창모 {

    static int T;
    static StringBuilder SB = new StringBuilder();
    static boolean[] VISITED;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            VISITED = new boolean[10001];

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            bfs(A, B);
            SB.append("\n");

        }

        System.out.println(SB);
    }

    private static void bfs(int a, int b) {
        int[] prev = new int[10001];
        char[] how = new char[10001];

        Arrays.fill(prev, -1);

        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(a);
        VISITED[a] = true;

        while (!q.isEmpty()) {

            int currNum = q.poll();

            if (currNum == b) break;

            int D = (currNum * 2) % 10000;
            int S = (currNum == 0) ? 9999 : currNum - 1;
            int L = (currNum % 1000) * 10 + (currNum / 1000);
            int R = (currNum / 10) + (currNum % 10) * 1000;

            if (!VISITED[D]) {
                VISITED[D] = true;
                prev[D] = currNum;
                how[D] = 'D';
                q.add(D);
            }

            if (!VISITED[S]) {
                VISITED[S] = true;
                prev[S] = currNum;
                how[S] = 'S';
                q.add(S);
            }

            if (!VISITED[L]) {
                VISITED[L] = true;
                prev[L] = currNum;
                how[L] = 'L';
                q.add(L);
            }

            if (!VISITED[R]) {
                VISITED[R] = true;
                prev[R] = currNum;
                how[R] = 'R';
                q.add(R);
            }
        }

        StringBuilder result = new StringBuilder();
        int curr = b;

        while (curr != a) {
            result.append(how[curr]);
            curr = prev[curr];
        }

        SB.append(result.reverse());
    }
}
