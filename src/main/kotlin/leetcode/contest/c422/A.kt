package leetcode.contest.c422

class SolutionA {
    fun isBalanced(num: String): Boolean {
        var evenSum = 0
        var oddSum = 0

        for (i in num.indices) {
            val digit = num[i].digitToInt()
            if (i % 2 == 0) {
                evenSum += digit
            } else {
                oddSum += digit
            }
        }

        return evenSum == oddSum
    }
}