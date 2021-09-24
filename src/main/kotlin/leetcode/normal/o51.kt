package leetcode.normal

import utils.SegmentTree
import utils.print

fun main() {
    val s = SolutionO51()
    s.reversePairs(intArrayOf(7, 5, 6, 4)).print()
    s.reversePairs(intArrayOf(-5, -5)).print()
}

// 逆序对
// 离散化
class SolutionO51 {
    fun reversePairs(nums: IntArray): Int {
        val map = HashMap<Int, Int>()
        var i = 1
        nums.sorted().distinct().forEach {
            map[it] = i
            i++
        }
        val res = nums.map { map[it]!! }
        val root = SegmentTree<Int>(start = 0, end = 50050, value = 0) { a, b ->
            a + b
        }
        var ans = 0
        res.reversed().forEach {
//            println("Query 0 ~ ${it - 1} is ${root.query(root, 0, it - 1)}")
            ans += root.query(root, 0, it - 1)
//            println("Query $it ${root.query(root, it, it)}")
            root.update(root, it, root.query(root, it, it) + 1)
        }
        return ans
    }
}