import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class School implements Comparable<School> {

	int to;
	int cost;

	School(int to, int cost) {
		this.to = to;
		this.cost = cost;
	}

	@Override
	public int compareTo(School o) {
		return this.cost - o.cost;
	}
}

public class Main {

	static int N, M, len;
	static ArrayList<School>[] A;
	static PriorityQueue<School> queue = new PriorityQueue<>();
	static String[] schoolSex;
	static boolean[] check;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		A = new ArrayList[N + 1];
		check = new boolean[N + 1];
		schoolSex = new String[N + 1];

		for (int i = 1; i <= N; i++) {
			A[i] = new ArrayList<>();
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			schoolSex[i] = st.nextToken();
		}


		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			if (!schoolSex[from].equals(schoolSex[to])) {
				A[from].add(new School(to, cost));
				A[to].add(new School(from, cost));
			}
		}

		if (getRoute() == -1) {
			System.out.println(-1);
		} else {
			System.out.println(len);
		}


	}

	private static int getRoute() { //가장 최단 비용 도출

		queue.add(new School(1, 0));

		while (!queue.isEmpty()) {
			School school = queue.poll();

			if (check[school.to]) continue;

			check[school.to] = true;
			len += school.cost;

			for (School s : A[school.to]) {
				if (!check[s.to]) {
					queue.add(new School(s.to, s.cost));
				}
			}

		}

		for (int i = 1; i <= N; i++) {
			if (!check[i]) {
				return -1;
			}
		}

		return 0;

	}

}