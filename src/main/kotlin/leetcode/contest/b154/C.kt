package leetcode.contest.b154

class SolutionC {
    fun uniqueXorTriplets(nums: IntArray): Int {
        val set0 = HashSet<Int>()
        val set1 = HashSet<Int>()

        for (i in nums.indices) {
            for (j in i until nums.size) {
                val c = nums[i] xor nums[j]
                set0.add(c)
            }
        }

        set0.forEach { a ->
            nums.forEach { b ->
                set1.add(a xor b)
            }
        }
        return set1.size
    }
}