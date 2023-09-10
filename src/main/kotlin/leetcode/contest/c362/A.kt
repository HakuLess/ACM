package leetcode.contest.c362

class SolutionA {
    fun numberOfPoints(nums: List<List<Int>>): Int {
        return (1..100).count { c ->
            nums.any { c in it[0]..it[1] } }
    }
}