package leetcode.contest.b93

class SolutionA {
    fun maximumValue(strs: Array<String>): Int {
        var ans = 0
        strs.forEach {
            if (it.toIntOrNull() == null) {
                ans = maxOf(it.length, ans)
            } else {
                ans = maxOf(it.toInt(), ans)
            }
        }
        return ans
    }
}