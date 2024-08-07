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
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int coupon = Integer.parseInt(st.nextToken());

		int[] sushi = new int[N + k - 1];

		for (int i = 0; i < N; i++) {
			sushi[i] = Integer.parseInt(br.readLine());
		}

		for (int i = N; i < sushi.length; i++) {
			sushi[i] = sushi[i % N];//8번째에 0번, 9번째에 1
		}

		int start = 0;
		int end = start + k;
		int max = 0;

		while (end <= sushi.length - 1) {

			int[] window = new int[d + 1]; //인덱스는 초밥의 유형, 값은 해당 유형의 초밥 수
			window[coupon] = 1;

			for (int i = start; i < end; i++) {
				window[sushi[i]]++; //이 윈도우에 해당 유형의 초밥 수가 계산된다.
			}


			int cnt = 0;
			for (int i = 0; i < window.length; i++) {
				if (window[i] != 0) {
					cnt++;
				}
			}

			max = Math.max(max, cnt);

			start++;
			end++;
		}

		System.out.println(max);

	}
}