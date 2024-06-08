package leetcode.contest.b132

class SolutionB {
    fun findWinningPlayer(skills: IntArray, k: Int): Int {

        var c = 0
        var cur = skills[0]
        var ans = 0
        for (i in 1 until skills.size) {
            if (cur > skills[i]) {
                c++
            } else {
                ans = i
                c = 1
                cur = skills[i]
            }
            if (c >= k) {
                return ans
            }
        }
        return ans
    }
}