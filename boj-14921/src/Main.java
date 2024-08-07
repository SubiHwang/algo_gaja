import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[] oil = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			oil[i] = Integer.parseInt(st.nextToken());
		}

		int start = 0;
		int end = oil.length - 1;
		int min = Integer.MAX_VALUE;


		while (start < end) { //같은 수를 더할 수는 없다.

			int sum = oil[start] + oil[end];

			//sum 값이 0의 근사치면 근사치로 갱신!
			if (Math.abs(min) > Math.abs(sum)) {
				min = sum;
			}

			if (sum < 0) { //합이 0보다 작으므로 마이너스인 숫자가 더 작아야 한다.
				start++;
			} else if (sum > 0) { //합이 0보다 크므로 플러스인 숫자가 더 작아야 한다.
				end--;
			} else {
				break;
			}

		}


		System.out.println(min);

	}

}