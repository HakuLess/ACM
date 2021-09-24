package luogu

import utils.SegmentTree
import utils.discretization

// 80分 TODO TestCase2报错
fun main(args: Array<String>) {
    val mod = 999999997L

    val n = readLine()!!.trim().toInt()
    val a = readLine()!!.trim().split(" ").map { it.toInt() }.toIntArray().discretization()
    val b = readLine()!!.trim().split(" ").map { it.toInt() }.toIntArray().discretization()

    val map = HashMap<Int, Int>()
    for (i in b.indices) {
        map[b[i]] = i
    }

    val g = IntArray(n) { 0 }
    for (i in a.indices) {
        g[i] = map[a[i]]!!
    }

    fun reversePairs(nums: IntArray): Int {
        val res = nums.discretization()
        val root = SegmentTree<Int>(start = 0, end = Int.MAX_VALUE, value = 0) { a, b ->
            a + b
        }
        var ans = 0
        res.reversed().forEach {
            ans = ((ans.toLong() + root.query(root, 0, it - 1)) % mod).toInt()
            root.update(root, it, root.query(root, it, it) + 1)
        }
        return ans
    }

    println((reversePairs(g)))
}