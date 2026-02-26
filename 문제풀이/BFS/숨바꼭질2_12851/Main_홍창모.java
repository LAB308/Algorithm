package 문제풀이.BFS.숨바꼭질2_12851;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * MAP = new int[100001];
 * VISITED = new boolean[100001];
 *
 * */

public class Main_홍창모 {

    static int N, K, MIN = Integer.MAX_VALUE;
    static int[] MAP;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        MAP = new int[100001];

        Arrays.fill(MAP, -1);

        bfs( N, K );
    }

    private static void bfs(int start, int end) {
        int[] cnt = new int[100001];

        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(start);

        MAP[start] = 0;
        cnt[start] = 1;

        while (!q.isEmpty()) {
            int curr = q.poll();
            int currDist = MAP[curr];

            if (MIN != Integer.MAX_VALUE && currDist > MIN) continue;

            if (curr == end) {
                MIN = currDist;
                continue;
            }

            int[] nexts = {curr - 1, curr + 1, curr * 2};

            for (int nx : nexts) {
                if (nx < 0 || nx > 100000) continue;

                // 처음 방문
                if (MAP[nx] == -1) {
                    MAP[nx] = currDist + 1;
                    cnt[nx] = cnt[curr];
                    q.add(nx);
                }
                // 같은 최단거리로 또 도달한 경우
                else if (MAP[nx] == currDist + 1) {
                    cnt[nx] += cnt[curr];
                }
            }
        }

        System.out.println(MIN);
        System.out.print(cnt[end]);
    }

}
