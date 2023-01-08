import java.util.*;

/**
 * @url: https://codeforces.com/problemset/problem/1761/B
 * @tag: 贪心 | 构造题
 * @date: 2022-12-27
 */
public class CF1761B {
	public void solution(Kattio io) {
		int T = io.nextInt();
		while(T-- > 0) {
			int n = io.nextInt();
			Set<Integer> set = new HashSet<>();
			for(int i = 0; i < n; ++i)	set.add(io.nextInt());
			if(n <= 3)					io.println(n);
			else if(set.size() == 2)	io.println(n / 2 + 1);
			else  						io.println(n);
		}
	}
}