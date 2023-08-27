package leetcode.contest.c360

class SolutionB {
    fun minimumPossibleSum(n: Int, target: Int): Long {
        val set = HashSet<Long>()
        var cur = 1
        while (set.size != n) {
            if ((target - cur).toLong() !in set) {
                set.add(cur.toLong())
            }
            cur++
        }
        return set.sumOf { it }
    }
}