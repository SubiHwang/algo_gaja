import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int d;
	static long x, y;
	static String n;
	static int[] arr;
	static long size;
	static long startX, startY, endX, endY;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		d = Integer.parseInt(st.nextToken());
		n = st.nextToken();

		st = new StringTokenizer(br.readLine());
		x = Long.parseLong(st.nextToken());
		y = Long.parseLong(st.nextToken());


		size = 1L << d; //3이면 2의 3승만큼의 길이이다.

		arr = new int[d];
		for (int i = 0; i < d; i++) {
			arr[i] = n.charAt(i) - '0'; //위치의 자릿수 하나하나가 사분면이다.
		}


		//주어진 숫자로 해당 좌표 찾기
		findLoc(0, 0, 0, size);

		endX = startX - y;
		endY = startY + x;

		if (endX < 0 || endY < 0 || endX >= size || endY >= size) {
			System.out.println(-1);
		} else {
			//해당 좌표애 위치하는 숫자 찾기
			findLocNum(endX, endY, size);
			System.out.println(sb.toString());
		}


		br.close();

	}

	private static void findLocNum(long nowX, long nowY, long size) {

		if (size == 1) {
			return;
		}

		long preLen = size / 2;

		if (nowX < preLen && nowY < preLen) {
			sb.append(2);
			findLocNum(nowX, nowY, preLen);
		} else if (nowX >= preLen && nowY >= preLen) {
			sb.append(4);
			findLocNum(nowX - preLen, nowY - preLen, preLen);
		} else if (nowX < preLen && nowY >= preLen) {
			sb.append(1);
			findLocNum(nowX, nowY - preLen, preLen);
		} else if (nowX >= preLen && nowY < preLen) {
			sb.append(3);
			findLocNum(nowX - preLen, nowY, preLen);
		}

	}

	private static void findLoc(int idx, long row, long col, long size) {

		if (idx == d) {
			startX = row;
			startY = col;
			return;
		}

		int num = arr[idx];

		long preLen = size / 2;

		if (num == 1) {
			findLoc(idx + 1, row, col + preLen, preLen);

		} else if (num == 2) {
			findLoc(idx + 1, row, col, preLen);

		} else if (num == 3) {
			findLoc(idx + 1, row + preLen, col, preLen);

		} else if (num == 4) {
			findLoc(idx + 1, row + preLen, col + preLen, preLen);
		}


	}


}