package leetcode.contest.c274

class SolutionB {
    fun numberOfBeams(bank: Array<String>): Int {
        var lst = 0
        var cur = 0
        var ans = 0
        bank.map { it.count { it == '1' } }.filter { it != 0 }.forEach {
            lst = cur
            cur = it
            ans += lst * cur
        }
        return ans
    }
}