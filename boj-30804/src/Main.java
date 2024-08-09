import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());

		int[] candy = new int[N];
		int[] type = new int[10]; //1-9 까지 배열
		for (int i = 0; i < N; i++) {
			candy[i] = Integer.parseInt(st.nextToken());
		}

		int start = 0, end = 0;
		int max = 0; //제공된 모든 과일 개수

		while (end < N) {

			type[candy[end++]]++;

			if (count(type) > 2) {
				type[candy[start++]]--;
			}

			max = Math.max(max, end - start);

		}

		System.out.println(max);

	}

	private static int count(int[] type) {
		int cnt = 0;
		for (int i = 0; i < 10; i++) {
			if (type[i] != 0) {
				cnt++;
			}
		}
		return cnt;
	}
}