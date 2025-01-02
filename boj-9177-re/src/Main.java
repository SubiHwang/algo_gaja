import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[][] maps;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		for (int t = 1; t <= N; t++) {

			st = new StringTokenizer(br.readLine());
			char[] fir = st.nextToken().toCharArray();
			char[] sec = st.nextToken().toCharArray();
			char[] thr = st.nextToken().toCharArray();

			maps = new int[200][200];


		}

	}


}