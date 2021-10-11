package atcoder.abc222

import utils.print

// 居间数组个数
// 后缀和
fun main(args: Array<String>) {
    val n = readLine()!!.trim().toInt()
    val a = readLine()!!.trim().split(' ').map { it.toInt() }
    val b = readLine()!!.trim().split(' ').map { it.toInt() }
    val mod = 998244353L

    val size = 3050
    var cur = LongArray(size)
    for (i in a.last()..b.last()) {
        cur[i] = 1
    }
    for (i in a.lastIndex - 1 downTo 0) {
        val suf = LongArray(size)
        for (j in suf.indices.reversed()) {
            suf[j] = (suf.getOrElse(j + 1) { 0 } + cur[j]) % mod
        }
        val next = LongArray(size)
        for (j in a[i]..b[i]) {
            next[j] = suf[j]
        }
        cur = next
    }
    println(cur.sum() % mod)
}

//fun main(args: Array<String>) {
//    val n = readLine()!!.trim().toInt()
//    val a = readLine()!!.trim().split(' ').map { it.toInt() }
//    val b = readLine()!!.trim().split(' ').map { it.toInt() }
//    val mod = 998244353L
//
//    val seen = HashMap<String, Long>()
//    fun dfs(cur: Int, index: Int): Long {
//        val key = "$cur,$index"
//        if (key in seen) return seen[key]!!
//        if (index == n) {
//            return 1L
//        }
//        var ans = 0L
//        for (i in maxOf(cur, a[index])..b[index]) {
//            ans = (ans + dfs(i, index + 1)) % mod
//        }
//        return ans.also { seen[key] = it }
//    }
//
//    var ans = 0L
//    for (i in a[0]..b[0]) {
//        ans = (ans + dfs(i, 1)) % mod
//    }
//    println(ans)
//}