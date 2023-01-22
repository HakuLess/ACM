package leetcode.contest.c329

class SolutionA {
    fun alternateDigitSum(n: Int): Int {
        var ans = 0
        var c = true
        n.toString().forEach {
            if (c) {
                ans += it - '0'
            } else {
                ans -= it - '0'
            }
            c = !c
        }
        return ans
    }
}