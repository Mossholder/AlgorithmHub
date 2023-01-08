import java.util.*;

/**
 * @url: https://atcoder.jp/contests/abc267/tasks/abc267_f
 * @tag: 图论 | 树上问题 | 树的直径
 * @date: 2022-12-29
 */
class ABC267F {	
	public void solution(Kattio io) {
		int N = io.nextInt();
		// build tree
		List<Integer>[] tree = new ArrayList[N + 1];
		Arrays.setAll(tree, i -> new ArrayList<>());
		for(int i = 1; i < N; ++i) {
			int from = io.nextInt(), to = io.nextInt();
			tree[from].add(to);
			tree[to].add(from);
		}

		// collect queries
		int m = io.nextInt();
		int[] ans = new int[m];
		Arrays.fill(ans, -1);
		List<int[]>[] query = new ArrayList[N + 1];
		Arrays.setAll(query, i -> new ArrayList<>());
		for(int i = 0; i < m; ++i) {
			int node = io.nextInt(), k = io.nextInt();
			query[node].add(new int[]{i, k});
		}

		int[] d2n = new int[N];		// dis -> node
		// dfs
		Deque<int[]> queue = new ArrayDeque<>();
		for(int j = 0, rt = 1; j < 3; ++j) {
			int mx = -1;
			queue.offerLast(new int[]{rt, 0, 0});
			while(!queue.isEmpty()) {
				int[] top = queue.pollLast();
				int v = top[0], fa = top[1], d = top[2];
				d2n[d] = v;
				if(d > mx) {
					mx = d;
					rt = v;
				} 				
				for(int[] q : query[v]) {
					int i = q[0], k = q[1];
					if(k <= d)	ans[i] = d2n[d - k];
				}
				for(int to : tree[v])
					if(to != fa) 
						queue.offerLast(new int[]{to, v, d + 1});				
			}
		}

		for(int a : ans)	io.println(a);
	}
}