package leetcode.contest.c301

class SolutionA {
    fun fillCups(amount: IntArray): Int {
        amount.sort()
        if (amount[2] >= amount[0] + amount[1]) {
            return amount[2]
        } else {
            return (amount.sum() + 1) / 2
        }
    }
}