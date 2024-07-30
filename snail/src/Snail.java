public class Snail {

	static int[] dx = {0, 1, 0, -1}; //좌하우상
	static int[] dy = {-1, 0, 1, 0};
	static int[][] map = new int[5][5];

	public static void main(String[] args) {

		int cnt = 25;

		int x = 0, y = 4;
		int dir = 0; //방향 변수


		while (cnt >= 1) {

			//현재 좌표부터 넣어준다.
			map[x][y] = cnt--;

			//NowX,nowY는 판단하는 변수이지 현재 좌표가 여기서는 아니다.
			int nowX = x + dx[dir];
			int nowY = y + dy[dir];

			//다음 좌표가 유효한지 체크하고 유효하지 않다면 nowX,nowY를 유효하게 바꾸고 x,y에 넣어준다.
			if (nowX < 0 || nowY < 0 || nowX >= 5 || nowY >= 5 || map[nowX][nowY] != 0) { //벽을 만나거나 지도의 숫자가 0이 아닌 다른 수로 채워져있을 경우
				dir = (dir + 1) % 4;//0,1,2,3 순서로 바꾼다.
				//nowX는 기존 좌표에서 다음 좌표의 이동 좌표를 판단하는 기준이 되는 좌표이다.
				//x,y가 0이 되면 nowX=0,nowY=-1 상태로 되어있다.
				//nowY가 -1이므로 if 문으로 들어오게 되고 dir = 1로 바뀐다.
				//그 이후에 좌표값의 아무런 변동없이 if문을 탈출하면 cnt--; 와 map[nowX][nowY]가 실행되므로
				//map[0][-1] 이 나와 배열 인덱스 범위를 벗어나게 된다.
				//따라서 nowX와 nowY를 (0,-1)에서 값을 변경시켜야 한다.
				//기존 값 (0,0)에서 방향을 바꾼 좌표를 nowX,nowY에 넣어준다.
				//(0+1,0) 해서 아래로 내려가는 좌표가 대입된다.
				nowX = x + dx[dir]; //1
				nowY = y + dy[dir]; //0
			}

			x = nowX; //위에보면 x에 방향을 더하고 있다. 따라서 x에 이동할 때마다 좌표값을 업데이트 해주어야 한다.
			y = nowY;

		}


		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				System.out.printf("%5d", map[i][j]);
			}
			System.out.println();
		}

	}
}