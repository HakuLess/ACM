package leetcode.contest.b90

import utils.print
import java.util.*
import kotlin.collections.ArrayList

fun main() {
    val s = SolutionD()
    s.secondGreaterElement(intArrayOf(2, 4, 0, 9, 6)).print()
}

class SolutionD {
    fun secondGreaterElement(nums: IntArray): IntArray {
        val l = ArrayList<Pair<Int, Int>>()
        for (i in nums.indices) {
            l.add(Pair(i, nums[i]))
        }
        // 按值递减排序
        l.sortWith(compareBy({ -it.second }, { it.first }))
        val ans = IntArray(nums.size) { -1 }
        val ts = TreeSet<Int>()
        for (i in l.indices) {
            ts.add(l[i].first)
            val a = ts.ceiling(l[i].first + 1) ?: continue
            val b = ts.ceiling(a + 1) ?: continue
            ans[l[i].first] = nums[b]
        }
        return ans
    }
}