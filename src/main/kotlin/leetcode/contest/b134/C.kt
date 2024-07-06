package leetcode.contest.b134

import utils.SegmentTree
import utils.print

fun main() {
    val s = SolutionC()
    // 3
    s.numberOfAlternatingGroups(intArrayOf(0, 1, 0, 1, 0), 3).print()
    // 2
    s.numberOfAlternatingGroups(intArrayOf(0, 1, 0, 0, 1, 0, 1), 6).print()
    // 0
    s.numberOfAlternatingGroups(intArrayOf(1, 1, 0, 1), 4).print()
}

class SolutionC {
    fun numberOfAlternatingGroups(colors: IntArray, k: Int): Int {
        val n = colors.size
        val root = SegmentTree<Int>(start = 0, end = n * 3 + 5, value = 0) { a, b ->
            a + b
        }
        for (i in colors.indices) {
//            println("enter $i for ${n + i}")
            if (colors[i] != colors[(i - 1 + n) % n] && colors[i] != colors[(i + 1 + n) % n]) {
                root.update(root, n + i, n + i, 1)
                root.update(root, i, i, 1)
//                println("update ${n + i} with 1")
            }
        }
//        root.print()
        var ans = 0
        for (i in 0 until n) {
            val start = i + 1
            val res = root.query(root, start, start + k - 3)
//            println("query ${start}..${start + k - 3} with $res")
            if (res == k - 2) {
                ans++
            }
        }
        return ans
    }
}