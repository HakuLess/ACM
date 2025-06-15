package leetcode.normal

class Solution440 {
    fun findKthNumber(n: Int, k: Int): Int {
        var prefix = 1
        var kLeft = k - 1

        fun getCount(prefix: Long, n: Int): Long {
            var cur = prefix
            var next = prefix + 1
            var count = 0L
            while (cur <= n) {
                count += minOf(n + 1L, next) - cur
                cur *= 10
                next *= 10
            }
            return count
        }

        while (kLeft > 0) {
            val count = getCount(prefix.toLong(), n)
            if (kLeft >= count) {
                prefix++
                kLeft -= count.toInt()
            } else {
                prefix *= 10
                kLeft--
            }
        }

        return prefix
    }
}