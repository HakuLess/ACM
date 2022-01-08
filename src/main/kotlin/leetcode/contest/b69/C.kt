package leetcode.contest.b69

class SolutionC {
    fun longestPalindrome(words: Array<String>): Int {
        val map = HashMap<String, Int>()
        var ans = 0
        for (i in words.indices) {
            if (words[i].reversed() in map.keys) {
                ans += 4
                map[words[i].reversed()] = map.getOrDefault(words[i].reversed(), 0) - 1
                if (map[words[i].reversed()] == 0)
                    map.remove(words[i].reversed())
            } else {
                map[words[i]] = map.getOrDefault(words[i], 0) + 1
            }
        }
        val set = map.keys
        if (set.any { it[0] == it[1] }) {
            ans += 2
        }
        return ans
    }
}