package leetcode.contest.c285

import utils.print

fun main() {
    val s = SolutionB()
//    s.countCollisions("RLRSLL").print()
//    s.countCollisions("SSRSSRLLRSLLRSRSSRLRRRRLLRRLSSRR").print()
    s.countCollisions("LSSSLLSSSSLRRSLLLSLSLRRLLLLLRSSRSRRSLLLSSS").print()
}

class SolutionB {
    fun countCollisions(directions: String): Int {
        var r = 0
        var s = 0
        var ans = 0
        for (i in directions.indices) {
            when (directions[i]) {
                'L' -> {
                    if (r != 0) {
                        ans += r + 1
                        r = 0
                        s = 1
                    } else if (s != 0) {
                        ans += 1
                    }
                }
                'R' -> {
                    r++
                }
                'S' -> {
                    if (r != 0) {
                        ans += r
                        s = r
                        r = 0
                    } else {
                        s++
                    }
                }
            }
            println("$i ${directions[i]}: $ans")
        }
        return ans
    }
}