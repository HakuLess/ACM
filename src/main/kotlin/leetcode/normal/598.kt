package leetcode.normal

class Solution598 {
    fun maxCount(m: Int, n: Int, ops: Array<IntArray>): Int {
        var a = m
        var b = n
        ops.forEach {
            a = minOf(a, it[0])
            b = minOf(b, it[1])
        }
        return a * b
    }
}