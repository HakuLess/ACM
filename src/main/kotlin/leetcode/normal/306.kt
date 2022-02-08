package leetcode.normal

import utils.print

fun main() {
    val s = Solution306()
    s.isAdditiveNumber("112358").print()
//    s.isAdditiveNumber("199111992").print()
//    s.isAdditiveNumber("12").print()
}

class Solution306 {
    fun isAdditiveNumber(num: String): Boolean {
        if (num == "000") return true
        fun check(a: Long, b: Long): Boolean {
            var x = a
            var y = b
            var cur = "$a$b"
            while (cur.length < num.length) {
                cur += "${x + y}"
                y = x + y
                x = y - x
            }
            if (cur == num) return true
            return false
        }

        val l = num.length / 2
        for (i in 1..l) {
            for (j in i + 1 until num.length) {
                if (num[j] != '0')
                    if (check(num.substring(0, i).toLong(), num.substring(i, j).toLong())) return true
            }
        }
        return false
    }
}