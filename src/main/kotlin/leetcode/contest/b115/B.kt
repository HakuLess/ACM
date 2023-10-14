package leetcode.contest.b115

class SolutionB {
    fun getWordsInLongestSubsequence(n: Int, words: Array<String>, groups: IntArray): List<String> {
        var lst = -1
        val ids = HashSet<Int>()
        for (i in groups.indices) {
            if (groups[i] != lst) {
                lst = groups[i]
                ids.add(i)
            }
        }
        return words.filterIndexed { index, s ->
            index in ids
        }
    }
}