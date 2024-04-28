package leetcode.contest.c395

import utils.print

fun main() {
    val s = SolutionC()
    // 6
    s.minEnd(3, 4).print()
    // 15
    s.minEnd(2, 7).print()
    // 55012476815
    s.minEnd(6715154, 7193485).print()
    // 2457406996159
    s.minEnd(74993882, 13792951).print()

}

class SolutionC {
    fun minEnd(n: Int, x: Int): Long {
        val str = x.toLong().toString(2).padStart(50, '0')

//        str.print()

        val map = HashMap<Long, Int>()
        var c = 1L
        for (i in str.indices.reversed()) {
            // 是0可以补1，1只能维持1
            if (str[i] == '0') {
                map[c] = i
                c *= 2
            }
        }

//        map.toSortedMap().forEach { t, u ->
//            println("$t with $u")
//        }

        val sb = StringBuilder(str)
        var cur = n.toLong()
        map.keys.sortedDescending().forEach {
            if (cur > it) {
                cur -= it
                sb[map[it]!!] = '1'
            }
        }

//        sb.toString().print()

        var ans = 0L
        var step = 1L
        for (i in sb.indices.reversed()) {
            if (sb[i] == '1') {
                ans += step
            }
            step *= 2
        }
        return ans
    }
}