package 삼성SW.문제풀이.상어초등학교_21608;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_홍창모 {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int N;
    static int[][] SEATS;
    static int[] STUDENT_ARR;
    static List<Integer>[] STUDENTS_INFO;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        SEATS = new int[N+1][N+1];
        STUDENT_ARR = new int[N*N+1];
        STUDENTS_INFO = new ArrayList[N*N+1];

        for (int i = 1; i <= N * N; i++) {
            STUDENTS_INFO[i] = new ArrayList<>();
        }


        for (int i = 1; i <= N * N; i++) {
            st = new StringTokenizer(br.readLine());
            int target = Integer.parseInt(st.nextToken());
            STUDENT_ARR[i] = target;

            for (int j = 0; j < 4; j++) {
                STUDENTS_INFO[target].add(Integer.parseInt(st.nextToken()));
            }
        }

        for (int i = 1; i <= N*N; i++) {
            int student = STUDENT_ARR[i];

            seating(student);
        }

        int value = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                // 만족도 검사
                value += satisfaction(i, j);
            }
        }

        System.out.println(value);

    }

    private static int satisfaction(int row, int col) {
        int value = 0;
        int cnt = 0;

        int target = SEATS[row][col];

        for (int i = 0; i < 4; i++) {
            int nx = row + dx[i];
            int ny = col + dy[i];


            if( nx > 0 && nx <= N && ny > 0 && ny <= N ) {
                if( STUDENTS_INFO[target].contains(SEATS[nx][ny]) ) {
                    cnt++;
                }
            }

        }

        if( cnt == 1 ) {
            value = 1;
        } else if( cnt == 2 ) {
            value = 10;
        } else if( cnt == 3 ) {
            value = 100;
        } else if( cnt == 4 ) {
            value = 1000;
        }


        return value;
    }

    private static void seating(int student) {
        int bestR = 0;
        int bestC = 0;
        int bestLike = -1;
        int bestEmpty = -1;

        // 좋아하는 학생 List
        List<Integer> likeStudents = STUDENTS_INFO[student];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                int emptySeatCnt = 0;
                int likeCnt = 0;

                if( SEATS[i][j] != 0 ) continue;

                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if( nx > 0 && nx <= N && ny > 0 && ny <= N ) {
                        if( SEATS[nx][ny] == 0 ) emptySeatCnt++;
                        if( likeStudents.contains(SEATS[nx][ny]) ) likeCnt++;
                    }
                }

                if( bestR == 0 ) {
                    bestLike = likeCnt;
                    bestEmpty = emptySeatCnt;
                    bestR = i;
                    bestC = j;
                } else if ( bestLike < likeCnt ) {
                    // 좋아하는 학생이 가장 많은 좌석 정보
                    bestLike = likeCnt;
                    bestEmpty = emptySeatCnt;
                    bestR = i;
                    bestC = j;
                } else if( likeCnt == bestLike && emptySeatCnt > bestEmpty ) {
                    // 빈 자리가 가장 많은 좌석 정보
                    bestEmpty = emptySeatCnt;
                    bestR = i;
                    bestC = j;
                } else if( likeCnt == bestLike && emptySeatCnt == bestEmpty ) {
                    if( i < bestR ) {
                        bestR = i;
                    } else if( i == bestR && j < bestC ) {
                        bestC = j;
                    }
                }

            }
        }

        SEATS[bestR][bestC] = student;
    }
}
