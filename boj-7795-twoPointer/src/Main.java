import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int Test = Integer.parseInt(br.readLine());

		for (int t = 0; t < Test; t++) {
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

			int ans = 0;

			//투 포인터 탐색 (B보다 큰 A의 개수를 구한다.)
			for (int j = 0; j < M; j++) {

				long num = B[j];
				int start = 0;//start,end를 초기화하는 곳을 잘 알아두자!!

				while (start <= A.length - 1) { //각 B에 대해서 A를 탐색한다.
					if (A[start] > num) {
						break;
					}
					start++;
				}

				ans += (N - start);
			}

			System.out.println(ans);

		}

	}
}