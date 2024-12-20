import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		//초기 moo라는 문자열 길이는 3
		int len = 3;
		//처음 수열은 S(k) -> S(0)
		int k = 0;

		//처음을 0번부터 시작했다.
		while (len < N) { //총 문자열의 길이가 N이 될떄까지
			k++; //수열을 하나씩 증가시키고
			len = len * 2 + (3 + k); //전체 문자열의 길이는 이전 수열*2 한 것의 3+k과 같다.
		} //len이 N이상인 문자열의 총 길이를 구했다.


		char ans = dfs(N, k, len, 0);
		System.out.println(ans);

	}

	private static char dfs(int N, int k, int len, int start) {

		//이전 수열의 길이는 전체 길이에서 중간길이 뺀 값의 나누기 2
		int preLen = (len - (k + 3)) / 2;

		if (N <= preLen + start) { //N이 왼쪽에 있으면
			return dfs(N, k - 1, preLen, start);
		} else if (N <= start + preLen + k + 3) { //N이 중간에 있으면
			if (N == preLen + start + 1) return 'm';
			else return 'o';
		} else {
			return dfs(N, k - 1, preLen, start + preLen + k + 3);
		}


	}

}