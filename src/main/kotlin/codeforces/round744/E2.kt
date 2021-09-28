package codeforces.round744

import utils.SegmentTree

fun main(args: Array<String>) {
    val t = readLine()!!.trim().toInt()
    repeat(t) {
        val n = readLine()!!.trim().toInt()
        val arr = readLine()!!.trim().split(" ").map { it.toInt() }
        val min = arr.minOrNull()!!
        val l = arr.map { it - min + 1 }

        val end = l.maxOrNull()!! + 100
        val ans = ArrayList<Int>()
        val root = SegmentTree<Int>(start = 0, end = end, value = 0) { a, b -> a + b }
        var res = 0
        l.forEach {
            // 比当前值小
            val a = root.query(root, 0, it - 1)
            // 比当前值大
            val b = root.query(root, it + 1, end)
            if (ans.isEmpty() || b > a) {
                ans.add(0, it)
            } else {
                ans.add(it)
            }
            res += minOf(a, b)
            root.update(root, it, it, root.query(root, it, it) + 1)
        }
        println(res)
    }
}