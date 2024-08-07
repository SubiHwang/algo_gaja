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
			sushi[i] = sushi[i % N]; // 초밥 배열을 원형으로 만든다.
		}

		int[] window = new int[d + 1]; // 각 초밥 유형의 수

		int cnt = 0;

		// 초기 윈도우 설정
		for (int i = 0; i < k; i++) {
			if (window[sushi[i]] == 0) {
				cnt++; //유형 수를 증가시킨후에 window에 넣어준다.
			}
			window[sushi[i]]++;
		}

		if (window[coupon] == 0) {
			cnt++;
		}
		window[coupon]++;
		//초기 설정 end

		int max = cnt; // 초기 최대값 설정

		// 슬라이딩 윈도우
		int start = 1, end = 1;

		while (end < sushi.length) {

			end = start + k;

			// 윈도우에서 나가는 초밥
			int out = sushi[start - 1];
			window[out]--;
			if (window[out] == 0) { // 나가는 초밥으로 인해 초밥의 수가 0이 되는 유형이 있다면
				cnt--; // 유형 수를 감소시킨다.
			}

			// 윈도우로 들어오는 초밥
			int in = sushi[end - 1]; //end 까지 배열로 들어온다.
			if (window[in] == 0) {
				cnt++;
			}
			window[in]++;

			max = Math.max(max, cnt);

			start++;
		}

		System.out.println(max);
	}
}
