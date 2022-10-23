package leetcode.contest.c316

class SolutionA {
    fun haveConflict(event1: Array<String>, event2: Array<String>): Boolean {
        return !(event1[0] > event2[1] || event1[1] < event2[0])
    }
}