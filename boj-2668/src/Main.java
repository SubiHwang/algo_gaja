import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

	static int N;

	static int[][] maps;
	static boolean[] check;
	static ArrayList<Integer> A = new ArrayList<>();

	static int ans = 0;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		maps = new int[2][N + 1];


		for (int j = 1; j <= N; j++) {
			maps[1][j] = Integer.parseInt(br.readLine());
		}


		check = new boolean[N + 1];

		for (int i = 1; i <= N; i++) {
			check[i] = true;
			dfs(i, i); //다음으로 알아볼 숫자, target(=시작점)
			check[i] = false;
		}

		Collections.sort(A);

		System.out.println(A.size());

		for (int a : A) {
			System.out.println(a);
		}

	}

	private static void dfs(int idx, int target) {

		if (maps[1][idx] == target) {
			A.add(target);
		}

		//사이클 무한루프를 없앤다.
		if (!check[maps[1][idx]]) {
			check[maps[1][idx]] = true;
			dfs(maps[1][idx], target);
			check[maps[1][idx]] = false;
		}

	}


}