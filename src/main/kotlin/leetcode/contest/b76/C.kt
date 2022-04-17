package leetcode.contest.b76

class ATM() {

    // 20 50 100 200 500
    val cur = longArrayOf(0, 0, 0, 0, 0)
    val value = longArrayOf(20, 50, 100, 200, 500)

    fun deposit(banknotesCount: IntArray) {
        for (i in banknotesCount.indices) {
            cur[i] = cur[i] + banknotesCount[i]
        }
    }

    fun withdraw(amount: Int): IntArray {
        var left = amount.toLong()
        val ans = intArrayOf(0, 0, 0, 0, 0)
        for (i in cur.indices.reversed()) {
            if (cur[i] != 0L) {
                ans[i] = minOf(left / value[i], cur[i]).toInt()
                left -= ans[i] * value[i]
            }
        }
        if (left != 0L) return intArrayOf(-1)
        for (i in ans.indices) {
            cur[i] = cur[i] - ans[i]
        }
        return ans
    }

}

/**
 * Your ATM object will be instantiated and called as such:
 * var obj = ATM()
 * obj.deposit(banknotesCount)
 * var param_2 = obj.withdraw(amount)
 */