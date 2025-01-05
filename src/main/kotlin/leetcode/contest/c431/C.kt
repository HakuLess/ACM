package leetcode.contest.c431

import utils.print
import utils.printLong
import utils.toGrid
import java.util.*

fun main() {
    val s = SolutionC()
    s.maximumCoins("[[8,10,1],[1,3,2],[5,6,4]]".toGrid(), 4).print()
    // 226
    s.maximumCoins("[[8,12,13],[29,32,2],[13,15,2],[40,41,18],[42,48,18],[33,36,11],[37,38,6]]".toGrid(), 28).print()
}

// TODO Not Finish
class SolutionC {
    fun maximumCoins(coins: Array<IntArray>, k: Int): Long {

        // 排序区间
        coins.sortBy { it[0] }

        val tm = TreeMap<Int, Long>()
        var curCoins = 0L
        coins.forEach {
            val (start, end, cnt) = it
            tm[start - 1] = curCoins
            curCoins += (end - start + 1L) * cnt
            tm[end] = curCoins
        }
        tm[Int.MIN_VALUE] = 0
        tm[Int.MAX_VALUE] = curCoins

//        tm.printLong()

        var ans = 0L
        for (i in coins.indices) {
            // 以 start 为起点
            val start = coins[i][0]
            val end = start + k - 1

            // start - 1之前拥有的硬币
            val lst = tm.floorEntry(start - 1)?.value ?: 0L

            val endLeft = tm.floorKey(end)
            val endRight = tm.ceilingKey(end)

//            println("check $end for $endLeft $endRight")
//            println("check ${tm[endLeft]!!}..${tm[endRight]!!}")
            val sum = if (endLeft == endRight) {
                tm[endLeft]!!
            } else {
                tm[endLeft]!! + (tm[endRight]!! - tm[endLeft]!!) / (endLeft - endRight + 1) * (end - endLeft)
            }

//            println("以 $start 为起点  $end 为终点 当前 sum is $sum  lst is $lst")
            ans = maxOf(ans, sum - lst)
        }

        for (i in coins.indices) {
            // 以 end 为终点
            val end = coins[i][1]
            val start = end - k + 1

            // start - 1之前拥有的硬币
            val sum = tm[end]!!

            val startLeft = tm.floorKey(start)
            val startRight = tm.ceilingKey(start)

//            println("check $end for $endLeft $endRight")
//            println("check ${tm[endLeft]!!}..${tm[endRight]!!}")
            val lst = if (startLeft == startRight) {
                tm[startLeft]!!
            } else {
                tm[startLeft]!! + (tm[startRight]!! - tm[startLeft]!!) / (startRight - startLeft + 1) * (start - startLeft)
            }

//            println("以 $start 为起点  $end 为终点 当前 sum is $sum  lst is $lst")
            ans = maxOf(ans, sum - lst)
        }

        return ans
    }
}