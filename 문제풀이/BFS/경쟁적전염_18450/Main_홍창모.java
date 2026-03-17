package 문제풀이.BFS.경쟁적전염_18405;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main_홍창모 {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int N, K, S, X, Y;
    static int[][] TUBE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        TUBE = new int[N + 1][N + 1];
        List<Node> viruses = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                TUBE[i][j] = Integer.parseInt(st.nextToken());

                if (TUBE[i][j] != 0) {
                    viruses.add(new Node(i, j, TUBE[i][j], 0));
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

        // 처음 바이러스들을 번호 순으로 정렬
        Collections.sort(viruses);

        bfs(viruses);

        System.out.println(TUBE[X][Y]);
    }

    private static void bfs(List<Node> viruses) {
        ArrayDeque<Node> q = new ArrayDeque<>();

        for (Node virus : viruses) {
            q.add(virus);
        }

        while (!q.isEmpty()) {
            Node curr = q.poll();

            int row = curr.row;
            int col = curr.col;
            int virusNum = curr.virusNum;
            int time = curr.time;

            if (time == S) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nr = row + dx[i];
                int nc = col + dy[i];

                if (nr >= 1 && nr <= N && nc >= 1 && nc <= N) {
                    if (TUBE[nr][nc] == 0) {
                        TUBE[nr][nc] = virusNum;
                        q.add(new Node(nr, nc, virusNum, time + 1));
                    }
                }
            }
        }
    }

    private static class Node implements Comparable<Node> {
        int row;
        int col;
        int virusNum;
        int time;

        public Node(int row, int col, int virusNum, int time) {
            this.row = row;
            this.col = col;
            this.virusNum = virusNum;
            this.time = time;
        }

        @Override
        public int compareTo(Node o) {
            return this.virusNum - o.virusNum;
        }
    }
}