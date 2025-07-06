package leetcode.normal

import utils.print

fun main() {
    val s = Solution3304()
    println(s.kthCharacter(5))
}

class Solution3304 {
    fun kthCharacter(k: Int): Char {
        return 'a' + Integer.bitCount(k - 1)
    }
//    fun kthCharacter(k: Int): Char {
//        val sb = StringBuilder("a")
//        while (sb.length < k) {
//            val append = StringBuilder()
//            for (c in sb) {
//                if (c == 'z') {
//                    append.append('a')
//                } else {
//                    append.append(c + 1)
//                }
//            }
//            sb.append(append)
//        }
//        sb.toString().print()
//        return sb[k - 1]
//    }
}