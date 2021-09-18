package leetcode.contest.b61

import utils.MultiSet

class Solution5860 {
    fun findOriginalArray(changed: IntArray): IntArray {
        if (changed.size % 2 != 0) return intArrayOf()
        val n = changed.size / 2
        val multiSet = MultiSet<Int>()
        changed.forEach {
            multiSet.add(it)
        }
        val ans = arrayListOf<Int>()
        for (i in 0 until n) {
            val l = multiSet.popLeft()
            ans.add(l)
            if (!multiSet.remove(l * 2)) return intArrayOf()
        }
        return ans.toIntArray()
    }
}