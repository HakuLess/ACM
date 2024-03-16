package leetcode.contest.b126

import utils.print
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

fun main() {
    val s = SolutionC()
    s.minimizeStringValue("abcdefghijklmnopqrstuvwxy??").print()
}

class SolutionC {
    fun minimizeStringValue(s: String): String {
        val map = HashMap<Char, Int>()
        for (i in 'a'..'z') {
            map[i] = 0
        }
        val l = ArrayList<Char>()
        for (i in s.indices) {
            val c = s[i]
            if (c == '?') {

            } else {
                map[c] = map[c]!! + 1
            }
        }

        for (i in s.indices) {
            val c = s[i]
            if (c == '?') {
                val minV = map.values.minOrNull()!!
                val minK = map.filterKeys { map[it] == minV }.keys.minOrNull()!!
                map[minK] = map[minK]!! + 1
                l.add(minK)
            }
        }
        l.sort()
        val ans = StringBuilder()
        for (i in s.indices) {
            val c = s[i]
            if (c == '?') {
                ans.append(l.removeAt(0))
            } else {
                ans.append(c)
            }
        }
        return ans.toString()
    }
}