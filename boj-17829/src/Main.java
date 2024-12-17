import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] map;
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int ans = cut(0, 0, N);

		System.out.println(ans);


	}

	private static int cut(int row, int col, int size) {

		if (size == 1) { //계속 4등분으로 나누다가 1이되면 자기 자신을 리리턴
			return map[row][col];
			//0,0 시작
		}

		int half = size / 2;

		//현재 상황에서 4등분으로 나누기
		int a = cut(row, col, half); //map[0][0]
		int b = cut(row + half, col, half); //map[2][0]
		int c = cut(row, col + half, half); //map[0][2]
		int d = cut(row + half, col + half, half); //map[2][2]

		//사이즈가 1일 떄 map[0][0], map[2][0], map[0][2], map[2][2] 을 구한다.
		//그 후에 다시 사이즈 2로 합쳐질때는 4개의 부분 결과에서 각 부분의 영역 값을 반환받는다.
		//size = 2라면 4개의 size=1 결과를 가져온다.

		int[] nums = {a, b, c, d};
		Arrays.sort(nums);
		return nums[2];
	}

}