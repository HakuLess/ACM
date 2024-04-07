package leetcode.contest.c392

class SolutionB {
    fun getSmallestString(s: String, k: Int): String {
        var cur = 0
        val n = s.length
        val sb = StringBuilder()
        for (i in 0 until n) {
            for (c in 'a'..'z') {
                val add = minOf((s[i] - c + 26) % 26, (c - s[i] + 26) % 26)
                if (add + cur <= k) {
                    cur += add
                    sb.append(c)
                    break
                }
            }
        }
        return sb.toString()
    }
}