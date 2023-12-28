import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static char[][] strs;
    static long[] weights = new long['J'+1];
    static long[] pow10 = new long[12];
    static int[] values = new int['J'+1];

    public static void main(String[] args) throws Exception {
        // init: 10의 제곱수들을 저장해놓는다.
        pow10[0] = 1L;
        for (int i=1; i<12; i++) pow10[i] = pow10[i-1] * 10L;

        // N개의 수를 입력받아 각 문자열을 저장하고 각 자릿수에 나오는 횟수를 카운트한다.
        N = Integer.parseInt(rd.readLine());
        strs = new char[N][];
        for (int i=0; i<N; i++) {
            char[] s = rd.readLine().trim().toCharArray();
            strs[i] = s;
            for (int j=0; j<s.length; j++) {
                weights[s[s.length-1-j]] += pow10[j];
            }
        }

        // A~J를 12번째 자릿수부터 카운트 순서대로 정렬한다. 같으면 그 아래 자릿수를 대상으로 계속 비교
        // 정렬결과 chs[i]는 숫자 i를 나타내는 알파벳이 된다. (후처리 제외)
        Character[] chs = new Character[] {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
        Arrays.sort(chs, (a, b) -> weights[a] < weights[b] ? -1 : 1);

        // 만약 첫 자리가 chs[0]인 문자열은 0으로 시작하는 문자열이 있다는 뜻이므로
        // 어쩔 수 없이 chs[0]를 더 큰 자리 수와 바꿔야 한다.
        // 모든 문자열이 0으로 시작하지 않을 때까지 바꿀 자릿수를 늘리며 반복한다.
        boolean zeroLead = true;
        int idx = 1;
        while (zeroLead) {
            for (int i = 0; i < N; i++) {
                zeroLead = (strs[i][0] == chs[0]);
                if (zeroLead) {
                    Character tmp = chs[0];
                    chs[0] = chs[idx];
                    chs[idx] = tmp;
                    idx++;
                    break;
                }
            }
        }

        // 각 알파벳이 가지는 값, 즉 chs[i]의 역방향 참조
        for (int i=0; i<10; i++) {
            values[chs[i]] = i;
        }

        // 각 자릿수의 알파벳에 수를 대입해 합을 구해준다.
        long sum = 0;
        for (int i=0; i<N; i++) {
            int len = strs[i].length;
            for (int j=0; j<len; j++) {
                sum += values[strs[i][len-1-j]] * pow10[j];
            }
        }

        System.out.println(sum);
    }
}