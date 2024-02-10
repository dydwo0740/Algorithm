import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] v = new int[n];
        for (int i = 0; i < n; i++)
            v[i] = sc.nextInt();

        // Check 람다 함수 정의
        // mid 이상인 수의 연속적인 개수를 세는 함수
        // mid 이상인 수가 mid개 이상인 경우 true 반환
        // 아닌 경우 false 반환
        // Check 함수는 mid 이상인 수의 연속 개수를 세고,
        // 해당 개수가 mid와 같거나 클 경우 true를 반환하고,
        // 그렇지 않으면 false를 반환한다.
        Check check = (mid) -> {
            int t = 0;
            for (int i = 0; i < n; i++) {
                if (v[i] >= mid) t++;
                else t = 0;
                if (t >= mid) return true;
            }
            return false;
        };

        int lo = 1, hi = 1000000; // 1부터 1000000까지 범위 설정
        while (lo + 1 < hi) {
            int mid = (lo + hi) / 2; // 중간값 계산
            if (check.check(mid)) lo = mid;
            else hi = mid;
        }
        System.out.println(lo);
    }

    // Check 함수를 정의하는 인터페이스
    interface Check {
        boolean check(int mid);
    }
}
