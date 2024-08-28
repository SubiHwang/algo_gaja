import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Island implements Comparable<Island> {
	int to;
	int weight;

	Island(int to, int weight) {
		this.to = to;
		this.weight = weight;
	}

	@Override
	public int compareTo(Island o) {
		return o.weight - this.weight; // 무게가 큰 순서로 정렬
	}
}

public class Main {
	static int N, M, s, e;
	static ArrayList<Island>[] A;
	static PriorityQueue<Island> queue = new PriorityQueue<>();
	static int[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		s = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());

		A = new ArrayList[N + 1];
		visited = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			A[i] = new ArrayList<>();
			visited[i] = Integer.MIN_VALUE; // 초기값을 매우 작은 값으로 설정
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			A[from].add(new Island(to, weight));
			A[to].add(new Island(from, weight));
		}

		int max = getRoute();
		System.out.println(max);
	}

	private static int getRoute() {
		queue.offer(new Island(s, Integer.MAX_VALUE)); // 시작 노드의 무게는 무한대로 설정
		visited[s] = Integer.MAX_VALUE;

		int max = 0;

		while (!queue.isEmpty()) {
			Island current = queue.poll();
			if (current.to == e) {
				max = current.weight; // 도착 노드에 도달하면 최대 무게 업데이트
				break;
			}

			if (visited[current.to] > current.weight) continue; // 무게가 더 작으면 스킵

			for (Island neighbor : A[current.to]) {
				int newWeight = Math.min(current.weight, neighbor.weight);
				if (visited[neighbor.to] < newWeight) {
					visited[neighbor.to] = newWeight;
					queue.offer(new Island(neighbor.to, newWeight));
				}
			}
		}

		return max; // 최대 금빼빼로 수 반환
	}
}
