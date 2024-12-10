import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int orderSize;
	static int M, N;
	static int[] sizeOfA, sizeOfB;
	static int[] countOfA, countOfB;
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		orderSize = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		//원본 배열
		sizeOfA = new int[M];
		sizeOfB = new int[N];

		//크기 계산 배열 (각 크기에 해당하는 경우의 수 계산)
		countOfA = new int[2_000_001];
		countOfB = new int[2_000_001];

		int sum = 0;
		for (int i = 0; i < M; i++) {
			sizeOfA[i] = Integer.parseInt(br.readLine());
			sum += sizeOfA[i];
		}
		countOfA[sum] = 1;

		sum = 0;
		for (int i = 0; i < N; i++) {
			sizeOfB[i] = Integer.parseInt(br.readLine());
			sum += sizeOfB[i];
		}
		countOfB[sum] = 1;
		//endOfInput

		countOfA[0] = 1;
		countOfB[0] = 1;
		//사이즈 0을 만드는 것은 존재하지 않지만, 곱하기이므로 초깃값은 1로 세팅해준다.

		//사이즈별로 경우의 수를 어떻게 만들것인가..
		findSol(M, sizeOfA, countOfA);
		findSol(N, sizeOfB, countOfB);

		//System.out.println(countOfA[3]);
		//System.out.println(countOfB[3]);

		//사이즈가 7이면 (A의 경우의 수 * B의 경우의 수)
		for (int i = 0; i <= orderSize; i++) {
			ans += (countOfA[i] * countOfB[orderSize - i]);
		}

		System.out.println(ans);

	}

	private static void findSol(int n, int[] sizeOfP, int[] countOfP) {
		//모든 피자 조각을 선택하는 경우에 대해서는 고려하면 안되기 때문에 n+from에 -1 을 해준다.
		for (int from = 0; from < n; from++) { //각 시작점에 대해서
			int sum = 0;
			for (int idx = from; idx < n + from - 1; idx++) { //구간합을 구한다.
				//사이즈가 1인 경우
				//사이즈가 2인 경우를 판단해야 한다.
				sum += sizeOfP[idx % n];
				countOfP[sum]++;
			}
		}
	}

}