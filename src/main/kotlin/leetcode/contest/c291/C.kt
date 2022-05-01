package leetcode.contest.c291

import utils.print

fun main() {
    val s = SolutionC()
    s.countDistinct(intArrayOf(2, 3, 3, 2, 2), 2, 2).print()
    s.countDistinct(intArrayOf(1, 2, 3, 4), 4, 1).print()
}

class SolutionC {
    fun countDistinct(nums: IntArray, k: Int, p: Int): Int {
        var ans = 0
        val seen = HashSet<String>()
        for (i in nums.indices) {
            var c = 0
            val key = StringBuilder()
            for (j in i until nums.size) {
                key.append("${nums[j]},")
                if (nums[j] % p == 0) {
                    c++
                }
                if (c > k) break
                if (key.toString() !in seen) {
                    ans++
                    seen.add(key.toString())
                }
            }
        }
        return ans
    }
}