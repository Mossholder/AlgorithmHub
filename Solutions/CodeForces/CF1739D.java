import java.util.*;

/**
 * @url: https://codeforces.com/problemset/problem/1739/D
 * @tag: 二分 | 树形 dp
 * @date: 2023-01-06
 */
class CF1739D {
	private int cnt;

	public void solution(Kattio io) {
		for(int T = io.nextInt(); T > 0; T--) {
			int n = io.nextInt(), k = io.nextInt();
			// build tree
			List<Integer>[] tree = new ArrayList[n];
			Arrays.setAll(tree, i -> new ArrayList<>());
			for(int i = 1; i < n; ++i)		tree[io.nextInt() - 1].add(i);
			
			// 二分 | [low, high]
			int low = 1, high = n - 1;
			while(low <= high) {
				int mid = low + high >>> 1;
				cnt = 0;
				dfs(0, tree, false, mid);
				if(cnt > k)		low = mid + 1;
				else 			high = mid - 1;
			}
			io.println(low);
		}
	}

	// 树形 dp
	private int dfs(int v, List<Integer>[] tree, boolean cut, int mid) {
		int h = 0;
		for(int child : tree[v])	h = Math.max(h, dfs(child, tree, v > 0, mid));
		h++;
		if(cut && h == mid) {
			cnt++;
			return 0;
		}
		return h;
	}
}