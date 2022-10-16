package leetcode.contest.c315

class SolutionC {
    fun sumOfNumberAndReverse(num: Int): Boolean {
        for (i in 0..num) {
            if (i + i.toString().reversed().toInt() == num) {
                return true
            }
        }
        return false
    }
}