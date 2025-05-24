package leetcode.contest.b118

class SolutionA {
    fun findWordsContaining(words: Array<String>, x: Char): List<Int> {
        val ans = ArrayList<Int>()
        for (i in words.indices) {
            if (words[i].contains(x)) {
                ans.add(i)
            }
        }
        return ans
    }
}