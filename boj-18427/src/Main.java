import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M, H;
	static int[][] maps;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		maps = new int[N + 1][M + 1];
		dp = new int[N + 1][H + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; st.hasMoreTokens(); j++) {
				maps[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		//dp[0][0] 을 0으로 해주는 이유는 높이2를 구하고 있고, 1번 학생이 높이를 구할 떄 이전의 값 0 +
		//높이가 2보다 작거나 같은 것들 중 즉, 1번 학생이 가진 블록 2를 경우의 수에 추가할 때
		//dp[0][2-2] 를 사용하는데, 이때 dp[0][0]= 1이 되어야 0+1=1이 될 수 있다.
		//정리하자면, 현재 나의 블록으로 높이를 만들 수 있을 경우에는 dp[이전학생][0]인 경우의 수가 추가되는데. 아때 1이 필요하기 때문이다.
		for (int i = 0; i <= N; i++) {
			dp[i][0] = 1;
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= H; j++) {
				//이전 학생들까지 구한 높이 H가 되는 경우의 수에서 나라는 학생이 포함되었을 경우의 경우의 수를 구하면 된다.
				//3번 학생이 1,2,3 일 때 이전 학생의 H-1일때 dp를 구하면 된다.
				dp[i][j] = dp[i - 1][j];
				//밑의 반복문으로 가면 이전의 경우의 수를 더해줘야 하는데, 나의 높이가 구하려는 높이보다 크다면 아예
				//계산되지 않는다.
				for (int k = 1; k <= M; k++) {
					if (maps[i][k] == 0 || maps[i][k] > j) continue;//나의 기준에서 0번인 블록 은 계산하지 않는다.
					//0번인 블록을 선택하면 이전 학생까지 구했던 경우의 수와 같으므로
					//현재 나의 블록의 높이가 더 큰 경우에도 계산해주면 안된다.
					dp[i][j] = (dp[i][j] + dp[i - 1][j - maps[i][k]]) % 10007;
				}
			}
		}

		System.out.println(dp[N][H]);

	}


}