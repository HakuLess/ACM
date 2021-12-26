package leetcode.contest.b68

import utils.print

fun main() {
    val s = SolutionD()
//    s.abbreviateProduct(1, 4).print()
//    s.abbreviateProduct(2, 11).print()
    s.abbreviateProduct(999998, 1000000).print()
    // 23510...78528e16317
//    s.abbreviateProduct(256, 65535).print()
//
//    // 81384...08512e17604
//    measureTimeMillis {
//        s.abbreviateProduct(410, 70833).print()
//    }.also {
//        it.print()
//    }
//    // 13112...05984e72481
//    s.abbreviateProduct(92764, 382694).print()
//
//    // "54295...90624e395"
//    s.abbreviateProduct(144, 1725).print()

    // 16231...98048e2499
    s.abbreviateProduct(990000, 999999).print()

    // "47396...47744e106358"
//    s.abbreviateProduct(80952, 506382).print()
}

class SolutionD {
    fun abbreviateProduct(left: Int, right: Int): String {

        var c0 = 0L
        var cur = 1L
        for (i in left..right) {
            cur *= i
            while (cur != 0L && cur % 10 == 0L) {
                cur /= 10L
                c0++
            }
            if (cur.toString().length > 16) {
                cur = 0L
                break
            }
        }

        if (cur != 0L) {
            val ans = cur.toString()
            for (i in ans.indices.reversed()) {
                if (ans[i] == '0') c0++
                else {
                    if (i < 10) {
                        return "${ans.substring(0, i + 1)}e${c0}"
                    } else {
                        return "${ans.substring(0, 5)}...${ans.substring(ans.length - 5, ans.length)}e${c0}"
                    }
                }
            }
        }

        var l5 = 1L
        var r5 = 1L
        var c2 = 0
        var c5 = 0
        var c10 = 0

        for (i in left..right) {
            l5 *= i
            if (l5.toString().length > 12)
                l5 = l5.toString().substring(0, 12).toLong()

            r5 *= i
            while (r5 % 10 == 0L) {
                r5 /= 10
            }
            if (r5.toString().length > 7)
                r5 = r5.toString().substring(r5.toString().length - 7, r5.toString().length).toLong()

            var cur = i
            while (cur % 10 == 0) {
                c10++
                cur /= 10
            }
            while (cur % 2 == 0) {
                c2++
                cur /= 2
            }
            while (cur % 5 == 0) {
                c5++
                cur /= 5
            }
        }

        return "${l5.toString().substring(0, 5)}...${
            r5.toString().substring(r5.toString().length - 5)
        }e${c10 + minOf(c2, c5)}"
    }
}