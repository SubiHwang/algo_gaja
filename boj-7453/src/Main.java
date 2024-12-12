import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		arr = new int[4][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				arr[j][i] = Integer.parseInt(st.nextToken());
			}
		}

		int[] ab = new int[N * N];
		int[] cd = new int[N * N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				ab[i * N + j] = arr[0][i] + arr[1][j];
				cd[i * N + j] = arr[2][i] + arr[3][j];
			}
		}

		Arrays.sort(ab);
		Arrays.sort(cd);

		int abP = 0;
		int cdP = N * N - 1;
		long ans = 0;

		while (abP < N * N && cdP >= 0) {

			long sum = ab[abP] + cd[cdP];
			int abNum = ab[abP];
			int cdNum = cd[cdP];

			if (sum == 0) {
				
				//똑같은 값이 몇 번 존재하는지 확인한다.
				long abCnt = 0;
				while (abP < N * N && ab[abP] == abNum) {
					abP++;
					abCnt++;
				}

				long cdCnt = 0;
				while (cdP >= 0 && cd[cdP] == cdNum) {
					cdP--;
					cdCnt++;
				}

				ans += abCnt * cdCnt;
			} else if (sum < 0) {
				abP++;
			} else {
				cdP--;
			}

		}


		System.out.println(ans);

	}

}