package leetcode.contest.b121

import utils.print

fun main() {
    val s = SolutionD()
//    s.numberOfPowerfulInt(1, 6000, 4, "124").print()
//    s.numberOfPowerfulInt(15, 215, 6, "10").print()
//    s.numberOfPowerfulInt(1000, 2000, 4, "3000").print()
    // 8
//    s.numberOfPowerfulInt(20, 1159, 5, "20").print()

    // 0
//    s.numberOfPowerfulInt(141, 148, 9, "9").print()

    // 5470
    s.numberOfPowerfulInt(1829505, 1255574165, 7, "11223").print()
    // 783790
    s.numberOfPowerfulInt(15398, 1424153842, 8, "218").print()
    // 7030275
    s.numberOfPowerfulInt(123546, 32486458654, 4, "1").print()

    // 1
    s.numberOfPowerfulInt(1, 1000000000000000, 5, "1000000000000000").print()
}

class SolutionD {
    fun numberOfPowerfulInt(start: Long, finish: Long, limit: Int, s: String): Long {

        val seen = HashMap<String, Long>()

        val a = start.toString().length
        val b = finish.toString().length

        fun dfs(cur: String): Long {

            val n = cur.length

            val key =
                "_${cur.first()}" +
                        "_${cur.takeLast(a).toLong().compareTo(start)}" +
                        "_${start.toString().takeLast(n).toLong().compareTo(cur.toLong())}" +
                        "_${finish.toString().takeLast(n).toLong().compareTo(cur.toLong())}" +
                        "_${cur.length}"

//            val key = "$cur"
            if (key in seen) return seen[key]!!

            var tmp = 0L
            if (cur.length > 16) return 0L
            val c = cur.toLong()
            if (c > finish) return 0L
            if (cur.length > finish.toString().length) return 0L

            if (cur.first() != '0') {
                if (c in start..finish) {
                    tmp += 1L
                }
            }

            for (i in '0'..('0' + limit)) {
                tmp += dfs("$i$cur")
            }
            return tmp.also {
//                println("cur is $cur with $it")
                seen[key] = it
            }
        }

        return dfs(s)
    }
}