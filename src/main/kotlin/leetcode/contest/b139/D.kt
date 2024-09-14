package leetcode.contest.b139

import utils.print
import utils.printInt
import utils.toGrid
import java.util.*
import kotlin.collections.ArrayList

fun main() {
    val s = SolutionD()
//    s.maxPathLength("[[3,1],[2,2],[4,1],[0,0],[5,3]]".toGrid(), 1).print()
//    s.maxPathLength("[[0,1]]".toGrid(), 0).print()
//    s.maxPathLength("[[5,0],[9,3],[9,8]]".toGrid(), 0).print()
    s.maxPathLength("[[9,6],[8,3],[3,2],[3,5]]".toGrid(), 0).print()
}

class SolutionD {
    fun maxPathLength(coordinates: Array<IntArray>, k: Int): Int {
        val startX = coordinates[k][0]
        val startY = coordinates[k][1]
        val list = coordinates.filter {
            (it[0] > startX && it[1] > startY) || (it[0] < startX && it[1] < startY) || (it[0] == startX && it[1] == startY)
        }.toTypedArray()
        list.sortWith(compareBy({ it[0] }, { it[1] }))

//        list.print()

        val tm = TreeMap<Int, Int>()

        fun reset(y: Int, v: Int) {
            while (tm.ceilingEntry(y + 1) != null && tm.ceilingEntry(y + 1).value <= v) {
                val entry = tm.ceilingEntry(y + 1).key
                tm.remove(entry)
//                println("remove $entry")
            }
        }

        val add = ArrayList<Pair<Int, Int>>()
        for (i in list.indices) {
            val (x, y) = list[i]

            val v = (tm.floorEntry(y - 1)?.value ?: 0) + 1
//            println("poll $x $y entry ${tm.floorEntry(y - 1)} with $v")
            if (i + 1 in list.indices && list[i + 1][0] == list[i][0]) {
                add.add(Pair(y, v))
            } else {
                if (add.isNotEmpty()) {
                    add.forEach {
                        tm[it.first] = it.second
                        reset(it.first, it.second)
                    }
                }
                add.clear()
                tm[y] = v
//                println("add2 $y with $v")

                reset(y, v)
            }
        }

//        tm.printInt()


        if (add.isNotEmpty()) {
            add.forEach {
                tm[it.first] = it.second
            }
        }
        add.clear()

        return tm.values.maxOrNull()!!
    }
}