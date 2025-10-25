package leetcode.normal

class Solution1716 {
    fun totalMoney(n: Int): Int {
        val k = n / 7
        val f = 28
        val l = 28 + (k - 1) * 7
        val arithmeticSum = k * (f + l) / 2

        val monday = 1 + k
        var finalWeek = 0
        for (day in 0 until n % 7) {
            finalWeek += monday + day
        }

        return arithmeticSum + finalWeek
    }
}