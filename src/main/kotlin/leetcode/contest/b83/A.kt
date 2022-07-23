package leetcode.contest.b83

class SolutionA {
    fun bestHand(ranks: IntArray, suits: CharArray): String {
        if (suits.all { it == suits[0] }) return "Flush"
        val map = HashMap<Int, Int>()
        ranks.forEach {
            map[it] = map.getOrDefault(it, 0) + 1
        }
//        if (map.values.max()!! >= 3) return "Three of a Kind"
//        if (map.values.max()!! >= 2) return "Pair"
        return "High Card"
    }
}