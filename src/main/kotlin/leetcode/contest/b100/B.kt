package leetcode.contest.b100

import java.util.*

class SolutionB {
    fun maximizeGreatness(nums: IntArray): Int {
        val tm = TreeMap<Int, Int>()
        nums.forEach {
            tm[it] = tm.getOrDefault(it, 0) + 1
        }
        var ans = 0
        nums.forEach {
            val key = tm.ceilingKey(it + 1)
            if (key != null) {
                ans++
                tm[key] = tm[key]!! - 1
                if (tm[key] == 0) {
                    tm.remove(key)
                }
            }
        }
        return ans
    }
}