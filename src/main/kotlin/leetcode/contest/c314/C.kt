package leetcode.contest.c314

import utils.print
import java.util.*

fun main() {
    val s = SolutionC()
//    s.robotWithString("zza").print()
//    s.robotWithString("bac").print()
//    s.robotWithString("bdda").print()
    // "bdevfziy"
//    s.robotWithString("bydizfve").print()
    // "fnohopzv"
    s.robotWithString("vzhofnpo").print()
    // "biknrqw"
    s.robotWithString("ibwqrkn").print()
}

class SolutionC {
    fun robotWithString(s: String): String {
        val min = CharArray(s.length) { 'z' }
        for (i in s.lastIndex downTo 1) {
            min[i - 1] = minOf(s[i], min[i])
        }
        val sb = StringBuilder()
        val st = Stack<Char>()
        for (i in s.indices) {
            st.push(s[i])
            while (st.isNotEmpty() && st.peek() <= min[i]) {
                sb.append(st.pop())
            }
        }
        while (st.isNotEmpty()) {
            sb.append(st.pop())
        }
        return sb.toString()
    }
}