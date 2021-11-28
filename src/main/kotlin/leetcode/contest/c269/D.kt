package leetcode.contest.c269

import utils.TypedUFS
import utils.UFS
import utils.print
import utils.toGrid
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.system.measureTimeMillis

fun main() {
    val s = SolutionD()
//    s.findAllPeople(6, "[[1,2,5],[2,3,8],[1,5,10]]".toGrid(), 1).joinToString().print()
    s.findAllPeople(4, "[[3,1,3],[1,2,2],[0,3,3]]".toGrid(), 3).joinToString().print()
    val a = ArrayList<IntArray>()
//    for (i in 1 until 100000) {
//        if (i < 100000 / 2)
//            a.add(intArrayOf(0, i, 0))
//        else
//            a.add(intArrayOf(0, i, 1))
//    }
    for (i in 1 until 100000) {
        a.add(intArrayOf(0, i, i))
    }
    measureTimeMillis {
        s.findAllPeople(100000, a.toTypedArray(), 1).joinToString().print()
    }.also {
        println("cost $it")
    }
}

class SolutionD {
    fun findAllPeople(n: Int, meetings: Array<IntArray>, firstPerson: Int): List<Int> {
        val state = IntArray(n)
        state[0] = 1
        state[firstPerson] = 1
        meetings.sortBy { it[2] }
        var i = 0
        var cur = -1
        val l = ArrayList<IntArray>()

        fun check() {
            val map = HashMap<Int, ArrayList<Int>>()
            l.forEach {
                map[it[0]] = map.getOrDefault(it[0], arrayListOf())
                map[it[1]] = map.getOrDefault(it[1], arrayListOf())
                map[it[0]]!!.add(it[1])
                map[it[1]]!!.add(it[0])
            }
            val q: Queue<Int> = LinkedList<Int>()
            for (j in map.keys) {
                if (state[j] == 1)
                    q.offer(j)
            }
            while (q.isNotEmpty()) {
                val size = q.size
                for (i in 0 until size) {
                    val item = q.poll()
                    map[item]?.forEach {
                        if (state[it] == 0) {
                            state[it] = 1
                            q.offer(it)
                        }
                    }
                }
            }
            l.clear()
        }
        while (i in meetings.indices) {
            if (meetings[i][2] > cur) {
                cur = meetings[i][2]
                check()
                l.add(intArrayOf(meetings[i][0], meetings[i][1]))
            } else {
                l.add(intArrayOf(meetings[i][0], meetings[i][1]))
            }
            i++
        }
        check()

        val ans = ArrayList<Int>()
        for (i in state.indices) {
            if (state[i] == 1) {
                ans.add(i)
            }
        }
        return ans
    }
}