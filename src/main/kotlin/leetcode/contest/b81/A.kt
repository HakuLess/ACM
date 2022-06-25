package leetcode.contest.b81

class SolutionA {
    fun countAsterisks(s: String): Int {
        var ans = 0
        s.split('|').forEachIndexed { index, s ->
            if (index % 2 == 0) {
                ans += s.count { it == '*' }
            }
        }
        return ans
    }
}