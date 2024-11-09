package leetcode.contest.b143

class SolutionA {
    fun smallestNumber(n: Int, t: Int): Int {
        // 从 n 开始检查每个整数
        var current = n

        // 辅助函数：计算一个整数的各位数字乘积
        fun digitProduct(num: Int): Int {
            var product = 1
            var temp = num

            while (temp > 0) {
                val digit = temp % 10 // 获取当前最低位数字
                product *= digit // 更新乘积
                temp /= 10 // 去掉最低位数字
            }

            return product
        }

        while (true) {
            // 计算当前整数的各位数字乘积
            val product = digitProduct(current)

            // 检查乘积是否能被 t 整除
            if (product % t == 0) {
                return current // 返回满足条件的最小整数
            }

            current++ // 继续检查下一个整数
        }
    }
}