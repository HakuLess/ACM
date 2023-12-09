package leetcode.contest.b119

import utils.*

fun main() {
    val s = SolutionD()
    // 5
//    s.numberOfSets(3, 5, "[[0,1,2],[1,2,10],[0,2,10]]".toGrid()).print()
    // 7
//    s.numberOfSets(3, 5, "[[0,1,20],[0,1,10],[1,2,2],[0,2,2]]".toGrid()).print()
    // 5
    s.numberOfSets(3, 12, "[[1,0,11],[1,0,16],[0,2,13]]".toGrid()).print()
}

class SolutionD {
    fun numberOfSets(n: Int, maxDistance: Int, roads: Array<IntArray>): Int {
        val b = Bits(n)
        var ans = 0
        roads.sortByDescending { it[2] }
        b.eachMask { mask ->

            val g = Graph(n)
            val add = hashSetOf<Int>()

            b.eachBit(mask) { index, b ->
//                println("$mask with $index $b")
                if (b) {
                    add.add(index)
                }
            }


            roads.forEach {
                val (x, y, z) = it
                if (x in add && y in add) {
                    g.addEdge(x, y, z)
                }
            }

            val f = g.floyd()

//            f.print()

            var plus = true
            for (i in add) {
                for (j in add) {
                    if (f[i][j] > maxDistance) {
                        plus = false
                    }
                }
            }
            if (plus) {
//                println("add mask is $mask with add ${add.joinToString()}")
                ans++
            }
        }
        return ans
    }
}