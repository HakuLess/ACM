package leetcode.contest.c484

class SolutionC {
    fun countPairs(words: Array<String>): Long {
        val map = HashMap<String, Int>()

        for (word in words) {
            val base = word[0] - 'a'
            val sb = StringBuilder()

            for (c in word) {
                val diff = (c - 'a' - base + 26) % 26
                sb.append(diff).append('#')
            }

            val key = sb.toString()
            map[key] = (map[key] ?: 0) + 1
        }

        var ans = 0L
        for (count in map.values) {
            if (count > 1) {
                ans += count.toLong() * (count - 1) / 2
            }
        }
        return ans
    }
}