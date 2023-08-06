package leetcode.contest.c357

class SolutionA {
    fun finalString(s: String): String {
        var ans = ""
        s.forEach {
            if (it == 'i') {
                ans = ans.reversed()
            } else {
                ans += it
            }
        }
        return ans
    }
}