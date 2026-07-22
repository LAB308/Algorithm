package 프로그래머스._20260719.타겟넘버_43165;

public class Main_홍창모 {

    public static void main(String[] args) {

        int[] numbers = {4, 1, 2, 1};
        int target = 4;

        System.out.println(solution(numbers, target));
    }

    public static int solution(int[] numbers, int target) {
        int idx = 0;
        int sum = 0;

        return dfs(numbers, target, idx, sum);
    }

    private static int dfs(int[] numbers, int target, int idx, int sum) {

        if( idx == numbers.length ) {
            if( sum == target ) {
                return 1;
            } else {
                return 0;
            }
        }

        return dfs(numbers, target, idx + 1, sum + numbers[idx])
                + dfs(numbers, target, idx + 1, sum - numbers[idx]);

    }

}
