package codeforces.round764

import utils.TypedUFS

// 权值取或的最小生成树
fun main() {
    val t = readLine()!!.trim().toInt()
    repeat(t) {
        readLine()
        val (n, m) = readLine()!!.split(" ").map { it.toInt() }
        val ufs = TypedUFS<Int>()
        repeat(m) {
            val arr = readLine()!!.split(" ").map { it.toInt() }
            for (i in arr.indices) {
//                ufs.union()

            }
        }
    }
}