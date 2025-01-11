import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] maps;

    //각 모양에 따라 중심에서 나머지 두개의 좌표를 drow, dcol로 각각 두었다.
    static int[][] fdir = {{0, -1}, {0, 1}, {-1, 0}, {-1, 0}};
    static int[][] tdir = {{1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static boolean[][] check;
    static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        maps = new int[N][M];
        check = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        findSol(0, 0);
        System.out.println(max);

    }

    private static void findSol(int depth, int sum) {

        if (depth == N * M) {
            max = Math.max(sum, max);
            return;
        }

        int mx = depth / M;
        int my = depth % M;

        if (!check[mx][my]) { //중심을 방문했으면

            for (int k = 0; k < 4; k++) {

                int fx = mx + fdir[k][0];
                int fy = my + fdir[k][1];
                int tx = mx + tdir[k][0];
                int ty = my + tdir[k][1];

                if (isPossible(fx, fy, tx, ty)) {
                    check[fx][fy] = true;
                    check[tx][ty] = true;
                    check[mx][my] = true;
                    findSol(depth + 1, sum + (maps[mx][my] * 2) + maps[fx][fy] + maps[tx][ty]);
                    check[fx][fy] = false;
                    check[tx][ty] = false;
                    check[mx][my] = false;
                }
            }
        }

        findSol(depth + 1, sum); //선택안하는 경우도 존재한다.


    }

    private static boolean isPossible(int fx, int fy, int tx, int ty) {

        if (fx < 0 || fx >= N || fy < 0 || fy >= M || check[fx][fy]) return false;
        if (tx < 0 || tx >= N || ty < 0 || ty >= M || check[tx][ty]) return false;

        return true;
    }


}