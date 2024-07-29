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

		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[] ran = new int[K];

		for (int i = 0; i < K; i++) {
			ran[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(ran);

		long right = ran[K - 1];
		long left = 1;
		long count, half;

		while (left <= right) {
			count = 0;
			half = (left + right) / 2;

			for (int i = 0; i < K; i++) count += ran[i] / half;

			if (count < N) right = half - 1;
			else left = half + 1;
		}

		System.out.println(right);
	}
}