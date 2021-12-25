package leetcode.contest.b68

class SolutionA {
    fun mostWordsFound(sentences: Array<String>): Int {
        return sentences.map { it.split(" ").size }.maxOrNull()!!
    }
}