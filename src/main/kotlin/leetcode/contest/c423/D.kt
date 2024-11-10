package leetcode.contest.c423

class SolutionD {
    fun countKReducibleNumbers(s: String, k: Int): Int {
        val MOD = 1_000_000_007
        val n = s.toLong(2) // 将二进制字符串 s 转换为整数
        val memo = mutableMapOf<Pair<Int, Int>, Boolean>()

        // 检查数字 x 是否可以在 k 次或更少的操作内被约简到 1
        fun isKReducible(x: Int, remainingSteps: Int): Boolean {
            if (x == 1) return true
            if (remainingSteps == 0) return false

            val key = Pair(x, remainingSteps)
            if (key in memo) return memo[key]!!

            val next = Integer.bitCount(x) // 计算二进制表示中的置位数
            val result = isKReducible(next, remainingSteps - 1)
            memo[key] = result
            return result
        }

        // 计数符合条件的整数
        var count = 0
        for (i in 1 until n) {
            if (isKReducible(i.toInt(), k)) {
                count = (count + 1) % MOD
            }
        }

        return count
    }
}