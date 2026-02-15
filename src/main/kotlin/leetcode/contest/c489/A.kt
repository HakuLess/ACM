package leetcode.contest.c489

class SolutionA {
    fun toggleLightBulbs(bulbs: List<Int>): List<Int> {
        val on = BooleanArray(101)
        for (b in bulbs) {
            on[b] = !on[b]
        }
        val result = mutableListOf<Int>()
        for (i in 1..100) {
            if (on[i]) result.add(i)
        }
        return result
    }
}