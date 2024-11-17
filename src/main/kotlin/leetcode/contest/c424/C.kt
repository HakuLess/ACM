package leetcode.contest.c424

class SolutionC {
    fun minZeroArray(nums: IntArray, queries: Array<IntArray>): Int {
        if (nums.all { it == 0 }) return 0
        val n = nums.size
        val delta = IntArray(n + 1)

        for ((index, query) in queries.withIndex()) {
            val l = query[0]
            val r = query[1]
            val diff = query[2]

            delta[l] += diff
            if (r + 1 < n) {
                delta[r + 1] -= diff
            }

            var curDiff = 0
            var success = true
            for (i in 0 until n) {
                curDiff += delta[i]
                if (nums[i] > curDiff) {
                    success = false
                    break
                }
            }

            if (!success) continue

            return index + 1
        }

        return -1
    }
}