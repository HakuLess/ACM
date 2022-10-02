package leetcode.contest.c313

class SolutionA {
    fun commonFactors(a: Int, b: Int): Int {
        var ans = 0
        for (i in 1..minOf(a, b)) {
            if (a % i == 0 && b % i == 0) {
                ans++
            }
        }
        return ans
    }
}