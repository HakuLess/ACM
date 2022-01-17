package leetcode.normal

class Solution1220 {
    fun countVowelPermutation(n: Int): Int {
        if (n == 0) return 0
        val mod = 1000000007L
        var a = 1L
        var e = 1L
        var i = 1L
        var o = 1L
        var u = 1L
        repeat(n - 1) {
            val a1 = e + i + u
            val e1 = a + i
            val i1 = e + o
            val o1 = i
            val u1 = i + o

            a = a1 % mod
            e = e1 % mod
            i = i1 % mod
            o = o1 % mod
            u = u1 % mod
        }
        return ((a + e + i + o + u) % mod).toInt()
    }
}