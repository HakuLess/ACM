package leetcode.contest.b105

class SolutionA {
    fun buyChoco(prices: IntArray, money: Int): Int {
        prices.sort()
        return (prices[0] + prices[1]).let {
            if (it > money) money
            else money - it
        }
    }
}