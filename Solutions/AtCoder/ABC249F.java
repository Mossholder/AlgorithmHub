import java.util.*;

/**
 * @url: https://atcoder.jp/contests/abc249/tasks/abc249_f
 * @tag: 反悔堆
 * @date: 2023-01-03
 */
class ABC249F {
	public void solution(Kattio io) {
		int n = io.nextInt(), k = io.nextInt();
		int[][] operations = new int[n + 1][2];
		for(int i = 1; i <= n; ++i) {
			operations[i][0] = io.nextInt();
			operations[i][1] = io.nextInt();
		} 

		long s = 0, ans = Long.MIN_VALUE;
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
		for(int i = n; i >= 0; --i) {
			int t = operations[i][0], y = operations[i][1];
			if(t != 2) {
				ans = Math.max(ans, y + s);
				if(k-- == 0)		break;
			} else if(y >= 0)		s += y;
			else 					maxHeap.offer(y);
			if(maxHeap.size() > k)	s += maxHeap.poll();
		}
		io.println(ans);
	}

	// 方法2 ： TLE
	public void dp() {
		Kattio io = new Kattio();
		int n = io.nextInt(), k = io.nextInt();
		int[][] operations = new int[n][2];
		for(int i = 0; i < n; ++i) {
			operations[i][0] = io.nextInt();
			operations[i][1] = io.nextInt();
		} 

		long[] dp = new long[k + 1];
		for(int[] o : operations) {
			int t = o[0], y = o[1];
			for(int j = k; j >= 0; --j) {
				dp[j] = t == 1 ? y : dp[j] + y;
				if(j > 0)	dp[j] = Math.max(dp[j], dp[j - 1]);
			}
		}
		long ans = Long.MIN_VALUE;
		for(long item : dp)		ans = Math.max(ans, item);
		io.println(ans);
	}
}

