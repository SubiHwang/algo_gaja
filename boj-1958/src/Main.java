import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static String A, B, C;
	static int[][][] maps;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		//문자열 3개의 LCS
		A = br.readLine();
		B = br.readLine();
		C = br.readLine();

		maps = new int[A.length() + 1][B.length() + 1][C.length() + 1];


		for (int i = 1; i <= A.length(); i++) {
			for (int j = 1; j <= B.length(); j++) {
				for (int k = 1; k <= C.length(); k++) {
					//A.charAt(i - 1) == B.charAt(j - 1) == C.charAt(k - 1) 이렇게 3개 비교안된다.
					//왼쪽에서부터 연산이 실행되었을 떄 A.charAt(i - 1) == B.charAt(j - 1) 하면 boolean 값이 나오므로
					//boolean == char은 오류가 난다. 따라서 2개씩 비교해야 한다.
					if (A.charAt(i - 1) == B.charAt(j - 1) && B.charAt(j - 1) == C.charAt(k - 1)) {
						maps[i][j][k] = maps[i - 1][j - 1][k - 1] + 1;
					} else {
						//문자 C에서 i가 탐색할 차례일 때!
						//문자 A: abc, 문자 B: def, 문자 C:ghi
						// A에서 문자열을 하나 제외한 경우 -> ab까지, B와 C는 끝까지
						// K가 바로 이전 단계에서 만든 LCS (B에서 문자열을 하나 제외한 경우) -> def에서 de까지, A와 C는 끝까지
						// K의 바로 이전 문자가 만든 LCS (K에서 문자열 하나 제외한 경우) -> ghi에서 h까지, A와 B는 끝까지
						maps[i][j][k] = Math.max(maps[i - 1][j][k], Math.max(maps[i][j - 1][k], maps[i][j][k - 1]));
					}
				}
			}
		}


		System.out.println(maps[A.length()][B.length()][C.length()]);
	}
}