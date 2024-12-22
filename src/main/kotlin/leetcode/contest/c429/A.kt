package leetcode.contest.c429

class SolutionA {
    fun minimumOperations(nums: IntArray): Int {
        var operations = 0
        var array = nums.toMutableList()

        while (true) {
            val uniqueSet = mutableSetOf<Int>()
            var hasDuplicates = false

            for (num in array) {
                if (!uniqueSet.add(num)) {
                    hasDuplicates = true
                    break
                }
            }

            if (!hasDuplicates) break

            array = if (array.size <= 3) {
                mutableListOf()
            } else {
                array.subList(3, array.size)
            }

            operations++
        }

        return operations
    }
}