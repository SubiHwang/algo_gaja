import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int N;
	static String[] maps;
	static String ans1;
	static String ans2;


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		maps = new String[N];

		for (int i = 0; i < N; i++) {
			maps[i] = br.readLine();
		}

		ans1 = maps[0];
		ans2 = maps[1];

		int max = 0;
		for (int i = 0; i < N - 1; i++) {
			String from = maps[i];
			for (int j = i + 1; j < N; j++) {
				String to = maps[j];

				int minLen;
				if (from.length() > to.length()) minLen = to.length();
				else minLen = from.length();

				int cnt = 0;
				for (int k = 0; k < minLen; k++) {
					if (from.charAt(k) == to.charAt(k)) {
						cnt++;
					} else break;
				}

				if (cnt > max) { //개수가 더 많다면
					max = cnt;
					ans1 = from;
					ans2 = to; //문자열 바꿈
				}
			}
		}

		System.out.println(ans1);
		System.out.println(ans2);

	}


}