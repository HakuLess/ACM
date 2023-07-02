package leetcode.contest.c351

import utils.print

fun main() {
    val s = SolutionD()
    s.survivedRobotsHealths(intArrayOf(5, 4, 3, 2, 1), intArrayOf(2, 17, 9, 15, 10), "RRRRR").joinToString().print()
    s.survivedRobotsHealths(intArrayOf(3, 5, 2, 6), intArrayOf(10, 10, 15, 12), "RLRL").joinToString().print()
    s.survivedRobotsHealths(intArrayOf(1, 2, 5, 6), intArrayOf(10, 10, 11, 11), "RLRL").joinToString().print()
}

class SolutionD {
    fun survivedRobotsHealths(positions: IntArray, healths: IntArray, directions: String): List<Int> {
        data class Item(val index: Int, val pos: Int, var health: Int, val dir: Int)

        // index pos health
        val l = ArrayList<Item>()
        for (i in positions.indices) {
            l.add(Item(i, positions[i], healths[i], if (directions[i] == 'L') -1 else 1))
        }
        // 按照距离排序
        l.sortBy { it.pos }

        var i = 0
        while (i in l.indices) {
//            l.joinToString().print()
//            println("enter $i")
            var needContinue = true
            if (i != 0) {
                while (needContinue) {
                    needContinue = false
                    if (i !in l.indices || i - 1 !in l.indices) break
                    if (l[i - 1].dir == 1 && l[i].dir == -1) {
                        if (l[i - 1].health == l[i].health) {
                            l.removeAt(i)
                            l.removeAt(i - 1)
                        } else if (l[i - 1].health < l[i].health) {
                            val insert = l.removeAt(i)
                            l.removeAt(i - 1)
                            insert.health = insert.health - 1
                            l.add(i - 1, insert)
                        } else {
                            l.removeAt(i)
                            val insert = l.removeAt(i - 1)
                            insert.health = insert.health - 1
                            l.add(i - 1, insert)
                        }
//                        println("need Remove")
                        needContinue = true
                        i--
                    }
                }
            }
            i++
        }

        l.sortBy { it.index }
        return l.map { it.health }
    }
}