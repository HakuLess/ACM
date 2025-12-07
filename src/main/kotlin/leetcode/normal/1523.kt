package leetcode.normal

class Solution1523 {
    fun countOdds(low: Int, high: Int): Int {
        val a = low % 2 == 0
        val b = high % 2 == 0
        val offset = if (a && b) {
            0
        } else {
            1
        }
        return (high - low) / 2 + offset
    }
}