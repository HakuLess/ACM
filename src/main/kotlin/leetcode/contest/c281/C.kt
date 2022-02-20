package leetcode.contest.c281

import utils.print

fun main() {
    val s = SolutionC()
    s.repeatLimitedString("cczazcc", 3).print()
    s.repeatLimitedString("aababab", 2).print()
}

class SolutionC {
    fun repeatLimitedString(s: String, repeatLimit: Int): String {
        val arr = IntArray(26)
        s.forEach {
            arr[it - 'a']++
        }
        var repeat = 0
        var repeatChar = -1
        val ans = StringBuilder()
        while (true) {
            var add = false
            for (i in 25 downTo 0) {
                if (arr[i] > 0 && (repeatChar != i || repeat != repeatLimit)) {
                    add = true
                    ans.append('a' + i)
                    arr[i]--
                    if (repeatChar == i) {
                        repeat++
                    } else {
                        repeatChar = i
                        repeat = 1
                    }
                    break
                }
            }
            if (!add) break
        }
        return ans.toString()
    }
}