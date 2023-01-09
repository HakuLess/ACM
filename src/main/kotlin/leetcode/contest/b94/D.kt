package leetcode.contest.b94

import utils.longComb
import utils.print

fun main() {
    val s = SolutionD()
    s.countAnagrams("too hot").print()
    // 210324488
    s.countAnagrams("b okzojaporykbmq tybq zrztwlolvcyumcsq jjuowpp").print()

}

class SolutionD {
    fun countAnagrams(s: String): Int {
        val mod = 1000000007L
        var ans = 1L
        s.split(" ").forEach {
            val cur = IntArray(26)
            it.forEach {
                cur[it - 'a']++
            }
            var tmp = 1L
            var l = it.length.toLong()
            cur.forEach {
                tmp *= longComb(l, it.toLong())
                l -= it
                tmp %= mod
            }
            ans *= tmp
            ans %= mod
        }
        return ans.toInt()
    }
}