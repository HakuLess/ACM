package leetcode.contest.b128

import utils.print
import utils.toArrayList
import java.util.*
import kotlin.collections.HashMap

fun main() {
    val s = SolutionD()
    // 6
    s.numberOfSubarrays(intArrayOf(1, 4, 3, 3, 2)).print()
}

class SolutionD {
    fun numberOfSubarrays(nums: IntArray): Long {
        var ans = 0L

        var curMap = HashMap<Int, Long>()
        val st = Stack<Int>()
        for (i in nums.indices) {
            while (st.isNotEmpty() && st.peek() < nums[i]) {
                val item = st.pop()
                curMap[item] = curMap[item]!! - 1
            }

            st.add(nums[i])
            curMap[nums[i]] = curMap.getOrDefault(nums[i], 0) + 1
            ans += curMap[nums[i]]!!
        }

        return ans
    }
}