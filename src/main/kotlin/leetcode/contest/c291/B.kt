package leetcode.contest.c291

class SolutionB {
    fun minimumCardPickup(cards: IntArray): Int {
        val map = HashMap<Int, Int>()
        var ans = Int.MAX_VALUE
        for (i in cards.indices) {
            if (cards[i] in map.keys) {
                ans = minOf(ans, i - map[cards[i]]!!)
            }
            map[cards[i]] = i
        }
        if (ans == Int.MAX_VALUE) return -1
        return ans + 1
    }
}