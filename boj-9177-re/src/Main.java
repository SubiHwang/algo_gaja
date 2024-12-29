import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static boolean[] checkFir;
	static boolean[] checkThr;
	static boolean[] checkSec;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		for (int t = 1; t <= N; t++) {

			st = new StringTokenizer(br.readLine());
			char[] fir = st.nextToken().toCharArray();
			char[] sec = st.nextToken().toCharArray();
			char[] thr = st.nextToken().toCharArray();

			checkFir = new boolean[fir.length];
			checkThr = new boolean[thr.length];
			checkSec = new boolean[sec.length];

			int firCnt = 0;
			for (int i = 0; i < thr.length; i++) {
				if (firCnt >= fir.length) break;
				if (checkThr[i]) continue;
				if (thr[i] == fir[firCnt]) {
					checkThr[i] = true;
					firCnt++;
				}
			}


			int secCnt = 0;
			for (int i = 0; i < thr.length; i++) {
				if (secCnt >= sec.length) break;
				if (checkThr[i]) continue;
				if (thr[i] == sec[secCnt]) {
					checkThr[i] = true;
					secCnt++;
				}
			}

			if (firCnt == fir.length && secCnt == sec.length) {
				System.out.println("Data set " + t + ": yes");
			} else {
				System.out.println("Data set " + t + ": no");
			}


		}

	}


}