package leetcode.contest.c267

class SolutionA {
    fun timeRequiredToBuy(tickets: IntArray, k: Int): Int {
        val max = tickets[k]
        var ans = 0
        for (i in tickets.indices) {
            ans += if (i <= k) {
                minOf(max, tickets[i])
            } else {
                minOf(max - 1, tickets[i])
            }
        }
        return ans
    }
}