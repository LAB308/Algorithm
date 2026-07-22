package 프로그래머스._20260719_과제.게임맵최단거리_1844;

import java.util.ArrayDeque;
import java.util.Deque;

public class Main_홍창모 {

    private static int[] DX = {-1,1,0,0};
    private static int[] DY = {0,0,-1,1};
    private static boolean[][] VISITED;

    public static void main(String[] args) {
        int[][] map = {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}};

        System.out.println(solution(map));
    }

    public static int solution(int[][] maps) {
        int rowSize = maps.length;
        int colSize = maps[0].length;


        VISITED = new boolean[rowSize][colSize];

        return bfs(0, 0, rowSize, colSize, 1,  maps);
    }

    private static int bfs(int row, int col, int rowSize, int colSize, int depth, int[][] maps) {
        Deque<Node> q = new ArrayDeque<>();
        VISITED[row][col] = true;

        q.offer(new Node(row, col));

        while (!q.isEmpty()) {
            int size = q.size();

            // 현재 depth에 해당하는 노드를 모두 처리
            for (int i = 0; i < size; i++) {
                Node curr = q.poll();

                if (curr.row == rowSize - 1 &&
                        curr.col == colSize - 1) {
                    return depth;
                }

                for (int j = 0; j < 4; j++) {
                    int nextRow = curr.row + DX[j];
                    int nextCol = curr.col + DY[j];

                    if (nextRow >= 0 && nextRow < rowSize && nextCol >= 0 && nextCol < colSize && maps[nextRow][nextCol] == 1 && !VISITED[nextRow][nextCol]) {
                        VISITED[nextRow][nextCol] = true;
                        q.offer(new Node(nextRow, nextCol));
                    }
                }
            }

            depth++;
        }

        return -1;

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
