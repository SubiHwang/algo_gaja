import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class City implements Comparable<City> {
	int to;
	int cost;

	City(int to, int cost) {
		this.to = to;
		this.cost = cost;
	}

	@Override
	public int compareTo(City o) {
		return this.cost - o.cost;
	}
}

public class Main {
	static int N, M, t;
	static ArrayList<City>[] A;
	static PriorityQueue<City> queue = new PriorityQueue<>();
	static int ans;
	static boolean[] check;

	//모든 도시를 정복하는데 사용되는 최소 비용
	//양방향 도로
	//1번 도시의 군주 박건은 모든 도시를 정복하고 싶다.
	//만약 특정 도시 B를 정복하고 싶다면, B와 도로로 연결된 도시들 중에서 적어도 하나를 정복하고 있어야 한다.
	//조건을 만족하는 도시 중에서 하나인 A를 선택하면, B를 정복하는 과정에서 A와 B를 연결하는 도로의 비용이 소모된다.
	//박건은 한번에 하나의 도시만 정복을 시도하고 언제나 성공한다.
	//한 번 도시가 정복되면, 모든 도시는 경계를 하게 되기 때문에 모든 도로의 비용이 t만큼 증가하게 된다
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //도시
		M = Integer.parseInt(st.nextToken()); //도로
		t = Integer.parseInt(st.nextToken()); //한 번 정복할 때마다 모든 도로의 비용이 t만큼 증가한다.

		A = new ArrayList[N + 1];
		check = new boolean[N + 1];

		for (int i = 1; i <= N; i++) {
			A[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			A[a].add(new City(b, c));
			A[b].add(new City(a, c));
		}

		MST();
		System.out.println(ans);
	}

	private static void MST() {

		queue.add(new City(1, 0)); //1번 정복했고 그 비용은 0에서 출발한다.

		int cnt = 0;
		while (!queue.isEmpty()) {
			City city = queue.poll();

			if (check[city.to]) continue;

			check[city.to] = true;
			ans += city.cost;
			cnt++;

			if (cnt > 2) { //1이 시작점이고 2번째 도시 정복 후 비용이 증가되므로 3번째 도시부터 비용이 추가된다.
				ans += t * (cnt - 2);
			}

			for (City c : A[city.to]) { //인접한 모든 인접 도시를 탐색한다.
				if (!check[c.to]) {
					queue.add(c);
				}
			}

		}
	}
}