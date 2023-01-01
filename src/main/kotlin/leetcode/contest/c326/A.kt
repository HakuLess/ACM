package leetcode.contest.c326

class SolutionA {
    fun countDigits(num: Int): Int {
        var ans = 0
        num.toString().forEach {
            val c = it - '0'
            if (num % c == 0) ans++
        }
        return ans
    }
}