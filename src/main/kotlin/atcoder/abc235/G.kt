package atcoder.abc235

fun main(args: Array<String>) {
    val mod = 998244353L
    val (n, a, b, c) = readLine()!!.trim().split(' ').map { it.toInt() }
    val seen = HashMap<String, Long>()
    fun dfs(cur: Int, a: Int, b: Int, c: Int): Long {
        val key = "$cur,$a,$b,$c"
        if (key in seen) {
            return seen[key]!!
        }
        if (a < 0 || b < 0 || c < 0) return 0L
        if (cur == n) {
            return 1L
        }
        var ans = 0L
        ans += dfs(cur + 1, a - 1, b, c)
        ans += dfs(cur + 1, a - 1, b - 1, c)
        ans += dfs(cur + 1, a - 1, b, c - 1)
        ans += dfs(cur + 1, a - 1, b - 1, c - 1)
        ans += dfs(cur + 1, a, b - 1, c)
        ans += dfs(cur + 1, a, b - 1, c - 1)
        ans += dfs(cur + 1, a, b, c - 1)
        return (ans % mod).also {
            seen[key] = it
        }
    }
    println(dfs(0, a, b, c))
}