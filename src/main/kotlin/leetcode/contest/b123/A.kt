package leetcode.contest.b123

class SolutionA {
    fun triangleType(nums: IntArray): String {
        val st = nums.sorted()
        if (st[0] + st[1] <= st[2]) return "none"
        return when (nums.toHashSet().size) {
            1 -> "equilateral"
            2 -> "isosceles"
            else -> "scalene"
        }
    }
}