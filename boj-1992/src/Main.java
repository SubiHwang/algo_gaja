import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static char[][] maps;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		maps = new char[N][N];

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				maps[i][j] = s.charAt(j);
			}
		}

		dfs(0, 0, N);


		System.out.println(sb);

	}

	private static void dfs(int row, int col, int size) {

		if (size == 1) {
			sb.append(maps[row][col]);
			return;
		}

		int len = size / 2;

		int zeroCnt = 0, oneCnt = 0;
		for (int i = row; i < size + row; i++) {
			for (int j = col; j < size + col; j++) {
				if (maps[i][j] == '0') {
					zeroCnt++;
				} else {
					oneCnt++;
				}
			}
		}

		if (zeroCnt == size * size) {
			sb.append('0');
		} else if (oneCnt == size * size) {
			sb.append('1');
		} else {
			sb.append('(');
			dfs(row, col, len);
			dfs(row, col + len, len);
			dfs(row + len, col, len);
			dfs(row + len, col + len, len);
			sb.append(')');
		}


	}

}