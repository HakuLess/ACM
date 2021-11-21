package leetcode.contest.c268

class SolutionA {
    fun maxDistance(colors: IntArray): Int {
        var ans = 0
        for (i in 0 until colors.lastIndex) {
            for (j in i + 1 until colors.size) {
                if (colors[i] != colors[j]) {
                    ans = maxOf(ans, j - i)
                }
            }
        }
        return ans
    }
}