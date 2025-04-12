package leetcode.contest.b154

class SolutionC {
    fun uniqueXorTriplets(nums: IntArray): Int {
        val set = HashSet<Int>()
        for (i in nums.indices) {
            for (j in i until nums.size) {
                for (k in j until nums.size) {
                    val c = nums[i] xor nums[j] xor nums[k]
                    set.add(c)
                }
            }
        }
        return set.size
    }
}