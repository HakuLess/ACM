package leetcode.contest.c477

class SolutionB {
    fun maxBalancedSubarray(nums: IntArray): Int {
        // prefix XOR
        var prefixXor = 0
        // (#odd - #even)
        var balance = 0
        var ans = 0

        val map = HashMap<Pair<Int, Int>, Int>()
        map[Pair(0, 0)] = -1

        for (i in nums.indices) {
            val x = nums[i]
            prefixXor = prefixXor xor x

            if ((x % 2) == 1) {
                balance++
            } else {
                balance--
            }

            val key = Pair(prefixXor, balance)

            if (map.containsKey(key)) {
                ans = maxOf(ans, i - map[key]!!)
            } else {
                map[key] = i
            }
        }

        return ans
    }
}