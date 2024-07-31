import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static long[] cookie;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken()); //조카
		int N = Integer.parseInt(st.nextToken()); //과자
		//조카,과자,과자 길이 모두 1이상이다.

		cookie = new long[N];

		st = new StringTokenizer(br.readLine());

		long sum = 0;

		for (int i = 0; i < N; i++) {
			cookie[i] = Long.parseLong(st.nextToken());
			sum += cookie[i];
		}

		//long min = Long.MAX_VALUE; //막대과자의 최대 길이

		if (sum < M) { //막대과자 길이의 합이 조카의 수보다 작으면 0을 출력한다.
			System.out.println(0);
		} else if (sum == M) { //막대과자 길이의 합이 조카의 수와 같으면 1을 출력한다.
			System.out.println(1);
		} else { //막대과자 길이의 합이 조카의 수보다 크고 조카의 수보다 크면 이분 탐색을 시작한다.

			long right = cookie[N - 1]; //과자의 최대 길이
			long left = 1; //길이는 1부터 시작한다.
			long max = 0; // 최대 과자 길이

			//right를 cookie의 최대 길이로 준 이유는 나머지 쿠키길이가 최대 길이에 포함될 수 있기 때문이다.
			//right를 짧은 쿠키 갯수로 준다면 최대 길이는 포함될 수 없다.
			while (left <= right) { //right를 출력하므로 right=1이 될 때까지 반복해줘야 한다.
				long mid = (left + right) / 2; //중간 값
				//여기서 자르는 mid는 배열의 인덱스가 아니라 과자의 길이다.
				//즉 주어지지 않은 과자의 길이가 중간값이 될 수 있다.
				long cnt = 0; //나눌 수 있는 과자의 개수

				for (int i = 0; i < N; i++) {
					//right가 계속 바뀌는 경우에 left = 1, right = 1일때까지만 반복된다.
					//mid의 최소값은 1이 된다. (1+1)/2 = 1이다.
					cnt += (cookie[i] / mid); //나올 수 있는 과자 덩어리의 합
				}

				if (cnt >= M) { //나온 과자가 조카의 수와 같거나 크면 조금 큰 덩어리로 잘라본다.
					max = Math.max(max, mid); // 최대 길이 업데이트
					left = mid + 1;
				} else { //나온 과자가 조카의 수보다 작으면 조금 더 작은 덩어리로 잘라본다.
					right = mid - 1;
				}

				//min = Math.min(min, right); //이분탐색은 가장 근접한 수를 찾는 것이므로 큰 길이에서 올 때는 min을 구해야 한다.
				//이렇게 해줄 필요 없이 이 문제에서는 가장 최대의 과자 길이 값을 찾는다!!
				//아 아니다 이거 아니다. 다시..

				//3 1
				//1 1 15 15 15
				//위의 경우에는 15가 답이므로 mid가 아니라 right를 출력해야 한다.

			} //while end

			System.out.println(right);

		}

	}
}