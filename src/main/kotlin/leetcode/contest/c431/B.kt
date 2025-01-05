package leetcode.contest.c431

class SolutionB {
    fun calculateScore(s: String): Long {
        val map = mutableMapOf<Char, MutableList<Int>>()
        var score = 0L

        for (i in s.indices) {
            val currentChar = s[i]
            val mirrorChar = ('a' + ('z' - currentChar))

            if (map[mirrorChar]?.isNotEmpty() == true) {
                val j = map[mirrorChar]!!.removeAt(map[mirrorChar]!!.size - 1)
                score += i - j
            } else {
                map.computeIfAbsent(currentChar) { mutableListOf() }.add(i)
            }
        }

        return score
    }
}