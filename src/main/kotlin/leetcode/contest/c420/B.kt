package leetcode.contest.c420

class SolutionB {
    fun numberOfSubstrings(s: String, k: Int): Int {
        var count = 0

        for (start in s.indices) {
            val freq = IntArray(26)

            for (end in start until s.length) {
                val charIndex = s[end] - 'a'
                freq[charIndex]++

                if (freq.any { it >= k }) {
                    count++
                }
            }
        }

        return count
    }
}