import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken()); //최대 k번 삭제 가능
		boolean[] arr = new boolean[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			if (num % 2 == 0) {
				arr[i] = true;
			}
		}

		int start = 0, end = 0;
		int max = 0;
		int cnt = 0;

		while (end < N) {

			if (cnt < K) {
				if (!arr[end]) {
					cnt++;
				}
				end++;
				max = Math.max(max, end - start - cnt);

			} else if (arr[end]) {
				end++;
				max = Math.max(max, end - start - cnt);
			} else { //홀수인데 지울 수 없다
				if (!arr[start]) {
					cnt--;
				}
				start++;
			}

		}

		System.out.println(max);

	}
}