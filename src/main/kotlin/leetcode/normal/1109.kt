package leetcode.normal

// 差分数组
class Solution1109 {
    fun corpFlightBookings(bookings: Array<IntArray>, n: Int): IntArray {
        val diff = IntArray(n + 1)
        bookings.forEach {
            diff[it[0] - 1] += it[2]
            diff[it[1]] -= it[2]
        }
        val ans = IntArray(n)
        ans[0] = diff[0]
        for (i in 1 until n) {
            ans[i] = ans[i - 1] + diff[i]
        }
        return ans
    }
}