package leetcode.b59

import kotlin.math.abs

class Solution5834 {
    fun minTimeToType(word: String): Int {
        var cur = 'a'
        var ans = 0
        word.forEach {
            val diff = abs(it - cur)
            ans += minOf(diff, 26 - diff)
            cur = it
        }
        return ans + word.length
    }
}