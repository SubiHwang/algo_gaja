import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static ArrayList<Integer> A[];
	static boolean[] visited;
	static int[] answer;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		//BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		A = new ArrayList[N + 1];
		answer = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			A[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			A[end].add(start);
		}

		for (int i = 1; i <= N; i++) {
			visited = new boolean[N + 1];
			bfs(i);
		}

		int max = 0;
		for (int n : answer) {
			max = Math.max(max, n);
		}


		for (int i = 1; i <= N; i++) {
			if (answer[i] == max) {
				sb.append(i + " ");
			}
		}

		System.out.println(sb);

		br.close();
		//bw.flush();
		//bw.close();

	}

	private static void bfs(int num) {

		Queue<Integer> queue = new LinkedList<>();
		queue.add(num);
		visited[num] = true;

		while (!queue.isEmpty()) {
			int now = queue.poll();
			for (int n : A[now]) {
				if (!visited[n]) {
					visited[n] = true;
					answer[num]++;
					queue.add(n);
				}
			}
		}

	}


}