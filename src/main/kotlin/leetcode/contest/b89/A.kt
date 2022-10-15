package leetcode.contest.b89

import utils.print

fun main() {
    val s = SolutionA()
    s.countTime("?5:00").print()
    s.countTime("0?:0?").print()
    s.countTime("??:??").print()
}

class SolutionA {
    fun countTime(time: String): Int {
        val ans = HashSet<String>()
        for (a in '0'..'2') {
            val sb0 = StringBuilder(time)
            if (sb0[0] == '?') {
                sb0[0] = a
            }
            for (b in '0'..'9') {
                val sb1 = StringBuilder(sb0)
                if (sb1[1] == '?') {
                    sb1[1] = b
                }
                for (c in '0'..'6') {
                    val sb2 = StringBuilder(sb1)
                    if (sb2[3] == '?') {
                        sb2[3] = c
                    }
                    for (d in '0'..'9') {
                        val sb3 = StringBuilder(sb2)
                        if (sb3[4] == '?') {
                            sb3[4] = d
                        }
                        if (sb3.toString().take(2) in "00".."23" && sb3.toString().takeLast(2) in "00".."59") {
//                            sb3.toString().print()
                            ans.add(sb3.toString())
                        }
                    }
                }
            }
        }
        return ans.size
    }
}