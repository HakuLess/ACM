package leetcode.contest.b106

class SolutionA {
    fun isFascinating(n: Int): Boolean {
        return "$n${n * 2}${n * 3}".let {
            it.length == 9 && it.toHashSet().size == 9 && '0' !in it
        }
    }
}