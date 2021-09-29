package luogu

import utils.SegmentTree
import utils.print

// todo not finish
// 本地testCase正常，但是Case1都显示WA
fun main() {
    val n = readLine()!!.trim().toInt()
    val arr = readLine()!!.trim().split(" ").map { it.toInt() }
    val root = SegmentTree<ArrayList<Int>>(start = 0, end = n, value = arrayListOf()) { a, b ->
        val list = ArrayList<Int>()
        list.addAll(a)
        list.addAll(b)
        ArrayList(list.sorted())
    }
    for (i in arr.indices) {
        root.update(root, i + 1, arrayListOf(arr[i]))
    }

    val t = readLine()!!.trim().toInt()
    val ans = ArrayList<Int>()
    repeat(t) {
        val (x, y, k) = readLine()!!.trim().split(" ").map { it.toInt() }
        ans.add(root.query(root, x, y)[k - 1])
    }
    ans.forEach {
        println(it)
    }
}