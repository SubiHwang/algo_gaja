import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] score;

	//10000c3의 결과값이 나올 수 있으므로 long으로 둔다.
	static long ans = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		score = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			score[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(score);

		//크기 6의 배열이라고 할 때  (0,1,2,3,4,5)
		//마지막 찾을 숫자의 인덱스를 이분 탐색으로 찾는다.
		for (int i = 0; i < N - 2; i++) { //고정 값은 0부터 3까지 가능하다. (6-3)
			for (int j = i + 1; j < N - 1; j++) { //다음 값은 1부터 4까지 가능하다.

				int sum = score[i] + score[j];

				//-2 0 2 2 2 2 이렇게 중복인 값이 여러개 있을 경우
				//lower = 2, upper = 5가 된다.
				int lower = lowerBound(j + 1, -sum); //시작점 값이 오고
				int upper = upperBound(j + 1, -sum); //끝점 값이 온다.

				ans += (upper - lower); //5-2한 것이 중복값의 개수가 된다.
				//값이 하나이고, 해당 값의 인덱스가 4라면 lower=4, upper=5 해서 1이 된다.
			}
		}

		System.out.println(ans);

	}

	private static int upperBound(int start, long target) {
		int end = N;

		while (start < end) {
			int middle = (start + end) / 2;

			//구하고자 하는 값이 5인데 5보다 크디먄
			if (score[middle] > target) {
				end = middle; //오른쪽 범위 다 버린다.
			} else {
				start = middle + 1;
			}
		}

		return start;
	}

	private static int lowerBound(int start, int target) {
		int end = N;

		while (start < end) {
			int middle = (start + end) / 2;

			//구하고자 하는 값이 5인데 5보다 크거나 같다면
			if (score[middle] >= target) {
				end = middle; //오른쪽 범위 다 버린다.
			} else {
				start = middle + 1;
			}
		}

		return start;
	}
}