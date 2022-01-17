package leetcode.contest.c276


class SolutionB {
    fun minMoves(target: Int, maxDoubles: Int): Int {
        if (target == 1) return 0
        if (maxDoubles == 0) {
            return target - 1
        }
        if (target % 2 == 0) {
            return 1 + minMoves(target / 2, maxDoubles - 1)
        }
        // target % 2 != 0
        return 2 + minMoves(target / 2, maxDoubles - 1)
    }
}