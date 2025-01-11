import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] agrs) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		int sum = 0;
		int max = Integer.MIN_VALUE;
		int K = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		st.nextToken(); //방향은 육각형이 될 것이므로 신경쓰지 않아도 된다.

		int first = Integer.parseInt(st.nextToken()); //50
		int pre = first;

		for (int i = 1; i < 6; i++) {
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			int cur = Integer.parseInt(st.nextToken()); //변의 길이
			max = Math.max(cur * pre, max); //같은 방향의
			sum += cur * pre;
			pre = cur;
		}
		max = Math.max(pre * first, max);
		sum += pre * first;
		System.out.println((sum - max * 2) );
	}
}
