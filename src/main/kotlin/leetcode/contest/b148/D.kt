package leetcode.contest.b148

// Not Finished
class SolutionD {
    fun distanceSum(m: Int, n: Int, k: Int): Int {
        val totalCells = m.toLong() * n
        val totalWays = totalCells * (totalCells - 1) / 2 % MOD
        val rowSum = calculateDistanceSum(m, n)
        val colSum = calculateDistanceSum(n, m)
        val manhattanSum = (rowSum * n + colSum * m) % MOD
        val combination = combinationMod(totalCells - 2, (k - 2).toLong())
        return (manhattanSum * combination % MOD).toInt()
    }

    private fun calculateDistanceSum(length: Int, otherDimension: Int): Long {
        var sum: Long = 0
        for (i in 1 until length) {
            sum = (sum + i.toLong() * (length - i) % MOD) % MOD
        }
        return sum * otherDimension % MOD
    }

    private val MOD = 1000000007
    private fun combinationMod(a: Long, b: Long): Long {
        if (b > a || b < 0) return 0
        val numerator = factorialMod(a)
        val denominator = factorialMod(b) * factorialMod(a - b) % MOD
        return numerator * modInverse(denominator, MOD.toLong()) % MOD
    }

    private fun factorialMod(n: Long): Long {
        var result: Long = 1
        for (i in 2..n) {
            result = result * i % MOD
        }
        return result
    }

    private fun modInverse(base: Long, mod: Long): Long {
        return powerMod(base, mod - 2, mod)
    }

    private fun powerMod(base: Long, exp: Long, mod: Long): Long {
        var base = base
        var exp = exp
        var result: Long = 1
        while (exp > 0) {
            if (exp and 1 == 1L) result = result * base % mod
            base = base * base % mod
            exp = exp shr 1
        }
        return result
    }

}