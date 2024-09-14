package leetcode.contest.b139

 class SolutionC {
    fun maxValue(nums: IntArray, k: Int): Int {
        val dp = Array(nums.size) { BooleanArray(128) }

        var isMet = Array(128) { BooleanArray(k + 1) }
        var valid: MutableList<IntArray> = ArrayList()
        valid.add(intArrayOf(0, 0))
        isMet[0][0] = true
        for (i in nums.indices.reversed()) {
            val nextValid: MutableList<IntArray> = ArrayList()
            for (tmp in valid) {
                nextValid.add(tmp)
                if (tmp[1] + 1 <= k && !isMet[tmp[0] or nums[i]][tmp[1] + 1]) {
                    isMet[tmp[0] or nums[i]][tmp[1] + 1] = true
                    nextValid.add(intArrayOf(tmp[0] or nums[i], tmp[1] + 1))
                }
                if (tmp[1] + 1 == k) {
                    dp[i][tmp[0] or nums[i]] = true
                }
                if (tmp[1] == k) {
                    dp[i][tmp[0]] = true
                }
            }
            valid = nextValid
        }
        var res = 0
        val isLeftMet = BooleanArray(128)
        isMet = Array(128) { BooleanArray(k + 1) }
        valid = ArrayList()
        valid.add(intArrayOf(0, 0))
        isMet[0][0] = true
        for (i in nums.indices) {
            val nextValid: MutableList<IntArray> = ArrayList()
            for (tmp in valid) {
                nextValid.add(tmp)
                if (tmp[1] + 1 <= k && !isMet[tmp[0] or nums[i]][tmp[1] + 1]) {
                    isMet[tmp[0] or nums[i]][tmp[1] + 1] = true
                    nextValid.add(intArrayOf(tmp[0] or nums[i], tmp[1] + 1))
                }
                if (tmp[1] + 1 == k && !isLeftMet[tmp[0] or nums[i]] && i + 1 < nums.size) {
                    val orResult = tmp[0] or nums[i]
                    isLeftMet[orResult] = true
                    for (j in 1 until 128) {
                        if (dp[i + 1][j]) {
                            res = maxOf(res, orResult xor j)
                        }
                    }
                }
            }
            valid = nextValid
        }
        return res
    }
}