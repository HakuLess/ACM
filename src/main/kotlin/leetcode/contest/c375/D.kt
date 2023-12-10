package leetcode.contest.c375

import utils.Interval
import utils.print
import utils.toBigInteger
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

fun main() {
    val s = SolutionD()
    s.numberOfGoodPartitions(intArrayOf(1, 2, 3, 4)).print()
    s.numberOfGoodPartitions(intArrayOf(1, 1, 1, 1)).print()
    s.numberOfGoodPartitions(intArrayOf(1, 2, 1, 3)).print()
}

class SolutionD {
    fun numberOfGoodPartitions(nums: IntArray): Int {
        val mod = 1000000007L.toBigInteger()
        val mapFst = HashMap<Int, Int>()
        val mapLst = HashMap<Int, Int>()
        for (i in nums.indices) {
            val item = nums[i]
            if (item in mapFst.keys) {
                mapLst[item] = i
            } else {
                mapFst[item] = i
            }
        }
        val l = ArrayList<Interval>()
        mapLst.keys.forEach {
            l.add(Interval(mapFst[it]!!, mapLst[it]!!))
        }
        l.sortBy { it.left }

        val st = Stack<Interval>()
        for (i in l.indices) {
            val cur = l[i]
            if (st.isEmpty()) {
                st.push(cur)
            } else {
                val pre = st.pop()
                if (cur.left < pre.right) {
                    st.push(Interval(pre.left, maxOf(pre.right, cur.right)))
                } else {
                    st.push(pre)
                    st.push(cur)
                }
            }
        }

        // 可插装数量
        var n = nums.size - 1
        while (st.isNotEmpty()) {
            val item = st.pop()
            n -= (item.right - item.left)
        }

        return 2L.toBigInteger().pow(n).mod(mod).toInt()
    }
}