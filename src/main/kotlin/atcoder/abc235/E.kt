package atcoder.abc235

import utils.*

fun main(args: Array<String>) {
    val (n, m, q) = readLine()!!.trim().split(' ').map { it.toInt() }
    val source = ArrayList<IntArray>()
    repeat(m) {
        val (a, b, c) = readLine()!!.trim().split(' ').map { it.toInt() }
        source.add(intArrayOf(a - 1, b - 1, c, -1))
    }
    val ufs = UFS(n)
    repeat(q) {
        val (u, v, w) = readLine()!!.trim().split(' ').map { it.toInt() }
        source.add(intArrayOf(u - 1, v - 1, w, it))
    }
    val ans = BooleanArray(q)
    source.sortBy { it[2] }
    source.forEach {
        if (it[3] == -1) {
            ufs.union(it[0], it[1])
        } else {
            ans[it[3]] = ufs.find(it[0]) != ufs.find(it[1])
        }
    }
    ans.forEach {
        println(if (it) "Yes" else "No")
    }
}