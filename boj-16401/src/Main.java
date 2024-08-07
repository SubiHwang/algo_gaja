import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static long[] cookie;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken()); // 조카 수
		int N = Integer.parseInt(st.nextToken()); // 과자 수

		cookie = new long[N];

		st = new StringTokenizer(br.readLine());
		long sum = 0;

		for (int i = 0; i < N; i++) {
			cookie[i] = Long.parseLong(st.nextToken());
			sum += cookie[i];
		}

		if (sum < M) {
			System.out.println(0);
		} else if (sum == M) {
			System.out.println(1);
		} else {
			Arrays.sort(cookie); // 과자 길이를 정렬
			long right = cookie[N - 1]; // 최대 과자 길이
			long left = 1; // 최소 길이
			long max = 0; // 최대 과자 길이

			while (left <= right) {
				long mid = (left + right) / 2; // 중간 값
				long cnt = 0; // 나눌 수 있는 과자의 개수

				for (int i = 0; i < N; i++) {
					cnt += (cookie[i] / mid); // 나올 수 있는 과자 덩어리의 합
				}

				if (cnt >= M) {
					max = Math.max(max, mid); // 최대 길이 업데이트
					left = mid + 1; // 큰 덩어리로 자르기
				} else {
					right = mid - 1; // 작은 덩어리로 자르기
				}
			}

			System.out.println(max);
		}
	}
}
