package leetcode.contest.b127


class SolutionB {
    fun minimumLevels(possible: IntArray): Int {
        val sum = possible.sum() - possible.count { it == 0 }
        var a = 0
        for (i in 0 until possible.lastIndex) {
            if (possible[i] == 1) {
                a++
            } else {
                a--
            }

            val b = sum - a
            if (a > b) {
                return i + 1
            }
        }
        return -1
    }
}