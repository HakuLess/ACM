package leetcode.contest.b93

class SolutionA {
    fun maximumValue(strs: Array<String>): Int {
        var ans = 0
        strs.forEach {
            ans = if (it.toIntOrNull() == null) {
                maxOf(it.length, ans)
            } else {
                maxOf(it.toInt(), ans)
            }
        }
        return ans
    }
}