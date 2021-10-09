package atcoder.abc222

import utils.print

fun main(args: Array<String>) {
    val n = readLine()!!.trim().toInt()
    val a = readLine()!!.trim().split(' ').map { it.toInt() }
    val b = readLine()!!.trim().split(' ').map { it.toInt() }
    val mod = 998244353L

    val seen = HashMap<String, Long>()
    fun dfs(cur: Int, index: Int): Long {
        val key = "$cur,$index"
        if (key in seen) return seen[key]!!
        if (index == n) {
            return 1L
        }
        var ans = 0L
        for (i in maxOf(cur, a[index])..b[index]) {
            ans = (ans + dfs(i, index + 1)) % mod
        }
        return ans.also { seen[key] = it }
    }

    var ans = 0L
    for (i in a[0]..b[0]) {
        ans = (ans + dfs(i, 1)) % mod
    }
    println(ans)
}