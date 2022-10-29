package leetcode.contest.b90

class SolutionA {
    fun oddString(words: Array<String>): String {
        val c = words.map { s ->
            val arr = IntArray(words[0].length - 1)
            for (i in arr.indices) {
                arr[i] = s[i + 1] - s[i]
            }
            Pair(s, arr.joinToString())
        }.sortedBy { it.second }
        if (c.count { it.second == c[0].second } == 1) {
            return c[0].first
        } else {
            return c.last().first
        }
    }
}