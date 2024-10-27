package leetcode.contest.c421

class SolutionA {
    fun maxScore(nums: IntArray): Long {

        // 辅助函数计算 gcd
        fun gcd(a: Long, b: Long): Long {
            return if (b == 0L) a else gcd(b, a % b)
        }

        // 辅助函数计算 lcm
        fun lcm(a: Long, b: Long): Long {
            return a / gcd(a, b) * b
        }

        // 计算一个数组的 gcd
        fun arrayGcd(nums: List<Long>): Long {
            return nums.reduce { acc, num -> gcd(acc, num) }
        }

        // 计算一个数组的 lcm
        fun arrayLcm(nums: List<Long>): Long {
            return nums.reduce { acc, num -> lcm(acc, num) }
        }

        // 计算整个数组的 GCD 和 LCM 的乘积（不移除元素）
        val totalGcd = arrayGcd(nums.map { it.toLong() }.toList())
        val totalLcm = arrayLcm(nums.map { it.toLong() }.toList())
        var maxScore: Long = 1L * totalGcd * totalLcm

        // 尝试移除每一个元素，计算最大因子得分
        for (i in nums.indices) {
            // 移除 nums[i] 后剩下的数组
            val remaining = nums.map { it.toLong() }.toMutableList().apply { removeAt(i) }
            if (remaining.isNotEmpty()) {
                val currentGcd = arrayGcd(remaining)
                val currentLcm = arrayLcm(remaining)
                maxScore = maxOf(maxScore, 1L * currentGcd * currentLcm)
            }
        }

        return maxScore
    }
}