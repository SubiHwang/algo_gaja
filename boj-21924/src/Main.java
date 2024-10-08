import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Building implements Comparable<Building> {

	int to;
	int cost;

	Building(int to, int cost) {
		this.to = to;
		this.cost = cost;
	}

	@Override
	public int compareTo(Building o) {
		return this.cost - o.cost;
	}
}

public class Main {

	static int buildingNum, roadNum;
	static ArrayList<Building>[] A;
	static PriorityQueue<Building> queue = new PriorityQueue<>();
	static long totalCost, installCost;
	static boolean[] check;

	public static void main(String[] args) throws IOException {
		//전체 비용 - 모든 곳을 연결하는 비용 = 절약 비용
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		buildingNum = Integer.parseInt(st.nextToken());
		roadNum = Integer.parseInt(st.nextToken());

		A = new ArrayList[buildingNum + 1];
		check = new boolean[buildingNum + 1];

		for (int i = 1; i <= buildingNum; i++) {
			A[i] = new ArrayList<>();
		}

		for (int i = 0; i < roadNum; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			A[from].add(new Building(to, cost));
			A[to].add(new Building(from, cost));
			totalCost += cost;
		}


		if (getRoute() == -1) {
			System.out.println(-1);
		} else {
			long saveCost = totalCost - installCost;
			System.out.println(saveCost);
		}


	}

	private static int getRoute() { //가장 최단 비용 도출

		queue.add(new Building(1, 0));

		while (!queue.isEmpty()) {
			Building building = queue.poll();

			if (check[building.to]) continue;

			check[building.to] = true;
			//System.out.println("방문하는 도시 이름: " + building.to);
			//System.out.println("비용: " + building.cost);
			installCost += building.cost;

			for (Building b : A[building.to]) {
				if (!check[b.to]) {
					queue.add(new Building(b.to, b.cost));
				}
			}

		}

		for (int i = 1; i <= buildingNum; i++) {
			if (!check[i]) {
				return -1;
			}
		}

		return 0;

	}

}