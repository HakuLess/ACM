package codeforces.round744

import utils.SegmentTree

fun main(args: Array<String>) {
    val t = readLine()!!.trim().toInt()
    repeat(t) {
        val n = readLine()!!.trim().toInt()
        val arr = readLine()!!.trim().split(" ").map { it.toInt() }

        // 离散化
        val map = HashMap<Int, Int>()
        var i = 1
        arr.sorted().distinct().forEach {
            map[it] = i
            i++
        }
        val l = arr.map { map[it]!! }
        val end = i + 1

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