package leetcode.contest.c416

class SolutionD {
    fun validSubstringCount(word1: String, word2: String): Long {
        val map = IntArray(26)
        word2.forEach {
            map[it - 'a']++
        }

        val curMap = IntArray(26)
        var ans = 0L
        var left = 0
        for (i in word1.indices) {
            val c = word1[i]
            curMap[c - 'a']++
            while ((0..25).all { curMap[it] >= map[it] }) {
                ans += 1L * (word1.length - i)
                val remove = word1[left]
                curMap[remove - 'a']--
                left++
            }
        }
        return ans
    }
}