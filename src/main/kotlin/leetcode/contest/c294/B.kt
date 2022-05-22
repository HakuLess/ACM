package leetcode.contest.c294

class SolutionB {
    fun maximumBags(capacity: IntArray, rocks: IntArray, additionalRocks: Int): Int {
        val n = capacity.size
        val diff = IntArray(n)
        for (i in diff.indices) {
            diff[i] = capacity[i] - rocks[i]
        }
        diff.sort()
        var left = additionalRocks
        var ans = 0
        for (i in diff.indices) {
            if (diff[i] <= left) {
                left -= diff[i]
                ans++
            } else {
                break
            }
        }
        return ans
    }
}