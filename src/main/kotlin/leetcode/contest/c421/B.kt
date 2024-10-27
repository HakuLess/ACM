package leetcode.contest.c421

class SolutionB {
    fun lengthAfterTransformations(s: String, t: Int): Int {
        val mod = 1_000_000_007L
        var ans = s.length.toLong()

        var arr = LongArray(26)
        s.forEach {
            arr[it - 'a']++
        }

        repeat(t) {
            ans += arr[25]
            val new = LongArray(26)
            new[0] = arr[25] % mod
            new[1] = (arr[0] + arr[25]) % mod
            for (i in 2 until 26) {
                new[i] = arr[i - 1] % mod
            }
            ans %= mod
            arr = new
        }

        return ans.toInt()
    }
}