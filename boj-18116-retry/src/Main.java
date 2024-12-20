import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] parents;
	static int[] size;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());

		parents = new int[1_000_001];
		size = new int[1_000_001];

		Arrays.fill(parents, -1);
		Arrays.fill(size, 1);

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String command = st.nextToken();

			if (command.equals("I")) {

				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				Union(a, b);

			} else {

				int num = Integer.parseInt(st.nextToken());
				int parentNum = parents[num];

				if (parentNum == -1) {
					System.out.println(1);
				} else {
					System.out.println(size[parentNum]);
				}

			}

		}

		for (int i = 1; i <= 4; i++) {
			System.out.println(parents[i]);
		}
	}

	private static boolean Union(int a, int b) {

		int aRoot = findRoot(a);
		int bRoot = findRoot(b);

		if (aRoot == bRoot) return false; //집합이라면 유니온 할 필요가 없다.

		parents[bRoot] = aRoot;
		size[aRoot] += size[bRoot];

		return true;

	}

	private static int findRoot(int a) {

		if (parents[a] < 0) {
			return a;
		}

		return parents[a] = findRoot(parents[a]);

	}

}