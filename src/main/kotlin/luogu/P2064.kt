package luogu

// todo not finish
// 90分 WA testCase2
fun main() {
    val n = readLine()!!.trim().toInt()
    fun dfs(cur: Int): Int {
        var ans = Int.MAX_VALUE / 2
        if (cur == 0) return 0
        for (i in 2..9) {
            if (cur % i == 0) {
                ans = minOf(ans, dfs(cur / i - 1))
            }
        }
        return ans + 1
    }
    dfs(n - 1).let {
        if (it > Int.MAX_VALUE / 2) println(-1)
        else println(it + 1)
    }
}