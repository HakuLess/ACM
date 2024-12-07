package leetcode.contest.b145

import utils.TypedUFS
import utils.print

fun main() {
    val s = SolutionD()
    // 4
    s.countComponents(intArrayOf(2, 4, 8, 3, 9), 5).print()
    // 2
    s.countComponents(intArrayOf(2, 4, 8, 3, 9, 12), 10).print()
}

class SolutionD {
    fun countComponents(nums: IntArray, threshold: Int): Int {
        val ufs = TypedUFS<Int>(3 * 100000 + 5)
        for (i in nums.indices) {
            val num = nums[i]
            var multiple = num
            while (multiple <= threshold) {
                ufs.union(num, multiple)
                multiple += num
            }
        }

        val ans = HashSet<Int>()
        nums.forEach {
            ans.add(ufs.typedFind(it))
        }
        return ans.size
    }
}