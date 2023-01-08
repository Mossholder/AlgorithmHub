import java.util.*;

/**
 * @url: https://codeforces.com/problemset/problem/1762/B
 * @tag: 构造题
 * @date: 2022-12-27
 */
public class CF1762B {
	public void solution(Kattio io) {
		int T = io.nextInt();
		while(T-- > 0) {
			int n = io.nextInt();
			int p = 0;
			List<long[]> operations = new ArrayList<>();

			// max(2^m, 2^n) % min(2^m, 2^n) == 0
			for(int i = 1; i <= n; ++i) {
				long cur = io.nextLong();
				if(Long.bitCount(cur) == 1)		continue;
				p++;
				operations.add(new long[]{i, (Long.highestOneBit(cur) << 1) - cur});
			}
			io.println(p);
			for(long[] op : operations)		io.println(op[0] + " " + op[1]);
		}
	}
}