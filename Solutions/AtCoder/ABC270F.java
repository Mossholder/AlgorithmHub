import java.util.*;

/**
 * @url: https://atcoder.jp/contests/abc270/tasks/abc270_f
 * @tag: 图论 | 最小生成树
 * @date: 2022-12-27
 */
class ABC270F {
	private long ans = Long.MAX_VALUE;

	public void solution(Kattio io) {
		int N = io.nextInt(), M = io.nextInt();
		int[][] e1 = new int[N][3], e2 = new int[N][3], e3 = new int[M][3];
		// 1. airport
		for(int i = 0; i < N; ++i)		e1[i] = new int[]{i + 1, N + 1, io.nextInt()};
		// 2. harbor
		for(int i = 0; i < N; ++i)		e2[i] = new int[]{i + 1, N + 2, io.nextInt()};
		// 3. road
		for(int i = 0; i < M; ++i)		e3[i] = new int[]{io.nextInt(), io.nextInt(), io.nextInt()};

		List<int[]> edges = new ArrayList<>();
		for(int[] e : e3)	edges.add(e);
		f(N, M, edges);

		for(int[] e : e1)	edges.add(e);
		f(N + 2, M, edges);

		for(int[] e : e2)	edges.add(e);
		f(N + 2, M, edges);

		edges.clear();
		for(int[] e : e3)	edges.add(e);
		for(int[] e : e2)	edges.add(e);
		f(N + 2, M, edges);
		io.println(ans);
	}

	private void f(int N, int M, List<int[]> edges) {
		int[] p = new int[N + 1];
		Arrays.setAll(p, i -> i);

		// Kruskal 最小生成树
		PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[2] - b[2]);
		for(int[] e : edges)	minHeap.offer(e);

		int cntEdge = 0;
		long sum = 0;
		while(!minHeap.isEmpty()) {
			int[] top = minHeap.poll();
			int from = top[0], to = top[1];
			int x = findRoot(p, from), y = findRoot(p, to);
			// union
			if(x != y) {
				cntEdge++;
				sum += top[2];
				p[x] = y;
			}
		}

		// 只有道路时可能无法将所有的岛屿连接起来
		if(edges.size() == M && cntEdge < N - 1)	return;
		ans = Math.min(ans, sum); 
	}

	private int findRoot(int[] p, int x) {
		while(p[x] != x) {
			p[x] = p[p[x]];
			x = p[x];
		}
		return x;
	}
}
