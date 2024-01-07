package leetcode.contest.c379

import utils.print
import kotlin.math.abs

fun main() {
    val s = SolutionB()
//    s.minMovesToCaptureTheQueen(1, 1, 1, 4, 1, 8).print()
    // 1
    s.minMovesToCaptureTheQueen(4, 3, 3, 4, 2, 5).print()
    // 2
    s.minMovesToCaptureTheQueen(4, 3, 3, 4, 5, 2).print()
    // 2
    s.minMovesToCaptureTheQueen(7, 8, 7, 7, 7, 3).print()
}

class SolutionB {
    fun minMovesToCaptureTheQueen(a: Int, b: Int, c: Int, d: Int, e: Int, f: Int): Int {
        if (e == a || f == b) {
            // 象碍事儿
            if (e == a && c == a && d in minOf(b, f)..maxOf(b, f)) {
                return 2
            }
            if (f == b && d == b && c in minOf(a, e)..maxOf(a, e)) {
                return 2
            }
            return 1
        }
        if (abs(e - c) == abs(f - d)) {
            // 车碍事儿
            if (abs(e - a) == abs(f - b) && (e - a) / (f - b) == (e - c) / (f - d) && a > minOf(c, e) && a < maxOf(
                    c,
                    e
                )
            ) {
                return 2
            }
            return 1
        }
        return 2
    }
}