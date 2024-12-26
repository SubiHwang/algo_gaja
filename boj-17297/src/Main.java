import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

	static Map<Integer, Integer> map = new HashMap<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int M = Integer.parseInt(br.readLine());

		String s = "Messi Gimossi";

		//k=1 인 수열에서 M이 있을 경우
		if (M <= 5) {
			System.out.println(s.charAt(M - 1));
			return;
		} else if (M == 6) {
			System.out.println("Messi Messi Gimossi");
			return;
		} else if (M <= s.length()) {
			System.out.println(s.charAt(M - 1));
			return;
		}
		map.put(1, 5);

		int prepreLen = 5;
		int preLen = 13;
		int len = 0;
		int k = 2; //2항부터 계산 시작한다.
		map.put(2, 13);

		while (len < M) {
			len = preLen + 1 + prepreLen;
			prepreLen = preLen;
			preLen = len;
			k++;
			map.put(k, len);
		} //M번쨰 글자가 들어있는 가장 짧은 문자열의 길이

		char ans = dfs(M, k);
		System.out.println(ans == ' ' ? "Messi Messi Gimossi" : ans);

	}

	// k=2부터 가능
	private static char dfs(int M, int k) {

		if (k <= 2) { //2번째 수열까지 도달했을 경우에 13개 중 맞는 걸 리턴한다.
			String str = "Messi Gimossi";
			if (M <= 5) {
				return str.charAt(M - 1);
			} else if (M == 6) {
				return ' ';
			} else if (M <= str.length()) {
				return str.charAt(M - 1);
			}
		}

		char str = ' ';
		int preLen = map.get(k - 1);

		if (M <= preLen) { //이전 항의 길이와 같거나 작다면 전 항으로 이동한다.
			str = dfs(M, k - 1);
		} else if (M == preLen + 1) {
			str = ' '; //이전 항의 길이 + 1 과 같다면 공백이므로 바로 리턴해준다.
		} else if (M > preLen + 1) { //이전 항의 길이 + 1 보다 크다면 전전 항으로 이동한다.
			str = dfs(M - preLen - 1, k - 2);
		}

		return str;
	}

}