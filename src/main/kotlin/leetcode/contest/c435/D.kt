package leetcode.contest.c435

import utils.print

fun main() {
    val s = SolutionD()
    // -1
    s.maxDifference("12233", 4).print()
//    // 1
    s.maxDifference("1122211", 3).print()
    // -1
    s.maxDifference("110", 3).print()
}

class SolutionD {
    fun maxDifference(s: String, k: Int): Int {

        fun helper(a: Int, b: Int): Int {

//            println("enter ==== $a $b")

            var ans = Int.MIN_VALUE

            for (left in s.indices) {
                var ac = 0
                var bc = 0
                for (right in left..s.lastIndex) {
                    val item = s[right] - '0'
                    if (item == a) {
                        ac++
                    } else if (item == b) {
                        bc++
                    }

                    if (right - left + 1 >= k) {
//                        println(" ==== $left..$right $ac - $bc == ${ac - bc} with $ans")

                        if (ac % 2 == 1 && bc % 2 == 0 && ac != 0 && bc != 0) {
//                            println("$left..$right $ac - $bc == ${ac - bc} with $ans")
                            ans = maxOf(ans, ac - bc)
                        }
                    }
                }
            }

            return ans
        }

        var ans = Int.MIN_VALUE
        for (a in 0..4) {
            for (b in 0..4) {
                if (a == b) continue
                ans = maxOf(ans, helper(a, b))
            }
        }

        return ans
    }
}