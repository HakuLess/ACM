package leetcode.contest.b66

class SolutionA {
    fun countWords(words1: Array<String>, words2: Array<String>): Int {
        var ans = 0
        words1.forEach { str ->
            if (words1.count { it == str } == 1 && words2.count { it == str } == 1) {
                ans++
            }
        }
        return ans
    }
}