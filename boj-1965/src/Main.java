import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] coin;
	static int price;
	static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int test = Integer.parseInt(br.readLine());

		// 1원, 5원, 10원, 50원, 100원, 500원
		// 각 금액은 정수로서 1원부터 10000원까지 있을 수 있다.
		// N가지 동전으로 금액 M을 만드는 모든 방법의 수

		for (int t = 0; t < test; t++) {

			N = Integer.parseInt(br.readLine());

			st = new StringTokenizer(br.readLine());

			coin = new int[N + 1]; //가지고 있는 동전의 수

			for (int i = 1; i <= N; i++) {
				coin[i] = Integer.parseInt(st.nextToken());
			}

			price = Integer.parseInt(br.readLine()); //구하는 돈의 금액

			dp = new int[price + 1];
			dp[0] = 1;

			//dp[N] = 주어진 돈을 사용해 금액 N원을 만들 수 있는 모든 방법의 수
			//출력: dp[구하는 금액 = price]
			//dp[price] = 이전에 금액을 만들었다 치고 ... + 주어진 동전의 금액 coin[i] -> dp[j + coin[i]]
			for (int i = 1; i <= N; i++) { //가지고 있는 동전을 보면서
				for (int j = coin[i]; j <= price; j++) {
					//있는 동전이 (5,7) 이고 금액이 (10000)이면 금액은 (5부터,,,10000까지)
					//이전의 동전에서 만들어지는 경우의 수가 있다면
					//이전의 경우의 수에서 + 지금 동전으로 구한 방법의 수를 누적시킨다.
					dp[j] += dp[j - coin[i]];
				}
			}

			System.out.println(dp[price]);

		}
	}


}