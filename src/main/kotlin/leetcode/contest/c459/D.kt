package leetcode.contest.c459

import utils.*
import kotlin.math.min

fun main() {
    val s = SolutionD()
    // 2
//    s.countTrapezoids("[[-3,2],[3,0],[2,3],[3,2],[2,-3]]".toGrid()).print()
//    // 1
//    s.countTrapezoids("[[0,0],[1,0],[0,1],[2,1]]".toGrid()).print()
//
//    // 0
//    s.countTrapezoids("[[-32,12],[-32,-94],[-32,-15],[-30,88]]".toGrid()).print()

    // 3
//    s.countTrapezoids("[[34,88],[-62,-38],[26,88],[91,88],[47,-38]]".toGrid()).print()

    // 10
    s.countTrapezoids("[[71,-89],[-75,-89],[-9,11],[-24,-89],[-51,-89],[-77,-89],[42,11]]".toGrid()).print()
}

class SolutionD {

    fun countTrapezoids(points: Array<IntArray>): Int {
        val map = HashMap<Double, ArrayList<Pair<Point, Point>>>()
        for (i in points.indices) {
            for (j in i + 1 until points.size) {
                val a = Point(points[i][0].toDouble(), points[i][1].toDouble())
                val b = Point(points[j][0].toDouble(), points[j][1].toDouble())
                val line = getLine(a, b)

                if (line.vertical) {
                    map[Double.MAX_VALUE] = map.getOrDefault(Double.MAX_VALUE, arrayListOf())
                    map[Double.MAX_VALUE]!!.add(Pair(a, b))
                } else {
                    map[line.a] = map.getOrDefault(line.a, arrayListOf())
                    map[line.a]!!.add(Pair(a, b))
                }
            }
        }

        var ans = 0
        var minus = 0
        map.keys.forEach { key ->
            val pointsPair = map[key]!!

            for (i in pointsPair.indices) {
                for (j in i + 1 until pointsPair.size) {
                    val (a, b) = pointsPair[i]
                    val (c, d) = pointsPair[j]
                    val set = hashSetOf(a, b, c, d)

                    // 共边排除
                    val line1 = getLine(a, b)
                    val line2 = getLine(c, d)

                    if (set.size == 4 && line1 != line2) {
                        ans++

                        // 根据另外边已加过排除
                        var needMinus = false
                        val line3 = getLine(a, c)
                        val line4 = getLine(b, d)

                        if (line3.isSameVector(line4)) {
                            needMinus = true
                        }


                        val line5 = getLine(a, d)
                        val line6 = getLine(b, c)

//                            println("$a $d getLine $line5")
                        if (line5.isSameVector(line6)) {
                            needMinus = true
//                                println("needMinus $line5 $line6")
                        }

                        if (needMinus) minus++
                    }
                }
            }
        }

        return ans - minus / 2
    }
}