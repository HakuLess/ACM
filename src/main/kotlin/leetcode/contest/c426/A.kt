package leetcode.contest.c426

class SolutionA {
    fun smallestNumber(n: Int): Int {
        var ans = 1
        while (ans < n) {
            ans = ans * 2 + 1
        }
        return ans
    }
}