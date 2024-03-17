package leetcode.contest.c389

import utils.SortedList
import utils.print

fun main() {
    val s = SolutionD()
    // 3
//    s.minimumMoves(intArrayOf(1, 1, 0, 0, 0, 1, 1, 0, 0, 1), 3, 1).print()
//
//    s.minimumMoves(intArrayOf(0, 0, 0, 0), 2, 3).print()
//    s.minimumMoves(intArrayOf(0, 1), 1, 0).print()
//    s.minimumMoves(intArrayOf(0, 1), 1, 2).print()
//    // 2
//    s.minimumMoves(intArrayOf(0, 1), 2, 1).print()
//
//    // 2
//    s.minimumMoves(intArrayOf(0, 1), 2, 5).print()
//
//    // 1
//    s.minimumMoves(intArrayOf(1, 1), 2, 4).print()
//
//    // 3
//    s.minimumMoves(intArrayOf(1, 1), 3, 2).print()
//    // 5
//    s.minimumMoves(intArrayOf(1, 1), 4, 2).print()
//    // 4
//    s.minimumMoves(intArrayOf(1, 1, 1), 4, 3).print()
//    // 8
//    s.minimumMoves(intArrayOf(1, 1, 1), 6, 4).print()
//
//    // 1
//    s.minimumMoves(intArrayOf(1, 0, 1, 1), 2, 0).print()

    // 3
    s.minimumMoves(intArrayOf(0, 1, 1), 3, 2).print()
}

class SolutionD {
    fun minimumMoves(nums: IntArray, k: Int, maxChanges: Int): Long {

        if (k == 1) {
            if (1 in nums) return 0
            else return 2
        }

        var c1 = 0
        var tmp1 = 0
        for (i in nums.indices) {
            if (nums[i] == 1) {
                tmp1++
                c1 = maxOf(c1, tmp1)
            } else {
                tmp1 = 0
            }
        }

        val need = maxOf(0, k - maxChanges)

        val offset = if (c1 >= 3 && k > 2) {
            2
        } else if (c1 >= 2 && k > 1) {
            1
        } else {
            0
        }

//        println("need is $need")
//        println("offset is $offset")

        // 全都可用最近替换
        if (need == 0) {
            if (1 in nums) {
                return (k - 1) * 2L - offset
            } else {
                return k * 2L
            }
        }


        var ans = 0L
        ans += maxChanges * 2L
        if (offset == 1 && k - 2 < maxChanges) {
            ans--
        }
        if (offset == 2 && k - 3 < maxChanges) {
            if (maxChanges - (k - 3) == 1) {
                ans--
            } else {
                ans -= 2
            }
        }

//        println("ans is $ans")

        var tmp = Long.MAX_VALUE

        val left = ArrayList<Int>()
        val right = ArrayList<Int>()

        var leftSum = 0L
        var rightSum = 0L

        val leftLen = need / 2
        val rightLen = need - need / 2

//        println("need $leftLen $rightLen")

        for (i in nums.indices) {
            if (nums[i] != 1) continue

            if (left.size < leftLen) {
                left.add(i)
                leftSum += i
            } else if (right.size < rightLen) {
                right.add(i)
                rightSum += i

//                println("rightSum is $rightSum")
            }

//            println("$i: ${left.joinToString()} with ${right.joinToString()}")

            // 处理当前数据
            if (right.size == rightLen) {
                val mid = right[0]
                val leftDis = maxOf(0, 1L * mid * leftLen - leftSum)
                val rightDis = rightSum - 1L * mid * rightLen

//                println("$i: leftSum is $leftSum with ${mid} * ${leftLen}")
//                println("$i: rightSum is $rightSum with ${mid} * ${rightLen}")

                tmp = minOf(tmp, leftDis + rightDis)

//                println("$i: minTmp is $tmp  = $leftDis + $rightDis")

                // 更换
                if (left.isNotEmpty()) {
                    leftSum -= left.removeAt(0)
                }
                if (right.isNotEmpty()) {
                    leftSum += right[0]
                    rightSum -= right[0]
                    left.add(right.removeAt(0))
                }
            }
        }

        return ans + tmp
    }
}