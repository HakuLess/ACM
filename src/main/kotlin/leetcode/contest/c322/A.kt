package leetcode.contest.c322

class SolutionA {
    fun isCircularSentence(sentence: String): Boolean {
        "$sentence $sentence".split(" ").let {
            return (0 until it.lastIndex).all { i ->
                it[i].last() == it[i + 1].first()
            }
        }
    }
}