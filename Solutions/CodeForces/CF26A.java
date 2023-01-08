
/**
 * @url: https://codeforces.com/problemset/problem/26/A
 * @tag: 数论 | 素数
 * @date: 2023-01-06
 */
class CF26A {
	private static final int MAX_N = 3000;
	private static int[] cnt = new int[MAX_N + 10];

	static {
		for(int i = 2; i <= MAX_N; ++i) {
			if(cnt[i] != 0)		continue;
			for(int j = 2; i * j <= MAX_N; ++j)
				cnt[i * j]++;
		}
	} 

	private static boolean isPrime(int num) {
		for(int i = 2; i * i <= num; ++i) 
			if(num % i == 0)
				return false;
		return true;
	}

	public void solution(Kattio io) {
		int ans = 0;
		for(int i = io.nextInt(); i > 1; --i)
			if(cnt[i] == 2)
				ans++;
		io.println(ans);
	}
}