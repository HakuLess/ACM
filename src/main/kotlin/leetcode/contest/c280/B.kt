package leetcode.contest.c280

class SolutionB {
    fun minimumOperations(nums: IntArray): Int {
        val mapA = HashMap<Int, Int>()
        val mapB = HashMap<Int, Int>()
        for (i in nums.indices) {
            if (i % 2 == 0) {
                mapA[nums[i]] = mapA.getOrDefault(nums[i], 0) + 1
            } else {
                mapB[nums[i]] = mapB.getOrDefault(nums[i], 0) + 1
            }
        }

        var a0 = Pair(-1, 0)
        var a1 = Pair(-1, 0)
        mapA.forEach { t, u ->
            if (u > a0.second) {
                a1 = a0
                a0 = Pair(t, u)
            } else if (u > a1.second) {
                a1 = Pair(t, u)
            }
        }
        var b0 = Pair(-1, 0)
        var b1 = Pair(-1, 0)
        mapB.forEach { t, u ->
            if (u > b0.second) {
                b1 = b0
                b0 = Pair(t, u)
            } else if (u > b1.second) {
                b1 = Pair(t, u)
            }
        }

        var ans = nums.size
        if (a0.first != b0.first) {
            ans -= a0.second
            ans -= b0.second
        } else {
            ans = minOf(ans - a1.second - b0.second, ans - a0.second - b1.second)
        }
        return ans
    }
}