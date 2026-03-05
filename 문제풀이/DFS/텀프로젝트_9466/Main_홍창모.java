package 문제풀이.DFS.텀프로젝트_9466;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_홍창모 {

    static int[] STUDENTS;
    static boolean[] VISITED;
    static boolean[] FINISHED;
    static int T, teamCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());

            STUDENTS = new int[n+1];
            VISITED = new boolean[n+1];
            FINISHED = new boolean[n+1];

            st = new StringTokenizer(br.readLine());

            for (int i = 1; i <= n; i++) {
                STUDENTS[i] = Integer.parseInt(st.nextToken());
            }

            teamCount = 0;

            for (int i = 1; i <= n; i++) {
                if( !VISITED[i] ) {
                    dfs(i);
                }
            }

            System.out.println(n - teamCount);

        }
    }

    private static void dfs(int curr) {
        VISITED[curr] = true;

        int next = STUDENTS[curr];

        if (!VISITED[next]) {
            dfs(next);
        }
        else if (!FINISHED[next]) {
            // 사이클 발견
            teamCount++;

            for (int i = next; i != curr; i = STUDENTS[i]) {
                teamCount++;
            }
        }

        FINISHED[curr] = true;
    }
}
