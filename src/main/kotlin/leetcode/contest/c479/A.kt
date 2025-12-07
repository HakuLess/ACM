package leetcode.contest.c479

class SolutionA {
    fun sortByReflection(nums: IntArray): IntArray {
        return nums.sortedWith(
            compareBy(
                {
                    it.toString(2).reversed().toInt(2)
                },
                { it })
        ).toIntArray()
    }
}