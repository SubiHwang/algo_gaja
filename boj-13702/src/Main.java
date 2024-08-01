import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		long[] num = new long[N];

		for (int i = 0; i < N; i++) {
			num[i] = Long.parseLong(br.readLine());
		}

		Arrays.sort(num);

		long left = 1;
		long right = num[N - 1];

		while (left <= right) {
			long mid = (left + right) / 2;

			int mod = 0;
			for (int i = 0; i < N; i++) {
				mod += (num[i] / mid);
			}

			if (mod >= K) { //나눠줄 수 있는 개수가 친구의 수보다 크거나 같다면 작게 자른 걸 수도 있으므로 용량을 좀 더 크게해서 자른다.
				left = mid + 1;
			} else {
				right = mid - 1;
			}

		}

		System.out.println(right);

	}
}