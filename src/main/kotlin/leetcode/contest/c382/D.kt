package leetcode.contest.c382

import utils.print
import utils.printInt
import kotlin.math.pow

fun main() {
    val s = SolutionD()
    // 3
//    s.minOrAfterOperations(intArrayOf(3, 5, 3, 2, 7), 2).print()
    // 2
    s.minOrAfterOperations(intArrayOf(7, 3, 15, 14, 2, 8), 4).print()
    // 15
//    s.minOrAfterOperations(intArrayOf(10, 7, 10, 3, 9, 14, 9, 4), 1).print()
    // 32768
//    s.minOrAfterOperations(intArrayOf(1073709056, 32768), 1).print()
}

// Not Finished
class SolutionD {
    fun minOrAfterOperations(nums: IntArray, k: Int): Int {
        val map = HashMap<Int, Int>()
        val numsCnt = HashMap<Int, Int>()
        val numsMap = HashMap<Int, ArrayList<Int>>()
        nums.forEach {
            numsCnt[it] = numsCnt.getOrDefault(it, 0) + 1
            it.toString(2).reversed().forEachIndexed { index, c ->
                if (c == '1') {
                    map[index] = map.getOrDefault(index, 0) + 1
                    numsMap[index] = numsMap.getOrDefault(index, arrayListOf<Int>())
                    numsMap[index]!!.add(it)
                }
            }
        }
//        map.printInt()
        var ans = 0
        var add = 0
        map.keys.sortedDescending().forEach {
            if (map[it]!! <= (k - add)) {
                numsMap[it]?.forEach {
                    println("$it: cur add is $add")
//                    numsCnt.printInt()
                    if (it in numsCnt.keys) {
                        add++
                        println("remove value $it")
                        it.toString(2).reversed().forEachIndexed { index, c ->
                            if (c == '1') {
                                map[index] = map.getOrDefault(index, 0) - 1
                                numsCnt[it] = numsCnt.getOrDefault(it, 0) - 1
                                if (numsCnt[it]!! <= 0) {
                                    numsCnt.remove(it)
                                }
                            }
                        }
                    }
                }
                println("remove $it")
                map.printInt()
            } else if (map.getOrDefault(it, 0) > 0) {
                ans += 2.0.pow(it).toInt()
            }
        }
        return ans
    }
}