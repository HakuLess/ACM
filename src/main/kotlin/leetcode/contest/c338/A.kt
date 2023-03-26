package leetcode.contest.c338

class SolutionA {
    fun kItemsWithMaximumSum(numOnes: Int, numZeros: Int, numNegOnes: Int, k: Int): Int {
        var ans = minOf(numOnes, k)
        if (k > numOnes + numZeros) {
            ans -= k - numOnes - numZeros
        }
        return ans
    }
}