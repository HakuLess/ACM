package leetcode.contest.c274

class SolutionA {
    fun checkString(s: String): Boolean {
        var meet = false
        s.forEach {
            if (it == 'b') meet = true
            if (it == 'a' && meet) return false
        }
        return true
    }
}