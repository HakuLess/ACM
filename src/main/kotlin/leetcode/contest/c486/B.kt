package leetcode.contest.c486

class SolutionB {
    fun rotateElements(nums: IntArray, k: Int): IntArray {

        // 收集非负数
        val list = ArrayList<Int>()
        for (v in nums) {
            if (v >= 0) {
                list.add(v)
            }
        }

        val m = list.size
        if (m <= 1) return nums

        // 只在非负数内进行 rotate
        val shift = k % m
        val rotated = ArrayList<Int>(m)
        for (i in 0 until m) {
            rotated.add(list[(i + shift) % m])
        }

        var idx = 0
        for (i in nums.indices) {
            if (nums[i] >= 0) {
                nums[i] = rotated[idx++]
            }
        }

        return nums
    }
}