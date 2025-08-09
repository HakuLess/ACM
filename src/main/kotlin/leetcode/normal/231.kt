package leetcode.normal

class Solution231 {
    fun isPowerOfTwo(n: Int): Boolean {
        return n > 0 && n and (n - 1) == 0
    }

//    fun isPowerOfTwo(n: Int): Boolean {
//        if (n < 0) return false
//        return n.countOneBits() == 1
//    }
}