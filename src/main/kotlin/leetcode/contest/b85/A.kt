package leetcode.contest.b85

class SolutionA {
    fun minimumRecolors(blocks: String, k: Int): Int {
        var b = 0
        var ans = k
        for (i in blocks.indices) {
            if (blocks[i] == 'B') {
                b++
            }
            if (i - k in blocks.indices && blocks[i - k] == 'B') {
                b--
            }
            ans = minOf(ans, k - b)
        }
        return ans
    }
}