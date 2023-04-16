package leetcode.contest.c341

class SolutionB {
    fun maxDivScore(nums: IntArray, divisors: IntArray): Int {
        var ans = divisors[0]
        var cur = -1
        for (i in divisors.indices) {
            val cnt = nums.count { it % divisors[i] == 0 }
            if (cnt > cur) {
                cur = cnt
                ans = divisors[i]
            } else if (cnt == cur) {
                ans = minOf(divisors[i], ans)
            }
        }
        return ans
    }
}