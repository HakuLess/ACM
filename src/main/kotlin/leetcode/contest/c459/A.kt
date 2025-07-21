package leetcode.contest.c459

class SolutionA {
    fun checkDivisibility(n: Int): Boolean {
        val a = n.toString().sumOf { it - '0' }
        var b = 1
        n.toString().forEach {
            b *= (it - '0')
        }
        return n % (a + b) == 0
    }
}