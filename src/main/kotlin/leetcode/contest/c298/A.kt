package leetcode.contest.c298

class SolutionA {
    fun greatestLetter(s: String): String {
        var ans = ""
        ('A'..'Z').forEach {
            if (it in s && it + ('a' - 'A') in s) {
                ans = it.toString()
            }
        }
        return ans
    }
}