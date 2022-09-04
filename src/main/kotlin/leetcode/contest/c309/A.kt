package leetcode.contest.c309

class SolutionA {
    fun checkDistances(s: String, distance: IntArray): Boolean {
        return ('a'..'z').all { c ->
            if (c in s) {
                s.indexOfLast { c == it } - s.indexOfFirst { c == it } == distance[c - 'a'] + 1
            } else {
                true
            }
        }
    }
}