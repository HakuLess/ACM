package leetcode.contest.b176

class SolutionC {
    fun prefixConnected(words: Array<String>, k: Int): Int {
        val freq = HashMap<String, Int>()

        for (w in words) {
            if (w.length < k) continue
            val prefix = w.substring(0, k)
            freq[prefix] = (freq[prefix] ?: 0) + 1
        }

        var ans = 0
        for (count in freq.values) {
            if (count >= 2) ans++
        }

        return ans
    }
}