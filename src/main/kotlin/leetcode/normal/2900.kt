package leetcode.normal

class Solution2900 {
    fun getLongestSubsequence(words: Array<String>, groups: IntArray): List<String> {
        val l = ArrayList<Int>()
        for (i in groups.indices) {
            if (i > 0 && groups[i] != groups[i - 1]) {
                l.add(i)
            } else if (i == 0) {
                l.add(i)
            }
        }
        return l.map { words[it] }
    }
}