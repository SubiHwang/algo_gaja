import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, a, b;
	static Queue<Integer> queueA, queueB, buildings;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());

		queueA = new LinkedList<>();
		queueB = new LinkedList<>();
		buildings = new LinkedList<>();

		if (N < a + b - 1) {
			System.out.println(-1);
			return;
		}

		//8 6 3 -> 12345621

		if (a + b == N) {

			//10 3 7 인 경우
			if (a > b) {

				buildings.add(1);

				for (int i = 1; i <= a; i++) {
					queueA.add(i);
				}

				for (int i = b - 1; i >= 1; i--) {
					queueB.add(i);
				}

				updateBuildings();

			} else {

				buildings.add(1);

				for (int i = b; i >= 1; i--) {
					queueB.add(i);
				}

				for (int i = 1; i <= a - 1; i++) {
					queueA.add(i);
				}

				updateBuildings();

			}
		} else if (a + b - 1 == N) {

			if (a > b) {
				//10 4 7인 경우
				for (int i = 1; i <= a; i++) {
					queueA.add(i);
				}

				for (int i = b - 1; i >= 1; i--) {
					queueB.add(i);
				}

				updateBuildings();

			} else {

				for (int i = b; i >= 1; i--) {
					queueB.add(i);
				}

				for (int i = 1; i <= a - 1; i++) {
					queueA.add(i);
				}

				updateBuildings();

			}
		} else {
			if (a > b) {

				//빌딩을 다 만들고 나머지 앞을 1로 채운다.
				//10 6 2 인 경우 1234561 다 만들었으므로 앞에 1111을 채운다.

				for (int i = 0; i < N - a - b + 1; i++) {
					buildings.add(1);
				}

				for (int i = 1; i <= a; i++) {
					queueA.add(i);
				}

				for (int i = b - 1; i >= 1; i--) {
					queueB.add(i);
				}

				updateBuildings();

			} else {

				if (a == 1) {

					buildings.add(b);

					for (int i = 0; i < N - a - b + 1; i++) {
						buildings.add(1);
					}

					for (int i = b - 1; i >= 1; i--) {
						queueB.add(i);
					}

					for (int i = 1; i <= a - 1; i++) {
						queueA.add(i);
					}


				} else {

					for (int i = 0; i < N - a - b + 1; i++) {
						buildings.add(1);
					}

					for (int i = b; i >= 1; i--) {
						queueB.add(i);
					}

					for (int i = 1; i <= a - 1; i++) {
						queueA.add(i);
					}
				}


				updateBuildings();

			}
		}

		while (!buildings.isEmpty()) {
			System.out.print(buildings.poll() + " ");
		}

		br.close();
	}

	private static void updateBuildings() {

		while (!queueA.isEmpty()) {
			buildings.add(queueA.poll());
		}

		while (!queueB.isEmpty()) {
			buildings.add(queueB.poll());
		}
	}


}