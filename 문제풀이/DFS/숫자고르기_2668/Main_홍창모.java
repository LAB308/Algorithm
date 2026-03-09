package 문제풀이.DFS.숫자고르기_2668;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * totalCnt = 0;
 * NUMBERS = new int[N+1];
 * VISITED = new boolean[N+1];
 * FINISHED = new boolean[N+1];
 *
 * for( 1 부터 N ) {
 *     // 숫자 집합 정보 setting
 * }
 *
 * for( 1 부터 N ) {
 *     // dfs( i );
 * }
 *
 * void dfs( int curr ) {
 *   VISITED[curr] = true;
 *   int next = NUMBERS[curr];
 *
 *   if( !VISITED[next] ) {
 *
 *       dfs(next);
 *   } else if( !FINISHED[next] ){
 *      totalCnt++;
 *
 *      for( int i = next; i != curr; i = NUMBERS[i] ) {
 *          totalCnt++;
 *      }
 *   }
 *
 *   FINISHED[curr] = true;
 *
 * }
 *
 * */

public class Main_홍창모 {

    static int N;
    static int[] NUMBERS;
    static boolean[] VISITED, FINISHED;
    static List<Integer> RESULT = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        NUMBERS = new int[N + 1];
        VISITED = new boolean[N + 1];
        FINISHED = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            int num = Integer.parseInt(br.readLine());

            NUMBERS[i] = num;
        }

        for (int i = 1; i <= N; i++) {
            if( !VISITED[i] ) {
                dfs(i);
            }
        }

        System.out.println(RESULT.size());

        Collections.sort(RESULT);

        for ( Integer x : RESULT ) {
            System.out.println(x);
        }

    }

    private static void dfs(int curr) {
        VISITED[curr] = true;

        int next = NUMBERS[curr];

        if( !VISITED[next] ) {
            dfs(next);
        } else if( !FINISHED[next] ) {
            int i = next;

            while (true) {
                RESULT.add(i);
                if (i == curr) break;
                i = NUMBERS[i];
            }
        }

        FINISHED[curr] = true;
    }
}
