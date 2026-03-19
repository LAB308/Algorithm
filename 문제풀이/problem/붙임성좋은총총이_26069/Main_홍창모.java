package 문제풀이.problem.붙임성좋은총총이_26069;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main_홍창모 {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        HashSet<String> result = new HashSet<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            String first = st.nextToken();
            String second = st.nextToken();

            if( first.equals("ChongChong") || second.equals("ChongChong") ) {
                // 춤추고 있는 총총
                result.add(first);
                result.add(second);
            } else if( result.contains(first) || result.contains(second) ) {
                result.add(first);
                result.add(second);
            }

        }

        System.out.println(result.size());
    }
}
