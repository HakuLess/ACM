package leetcode.contest.c281

class SolutionA {
    fun countEven(num: Int): Int {
        var ans = 0
        for (i in 1..num) {
            var cur = 0
            i.toString().forEach {
                cur += it - '0'
            }
            if (cur % 2 == 0) {
                ans++
            }
        }
        return ans
    }
}