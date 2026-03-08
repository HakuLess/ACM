package leetcode.contest.c492

class SolutionA {
    fun minimumIndex(capacity: IntArray, itemSize: Int): Int {
        var minCap = Int.MAX_VALUE
        var ans = -1

        for (i in capacity.indices) {
            if (capacity[i] >= itemSize && capacity[i] < minCap) {
                minCap = capacity[i]
                ans = i
            }
        }

        return ans
    }
}