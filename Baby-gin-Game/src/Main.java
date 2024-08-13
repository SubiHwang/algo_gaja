import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	static int[] card = new int[6];
	static int[] visited = new int[6];
	static int[] map = new int[6];

	public static void main(String[] args) throws IOException {

		//0-9사이의 숫자 카드에서 임의의 카드 6장을 뽑았을 때
		//3장의 카드가 연속적인 번호를 가지면 run
		//3장의 카드가 동일한 번호를 갖는 경우 triplet
		//6장의 카드가 run과 triplet로만 구성된 경우를 baby-gin이라고 한다.
		//6자리 숫자를 입력 받아 baby-gin 여부 판별하는 프로그램을 작성하라.

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();

		for (int i = 0; i < 6; i++) {
			card[i] = Integer.parseInt(s.substring(i, i + 1));
		}

		dfs(0);
	}

	static private void dfs(int depth) {

		if (depth == 6) {
			System.out.println(Arrays.toString(map));
			return;
		}


		for (int i = 0; i < 6; i++) {
			if (visited[i] == 1) { //방문한 배열이라면 다음 인덱스를 탐색한다.
				continue;
			}
			map[depth] = card[i]; //새로운 조합의 배열의 생성한다.
			visited[i] = 1; //방문 체크
			dfs(depth + 1); //다음번에 뽑을 배열을 고른다.
			visited[i] = 0;                                                                                 //기저에서 다시 올라올 때 방문배열은 다시 복구시켜야 한다.
		}

	}

}