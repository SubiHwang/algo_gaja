import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node> {
	int end;
	int dis;

	public Node(int end, int dis) {
		this.end = end;
		this.dis = dis;
	}

	@Override
	public int compareTo(Node e) {
		return this.dis - e.dis;
	}
}

public class Main {
	static int N, E;
	static ArrayList<Node>[] A;
	static int INF = 200000000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 정점의 개수
		E = Integer.parseInt(st.nextToken()); // 간선의 개수

		A = new ArrayList[N + 1];

		for (int i = 1; i <= N; i++) {
			A[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int dis = Integer.parseInt(st.nextToken());
			A[s].add(new Node(e, dis));
			A[e].add(new Node(s, dis));
		}

		st = new StringTokenizer(br.readLine());
		int startP = Integer.parseInt(st.nextToken());
		int endP = Integer.parseInt(st.nextToken());

		int distV1 = dijkstra(1, startP) + dijkstra(startP, endP) + dijkstra(endP, N);
		int distV2 = dijkstra(1, endP) + dijkstra(endP, startP) + dijkstra(startP, N);

		System.out.println(distV1 >= INF && distV2 >= INF ? -1 : Math.min(distV1, distV2));
	}

	private static int dijkstra(int start, int end) {
		int[] distance = new int[N + 1]; // 거리 배열 초기화
		boolean[] visited = new boolean[N + 1]; // 방문 배열 초기화
		PriorityQueue<Node> queue = new PriorityQueue<>();

		for (int i = 1; i <= N; i++) {
			distance[i] = INF; // 거리 초기화
		}

		distance[start] = 0;
		queue.offer(new Node(start, 0));

		while (!queue.isEmpty()) {
			Node node = queue.poll();
			int now = node.end;
			if (visited[now]) continue;
			visited[now] = true;

			for (Node n : A[now]) {
				if (!visited[n.end] && distance[n.end] > distance[now] + n.dis) {
					distance[n.end] = distance[now] + n.dis;
					queue.add(new Node(n.end, distance[n.end]));
				}
			}
		}

		return distance[end]; // 최단 거리 반환
	}
}
