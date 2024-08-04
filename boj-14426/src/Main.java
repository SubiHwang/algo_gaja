import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		String[] word = new String[N];

		for (int i = 0; i < N; i++) {
			word[i] = br.readLine();
		}

		Arrays.sort(word); //사전순으로 정렬

		int cnt = 0;
		for (int i = 0; i < M; i++) {
			String s = br.readLine();
			int left = 0;
			int right = word.length - 1;


			while (left <= right) {
				int mid = (left + right) / 2;

				if (word[mid].startsWith(s)) {
					cnt++;
					break;
				} else if (word[mid].compareTo(s) > 0) { //word[mid]가 s보다 사전순으로 뒤에 오는 문자열일 때
					//즉 banana(word) > apple(s)
					right = mid - 1;
				} else if (word[mid].compareTo(s) < 0) {
					left = mid + 1;
				}

			}

		}

		System.out.println(cnt);

	}
}