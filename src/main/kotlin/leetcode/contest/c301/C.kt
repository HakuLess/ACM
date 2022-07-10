package leetcode.contest.c301

import utils.print

fun main() {
    val s = SolutionC()
    s.canChange("_L__R__R_", "L______RR").print()
}

class SolutionC {
    fun canChange(start: String, target: String): Boolean {
        if (start.filter { it != '_' } != target.filter { it != '_' }) return false
        var i = 0
        var j = 0
        while (i in start.indices && j in target.indices) {
            // 找到非_
            while (i in start.indices && start[i] == '_') {
                i++
            }
            while (j in target.indices && target[j] == '_') {
                j++
            }
            println("$i $j: ${start[i]} ${target[j]}")
            if (i !in target.indices || j !in target.indices) break
            if (start[i] != target[j]) return false
            if (start[i] == 'L' && j > i) return false
            if (start[i] == 'R' && j < i) return false
            i++
            j++
        }
        return true
    }
}