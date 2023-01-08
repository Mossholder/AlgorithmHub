import java.util.*;

/**
 * @url: https://codeforces.com/problemset/problem/1733/D2
 * @tag: 数学 | 动态规划
 * @date: 2023-01-04
 */
class CF1733D2 {
	public void solution(Kattio io) {

		for(int T = io.nextInt(); T > 0; T--) {
			int n = io.nextInt(), x = io.nextInt(), y = io.nextInt();
			char[] s = io.next().toCharArray(), t = io.next().toCharArray();

			List<Integer> diff = new ArrayList<>(n);
			for(int i = 0; i < n; ++i)
				if(s[i] != t[i])	diff.add(i);

			int m = diff.size();
			// 特判：不一样的元素个数为奇数个 -> -1
			if(m % 2 == 1) 		io.println(-1);
			else if(m == 0 || y <= x) 
				if(m == 2 && diff.get(0) + 1 == diff.get(1))
					io.println(Math.min(x, y * 2));
				else 		
					io.println(y * 1L * m / 2);
			else {
				// y > x
				long pre1 = y, pre2 = 0;
				for(int i = 1; i < m; ++i) {
					int l = diff.get(i - 1), r = diff.get(i);
					long cur = Math.min(pre1 + y, pre2 + (r - l) * 1L * x * 2);
					pre2 = pre1;
					pre1 = cur;
				}
				io.println(pre1 >>> 1);
			}
		}	
	}
}