package leetcode.contest.b130

class SolutionC {
    fun minimumSubstringsInPartition(s: String): Int {

        val seen = HashMap<Int, Int>()

        fun dfs(start: Int): Int {
            if (start !in s.indices) {
                return 0
            }
            if (start in seen) return seen[start]!!

            val map = HashMap<Char, Int>()
            var tmp = Int.MAX_VALUE / 2
            for (i in start..s.lastIndex) {
                map[s[i]] = map.getOrDefault(s[i], 0) + 1

                val cnt = map[s[i]]
                if (map.all { it.value == cnt }) {
                    tmp = minOf(tmp, 1 + dfs(i + 1))
                }
            }
            return tmp.also {
                seen[start] = it
            }
        }

        return dfs(0)
    }
}