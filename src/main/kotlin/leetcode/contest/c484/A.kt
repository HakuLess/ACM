package leetcode.contest.c484

class SolutionA {
    fun residuePrefixes(s: String): Int {
        val seen = BooleanArray(26)
        var distinct = 0
        var ans = 0

        for (i in s.indices) {
            val idx = s[i] - 'a'
            if (!seen[idx]) {
                seen[idx] = true
                distinct++
            }

            val lenMod = (i + 1) % 3
            if (distinct == lenMod) {
                ans++
            }
        }
        return ans
    }
}