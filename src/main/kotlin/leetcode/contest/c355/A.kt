package leetcode.contest.c355

class SolutionA {
    fun splitWordsBySeparator(words: List<String>, separator: Char): List<String> {
        val ans = ArrayList<String>()
        words.forEach {
            ans.addAll(it.split(separator).filter { it.isNotEmpty() })
        }
        return ans
    }
}