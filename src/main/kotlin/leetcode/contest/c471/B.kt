package leetcode.contest.c471

class SolutionB {
    fun longestBalanced(s: String): Int {
        val map = HashMap<Char, Int>()
        var ans = 0
        for (i in s.indices) {
            map.clear()
            for (j in i until s.length) {
                val item = s[j]
                map[item] = map.getOrDefault(item, 0) + 1
                if (map.values.toSet().size == 1) {
                    ans = maxOf(ans, j - i + 1)
                }
            }
        }
        return ans
    }
}