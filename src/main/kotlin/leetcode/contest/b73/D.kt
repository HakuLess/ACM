package leetcode.contest.b73

import utils.print
import utils.swap
import kotlin.collections.ArrayList

fun main() {
    val s = SolutionD()
//    s.minMovesToMakePalindrome("aabb").print()
//    s.minMovesToMakePalindrome("letelt").print()
    // 42
    s.minMovesToMakePalindrome("scpcyxprxxsjyjrww").print()
}

class SolutionD {
    fun minMovesToMakePalindrome(s: String): Int {
        val n = s.length
        var l = 0
        val sb = ArrayList<Char>()
        sb.addAll(s.toCharArray().toList())
        var ans = 0
        while (l < n / 2) {
            var r = s.lastIndex - l
            var tmp = 0
            while (sb[l] != sb[r]) {
                r--
                tmp++
            }
            if (l == r) {
                sb.swap(l, l + 1)
                ans++
            } else {
                sb.removeAt(r)
                sb.add(sb[l])
                ans += tmp
                l++
            }
        }
        return ans
    }
}