import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static int[] board;
	static int cnt = 0;

	public static void main(String[] args) throws IOException {
		//Queen 문제
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		board = new int[N];

		getQueensCount(0);
		System.out.println(cnt);
	}

	static void getQueensCount(int row) {
		//퀸은 같은 행, 같은 열, 대각선으로 움직일 수 있다.

		if (row == N) { //행을 끝까지 다 돌면
			cnt++;
			return;
		}

		for (int col = 0; col < N; col++) { //현재 행의 열을 탐색
			boolean isP = true;

			//놓을 수 없는 경우
			//prow: 이전까지의 행
			for (int prow = 0; prow < row; prow++) { //현재 탐색 위치와 이전 행을 비교

				//이전의 행의 열과 현재 행의 열이 같거나 || 이전의 행에 놓여진 퀸의 대각선 방향과 같다면
				//대각선 탐색 : 이전 행의 열에서 현재 행의 열의 좌표를 뺀 것의 절대값 == 이전 행의 값과 현재 행의 좌표를 뺀 것의 절대값
				if (board[prow] == col || Math.abs(board[prow] - col) == Math.abs(prow - row)) {
					isP = false;  //놓을 수 없음
					break;
				}
			}

			//놓을 수 있는 경우
			if (isP) {
				board[row] = col;
				getQueensCount(row + 1);
			}
		}
	}
}
