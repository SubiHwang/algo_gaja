import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int customerSize, M, N;
	static int[] A, B;
	static int[] countOfA, countOfB;
	static int ans = 0;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		customerSize = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		A = new int[M];
		B = new int[N];

		countOfA = new int[2_000_000];
		countOfB = new int[2_000_000];

		int sum = 0;
		for (int i = 0; i < M; i++) {
			A[i] = Integer.parseInt(br.readLine());
			sum += A[i];
		}

		countOfA[sum] = 1;
		countOfA[0] = 1;

		sum = 0;
		for (int i = 0; i < N; i++) {
			B[i] = Integer.parseInt(br.readLine());
			sum += B[i];
		}

		countOfB[sum] = 1;
		countOfB[0] = 1;


		//A 계산
		//전체집합, 공집합 제외!!
		for (int from = 0; from < M; from++) { //시작할 피자 인덱스 번호
			sum = 0;
			for (int j = from; j < from + M - 1; j++) {
				//시작할 피자 인덱스 번호부터 한 바퀴 돌아서 원점 위치의 앞까지
				//즉, 시작 피자 인덱스가 3이고 피자 조각이 5조각이라면
				//3,4,0,1,2번 인덱스의 피자까지 부분합을 구해야 한다.
				//이떄 3-7까지 구해야 % M 가능
				sum += A[j % M]; //3,4,0,1,2
				countOfA[sum]++;
			}
		}

		//B 계산
		for (int from = 0; from < N; from++) {
			sum = 0;
			for (int j = from; j < from + N - 1; j++) {
				sum += B[j % N];
				countOfB[sum]++;
			}
		}

		//사이즈 7을 만드는 경우의 수 = 0*7인 경우의 수
		for (int i = 0; i <= customerSize; i++) {
			ans += countOfA[i] * countOfB[customerSize - i];
		}

		System.out.println(ans);

	}


}