package leetcode.contest.c473

class SolutionA {
    fun removeZeros(n: Long): Long {
        return n.toString().filter { it !='0' }.toLong()
    }
}