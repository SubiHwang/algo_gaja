import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		long test = Long.parseLong(br.readLine());

		for (int t = 0; t < test; t++) {
			st = new StringTokenizer(br.readLine());

			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			long[] A = new long[N];
			long[] B = new long[M];

			st = new StringTokenizer(br.readLine());

			for (int i = 0; i < N; i++) {
				A[i] = Long.parseLong(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				B[i] = Long.parseLong(st.nextToken());
			}

			Arrays.sort(A);

			int cnt = 0;

			for (int i = 0; i < M; i++) {
				int result = N;
				long num = B[i];
				int left = 0;
				int right = N - 1;

				while (left <= right) { // while문을 탈출한다면 큰 숫자가 없는 것으므로 result = N으로 해줘야 한다.
					int mid = (left + right) / 2;

					if (num >= A[mid]) {
						left = mid + 1;
					} else { //A의 값이 num 보다 크다면
						right = mid - 1;
						result = mid; //해당 인덱스를 반환한다.
					}

				}

				cnt += (N - result);

			}

			System.out.println(cnt);

		}

	}
}