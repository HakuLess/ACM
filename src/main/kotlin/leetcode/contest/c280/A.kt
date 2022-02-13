package leetcode.contest.c280

class SolutionA {
    fun countOperations(num1: Int, num2: Int): Int {
        var a = num1
        var b = num2
        var ans = 0
        while (a != 0 && b != 0) {
            if (a >= b) a -= b
            else b -= a
            ans ++
        }
        return ans
    }
}