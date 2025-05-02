package leetcode.normal

import utils.print
import java.util.*

fun main() {
    val s = Solution838()
    s.pushDominoes("R.R.L").print()
}

// TODO 可以多源BFS处理，外部变量记录受力方向 && 是否抵消，每次遍历为时间维度
class Solution838 {
    fun pushDominoes(dominoes: String): String {
        val ans = dominoes.toCharArray()
        val tm = TreeMap<Int, Char>()
        for (i in dominoes.indices) {
            if (dominoes[i] == '.') {

            } else if (dominoes[i] == 'L') {
                tm[i] = 'L'
            } else {
                tm[i] = 'R'
            }
        }

        for (i in ans.indices) {
            if (ans[i] == '.') {
                val l = tm.lowerEntry(i)
                val r = tm.higherEntry(i)

                println("$i : $l $r ")
                if (l == null && r == null) {

                } else if (l == null) {
                    if (r.value == 'L') {
                        ans[i] = r.value
                    }
                } else if (r == null) {
                    if (l.value == 'R') {
                        ans[i] = l.value
                    }
                } else {
                    if (l.value == 'R' && r.value == 'L') {
                        if ((i - l.key) < (r.key - i)) {
                            ans[i] = 'R'
                        } else if ((i - l.key) > (r.key - i)) {
                            ans[i] = 'L'
                        }
                    } else if (l.value == r.value) {
                        ans[i] = l.value
                    }
                }
            }
        }

        return ans.joinToString("")
    }
}