import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static ArrayList<Integer>[] A;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());

		A = new ArrayList[1_000_001];

		for (int i = 1; i <= 1000000; i++) {
			A[i] = new ArrayList<>();
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String command = st.nextToken();

			if (command.equals("I")) {

				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				A[a].add(b);
				A[b].add(a);
			} else {

				int num = Integer.parseInt(st.nextToken());
				int ans = findNum(num);
				System.out.println(ans);

			}

		}

	}

	private static int findNum(int num) {

		Queue<Integer> queue = new LinkedList<>();
		queue.add(num);
		boolean[] check = new boolean[1_000_001];
		int cnt = 0;

		while (!queue.isEmpty()) {

			int n = queue.poll();
			cnt++; //자기자신도 부품에 들어간다.
			check[n] = true;

			for (int nums : A[n]) {
				if (check[nums]) continue;

				queue.add(nums);
				check[nums] = true;
			}

		}

		return cnt;

	}
}