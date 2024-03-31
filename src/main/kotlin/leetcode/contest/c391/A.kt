package leetcode.contest.c391

class SolutionA {
    fun sumOfTheDigitsOfHarshadNumber(x: Int): Int {
        var div = 0
        var cur = x
        while (cur > 0) {
            div += cur % 10
            cur /= 10
        }
        if (x % div == 0) {
            return div
        }
        return -1
    }
}