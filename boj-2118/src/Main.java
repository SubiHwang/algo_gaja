import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		//N+1임에 주의하자!!
		int[] town = new int[N + 1];
		int total = 0;

		for (int i = 0; i < N; i++) {
			town[i] = Integer.parseInt(br.readLine());
			total += town[i];
		}

		int start = 0;
		int end = 0;

		int max = 0;

		int now = town[start];

		//나의 기준은 시계방향이다.
		while (start <= end && end < N) {

			int minNow = Math.min(now, total - now); //시계 방향 거리와 반시계 방향 거리중 최소 값을 고른다.

			max = Math.max(max, minNow);//max 에 최소값을 갱신시킨다.

			if (now == minNow) {//현재 시계방향 거리값과 최소인 거리가 같다면 한 칸 더 이동시켜보자.
				end++;//오른쪽 지점을 한 칸 이동
				now += town[end]; //이전 시계방향 값에서 한 칸 이동한 값을 더한다. (1번 - 2번 지점 = now) + (2번 - 3번 지점)
			} else { //반시계방향이 최소값이 된다면 1-2번 지점에서의 최대값은 더 이상 없으므로 왼쪽 지점을 한 칸 이동
				now -= town[start];
				start++;
			}

		}

		System.out.println(max);

	}
}
