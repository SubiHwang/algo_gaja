import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static final int left = 0, right = 1;
	static int N;
	static int[][] node;
	static int ans = 0;
	static Set<Integer> check = new HashSet<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		node = new int[2][N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			node[left][a] = b;
			node[right][a] = c;
			check.add(i);
		} //end of input

		dfs(1);
		System.out.println(ans);

		br.close();
	}

	private static void dfs(int idx) {

		if (node[left][idx] != -1) { //왼쪽 먼저 탐색
			ans++;
			dfs(node[left][idx]);
			if (!check.isEmpty()) ans++;
			//맨 마지막 노드가 아니라면 위로 이동할 때 한 번 더 계산해준다.
		}

		check.remove(idx); //방문한 리프 노드 처리
		//리프노드 즉, dfs(5)가 들어오면 왼쪽 -1이므로 위의 if문 실행안되고
		//아래 if문 실행안되고 얘만 실행된 후에 return 된다.

		if (node[right][idx] != -1) {
			ans++;
			dfs(node[right][idx]);
			if (!check.isEmpty()) ans++;
		}

	}

}