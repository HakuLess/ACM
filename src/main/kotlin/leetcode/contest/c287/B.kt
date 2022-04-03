package leetcode.contest.c287

class SolutionB {
    fun findWinners(matches: Array<IntArray>): List<List<Int>> {
        val win = HashMap<Int, Int>()
        val lose = HashMap<Int, Int>()
        matches.forEach {
            win[it[0]] = win.getOrDefault(it[0], 0) + 1
            lose[it[1]] = lose.getOrDefault(it[1], 0) + 1
        }
        val ans0 = arrayListOf<Int>()
        val ans1 = arrayListOf<Int>()
        win.forEach { t, u ->
            if (u > 0 && lose.getOrDefault(t, 0) == 0) {
                ans0.add(t)
            }
        }
        lose.forEach { t, u ->
            if (u == 1) {
                ans1.add(t)
            }
        }
        return listOf(ans0.sorted(), ans1.sorted())
    }
}