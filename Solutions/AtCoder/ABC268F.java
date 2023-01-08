import java.util.*;
import java.util.stream.IntStream;

/**
 * @url: https://atcoder.jp/contests/abc268/tasks/abc268_f
 * @tag: 邻项交换法
 * @date: 2022-12-28
 */
class ABC268F {
	public void solution(Kattio io) {
		int N = io.nextInt();
		String[] strs = new String[N];
		Arrays.setAll(strs, i -> io.next());

		long ans = 0;
		int[] cntX = new int[N], cntScore = new int[N];
		for(int i = 0; i < N; ++i) 
			for(char c : strs[i].toCharArray())
				if(c == 'X')		cntX[i]++;
				else {
					ans += cntX[i] * (c - '0');
					cntScore[i] += c - '0';
				}

		Integer[] idx = IntStream.range(0, N).boxed().toArray(Integer[]::new);
		Arrays.sort(idx, (i, j) -> Long.compare(cntX[j] * 1L * cntScore[i], cntX[i] * 1L * cntScore[j]));

		long pre = 0;
		for(int i : idx) {
			ans += pre * cntScore[i];
			pre += cntX[i];
		}
		io.println(ans);
	}
}