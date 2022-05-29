package leetcode.contest.c295

class SolutionA {
    fun rearrangeCharacters(s: String, target: String): Int {
        var ans = Int.MAX_VALUE
        target.forEach { c ->
            ans = minOf(ans, s.count { it == c } / target.count { it == c })
        }
        return ans
    }
}