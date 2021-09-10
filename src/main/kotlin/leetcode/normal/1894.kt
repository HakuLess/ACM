package leetcode.normal

class Solution1894 {
    fun chalkReplacer(chalk: IntArray, k: Int): Int {
        var sum = 0L
        chalk.forEach { sum += it }
        var left = k % sum
        for (i in chalk.indices) {
            left -= chalk[i]
            if (left < 0) return i
        }
        return -1
    }
}