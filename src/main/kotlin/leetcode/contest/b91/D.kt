package leetcode.contest.b91

import utils.biMax
import utils.print
import java.util.*
import kotlin.collections.ArrayList

fun main() {
    val s = SolutionD()
    s.splitMessage("this is really a very awesome message", 9).joinToString().print()
    s.splitMessage("boxpn", 5).joinToString().print()
}

class SolutionD {
    fun splitMessage(message: String, limit: Int): Array<String> {
        var res = ArrayList<String>()
        // 尝试分割为N份
        fun sp(c: Int): Boolean {
            val st = Stack<Char>()
            val ans = ArrayList<String>()
            st.addAll(message.toCharArray().toList().reversed())
            for (i in 0 until c) {
                if (st.isEmpty()) return false
                val cur = StringBuilder()
                cur.append("<${i + 1}/$c>")
                if (cur.length > limit) {
                    return false
                }
                while (cur.length < limit && st.isNotEmpty()) {
                    cur.append(st.pop())
                }
                if (cur.length < limit && i != c - 1) {
                    return false
                }
                ans.add(cur.toString())
            }

            if (res.isEmpty() || res.size < ans.size) {
                res = ans
            }
            return true
        }
        biMax(l = 1L, r = 10000L) {
            sp(it.toInt())
        }

        val check = res.joinToString("").filter { it in 'a'..'z' || it == ' ' }
        if (check != message) return emptyArray()
        res.map { it.split('<') }
        return res.toTypedArray().map {
            val (a, b) = it.split('>').toList()
            "$b$a>"
        }.toTypedArray()
    }
}