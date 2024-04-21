package leetcode.contest.c394

class SolutionA {
    fun numberOfSpecialChars(word: String): Int {
        var ans = 0
        for (c in 'a'..'z') {
            if (word.contains(c) && word.contains(c.uppercaseChar())) {
                ans++
            }
        }
        return ans
    }
}