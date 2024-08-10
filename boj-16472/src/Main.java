import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String s = br.readLine();

		int[] alpha = new int[s.length()];

		for (int i = 0; i < s.length(); i++) {
			alpha[i] = s.charAt(i) - '0' - 48; //문자를 숫자로 변환 [97 - 48 (문자 0) - 48]
		}

		int[] arr = new int[27];

		int start = 0, end = 0;
		int max = 0;


		while (end < s.length()) { //end는 포함시키지 않는다.

			arr[alpha[end++]]++; // alpha[0]을 참조후 end 증가

			if (getCount(arr) > N) {
				arr[alpha[start++]]--; //start 이동 전 값 감소시켜준다. (순서가 바뀌면 증가시킨 값이 들어가므로 주의하자!)
				//start는 arr 연산 후에 증가된다.
				//조건문에서 max값을 계산한다면, start 증가 전의 값은 계산이 되지 않으므로 매 반복마다 max를 구해준다.
			}

			//매 반복시 값을 업데이트한다.
			//1-0,2-0,3-0,
			max = Math.max(max, end - start); //증가된 end 값 (포함 안되는 값) - start 값

		}

		System.out.println(max);
		br.close();

	}

	static int getCount(int[] arr) {
		int cnt = 0;

		for (int i : arr) {
			if (i != 0) {
				cnt++;
			}
		}

		return cnt;
	}

}