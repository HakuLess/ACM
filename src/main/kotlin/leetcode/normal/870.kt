package leetcode.normal

import utils.print
import java.util.*
import kotlin.collections.ArrayList

fun main() {
    val s = Solution870()
    // 4, 7, 8, 6, 5, 4, 0, 6, 4, 2
    // 4, 0, 8, 5, 6, 4, 2, 6, 4, 7
    // 0, 8, 7, 4, 4, 2, 8, 5, 2, 0
    s.advantageCount(intArrayOf(8, 2, 4, 4, 5, 6, 6, 0, 4, 7), intArrayOf(0, 8, 7, 4, 4, 2, 8, 5, 2, 0)).print()
}

class Solution870 {
    fun advantageCount(nums1: IntArray, nums2: IntArray): IntArray {
        val tm = TreeMap<Int, Int>()
        nums1.forEach {
            tm[it] = tm.getOrDefault(it, 0) + 1
        }
        val l = ArrayList<Pair<Int, Int>>()
        for (i in nums2.indices) {
            l.add(Pair(i, nums2[i]))
        }
        l.sortBy { it.second }
        return l.map {
            val key = tm.ceilingEntry(it.second + 1)?.key ?: tm.firstEntry().key
            if (tm[key] == 1) {
                tm.remove(key)
            } else {
                tm[key] = tm[key]!! - 1
            }
            Pair(it.first, key)
        }.sortedBy { it.first }.map { it.second }.toIntArray()
    }
}