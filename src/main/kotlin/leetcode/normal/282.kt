package leetcode.normal

import utils.eval
import utils.print

fun main() {
    val s = Solution282()
//    s.addOperators("105", 5).joinToString().print()
    s.addOperators("2147483648", -2147483648).joinToString().print()
    eval("2147483648").print()
//    eval("1*2*3*4*5-6+78-9").print()
}

class Solution282 {
    fun addOperators(num: String, target: Int): List<String> {
        val ans = ArrayList<String>()
        fun dfs(index: Int, sb: StringBuilder) {
            sb.append(num[index])
            if (index != num.lastIndex) {
                val next0 = StringBuilder(sb)
                val next1 = StringBuilder(sb)
                val next2 = StringBuilder(sb)
                next0.append('*')
                dfs(index + 1, next0)
                next1.append('+')
                dfs(index + 1, next1)
                next2.append('-')
                dfs(index + 1, next2)
                dfs(index + 1, sb)
            } else {
                if (sb.toString().split('*', '+', '-').any { it.length != 1 && it.startsWith('0') }) {
                    // 0开头的且长度不为1的，不接受
                } else if (eval(sb.toString()) == target.toLong()) {
                    ans.add(sb.toString())
                }
            }
        }
        dfs(0, StringBuilder())
        return ans
    }
}