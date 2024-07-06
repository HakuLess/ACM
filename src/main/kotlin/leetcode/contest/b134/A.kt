package leetcode.contest.b134

class SolutionA {
    fun numberOfAlternatingGroups(colors: IntArray): Int {
        val n = colors.size
        var count = 0

        // 遍历数组
        for (i in colors.indices) {
            // 取当前元素和相邻的两个元素，考虑环形结构
            val prev = colors[(i - 1 + n) % n]
            val curr = colors[i]
            val next = colors[(i + 1) % n]

            // 判断是否形成交替组
            if (curr != prev && curr != next) {
                count++
            }
        }

        return count
    }
}