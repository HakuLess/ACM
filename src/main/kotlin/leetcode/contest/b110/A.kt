package leetcode.contest.b110

class SolutionA {
    fun accountBalanceAfterPurchase(purchaseAmount: Int): Int {
        return 100 - (purchaseAmount + 5) / 10 * 10
    }
}