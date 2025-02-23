package leetcode.contest.c438

class SolutionC {
    fun hasSameDigits(s: String): Boolean {
        val m = s.length
        val k = m - 2
        if (k < 0) return false

        val combTable = arrayOf(
            intArrayOf(1, 0, 0, 0, 0),
            intArrayOf(1, 1, 0, 0, 0),
            intArrayOf(1, 2, 1, 0, 0),
            intArrayOf(1, 3, 3, 1, 0),
            intArrayOf(1, 4, 1, 4, 1)
        )

        fun combMod5(k: Int, i: Int): Int {
            var kVar = k
            var iVar = i
            var res = 1
            while (kVar > 0 || iVar > 0) {
                val d = kVar % 5
                val e = iVar % 5
                if (e > d) return 0
                res = (res * combTable[d][e]) % 5
                kVar /= 5
                iVar /= 5
            }
            return res
        }

        val crt = mapOf(
            Pair(0, 0) to 0,
            Pair(0, 1) to 6,
            Pair(0, 2) to 2,
            Pair(0, 3) to 8,
            Pair(0, 4) to 4,
            Pair(1, 0) to 5,
            Pair(1, 1) to 1,
            Pair(1, 2) to 7,
            Pair(1, 3) to 3,
            Pair(1, 4) to 9
        )

        val c = IntArray(k + 1)
        for (i in 0..k) {
            val mod2 = if ((i and k) == i) 1 else 0
            val mod5 = combMod5(k, i)
            c[i] = crt[Pair(mod2, mod5)] ?: 0
        }

        var sum1 = 0
        var sum2 = 0
        for (i in 0..k) {
            val digit1 = s[i] - '0'
            sum1 = (sum1 + digit1 * c[i]) % 10
            val digit2 = s[i + 1] - '0'
            sum2 = (sum2 + digit2 * c[i]) % 10
        }

        return sum1 == sum2
    }
}