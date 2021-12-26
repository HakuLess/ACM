package leetcode.contest.c273

class SolutionA {
    fun isSameAfterReversals(num: Int): Boolean {
        return num.toString().reversed().toInt().toString().reversed().toInt() == num
    }
}