package leetcode.contest.c364

class SolutionB {
    fun maximumSumOfHeights(maxHeights: List<Int>): Long {
        var ans = 0L
        for (i in maxHeights.indices) {
            var tmp = 0L + maxHeights[i]
            var left = maxHeights[i]
            for (j in i - 1 downTo 0) {
                left = minOf(left, maxHeights[j])
                tmp += left
            }

            var right = maxHeights[i]
            for (j in i + 1 until maxHeights.size) {
                right = minOf(right, maxHeights[j])
                tmp += right
            }
            ans = maxOf(ans, tmp)
        }
        return ans
    }
}