import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//시간 초과 코드임과 동시에 맞는지도 잘 모르겠다.
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken()); //최대 k번 삭제 가능
		int[] arr = new int[N];
		//boolean[] brr = new boolean[N];
		ArrayList<Integer> dp = new ArrayList<>();
		//int[] dp = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		if (arr[0] % 2 == 0) {
			dp.add(1);
		} else {
			dp.add(0);
		}

		for (int i = 1; i < N; i++) { //i번째 까지 중에서 가장 긴 짝수 연속한 부분 수열
			if (arr[i] % 2 == 0) {
				if (arr[i - 1] % 2 == 0) {//이전 값이 짝수라면 ( 연결되어 있다면 )
					dp.add(dp.get(i - 1) + 1);
					//dp[i] = dp[i - 1] + 1;
				} else { //이전 값이 홀수라면 ( 연결되어 있지 않다면 )
					dp.add(Math.max(dp.get(i - 1), 1));
					//dp[i] = Math.max(dp[i - 1], 1);
				}
			} else { //홀수라면 이전 값 그대로
				dp.add(dp.get(i - 1));
				//dp[i] = dp[i - 1];
			}
		}

//		for (int i : dp) {
//			System.out.println(i);
//		}

		int max = 0;
		int maxIndex = 0;

		for (int i = 0; i < dp.size(); i++) { //가장 큰 수가 들어간 인덱스의 시작점에서 뒤를 K보다 작거나 같게 지우고 max + 지운 수를 해준다.
			max = Math.max(dp.get(i), max);
			maxIndex = dp.indexOf(max);
		}

		int ans;

		if (dp.size() - maxIndex - 1 >= K) {
			ans = max + K;
		} else {
			ans = max + (dp.size() - maxIndex - 1);
		}

		System.out.println(ans);

	}
}