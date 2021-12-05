package leetcode.contest.c270

import utils.print
import utils.toGrid
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

fun main() {
    val s = SolutionD()
//    s.validArrangement(
//        arrayOf(
//            intArrayOf(5, 1),
//            intArrayOf(4, 5),
//            intArrayOf(11, 9),
//            intArrayOf(9, 4),
//        )
//    ).print()
//
//    s.validArrangement(
//        arrayOf(
//            intArrayOf(1, 2),
//            intArrayOf(1, 3),
//            intArrayOf(2, 1),
//        )
//    ).print()

//    s.validArrangement(
//        "[[5,13],[10,6],[11,3],[15,19],[16,19],[1,10],[19,11],[4,16],[19,9],[5,11],[5,6],[13,5],[13,9],[9,15],[11,16],[6,9],[9,13],[3,1],[16,5],[6,5]]".toGrid()
//    ).print()

    s.validArrangement(
        "[[229,699],[489,928],[92,398],[457,398],[798,838],[75,547],[856,141],[838,141],[356,578],[819,537],[229,458],[229,838],[473,175],[545,826],[705,75],[132,262],[92,974],[141,547],[856,92],[229,856],[838,826],[798,15],[892,157],[578,229],[458,905],[141,856],[157,458],[157,489],[92,458],[838,699],[905,458],[547,798],[928,157],[974,15],[545,132],[545,15],[141,132],[458,175],[856,586],[175,705],[547,229],[928,771],[157,671],[175,473],[132,229],[838,671],[458,356],[262,838],[75,262],[92,798],[156,671],[356,124],[547,175],[262,457],[705,545],[671,156],[928,671],[578,892],[483,856],[586,141],[141,838],[974,928],[356,157],[398,586],[15,157],[905,175],[856,157],[157,856],[398,771],[892,586],[974,473],[262,458],[175,141],[458,92],[175,856],[905,974],[928,229],[826,699],[826,483],[826,905],[905,838],[928,356],[974,905],[124,356],[124,537],[771,545],[262,771],[157,928],[229,157],[547,141],[928,75],[262,974],[856,798],[92,132],[15,141],[141,819],[458,15],[141,905],[458,928],[537,586],[92,819],[473,262],[578,473],[141,458],[15,856],[132,798],[537,974],[586,398],[928,141],[141,262],[771,141],[458,974],[157,771],[398,175],[838,974],[826,92],[175,892],[974,157],[838,356],[699,229],[356,489],[15,771],[771,905],[586,92],[771,92],[798,826],[92,537],[699,458],[671,928],[771,928],[398,928],[699,157],[458,157],[537,905],[974,578],[671,92],[671,75],[157,75],[156,838],[473,398],[928,705],[15,458],[705,458],[157,15],[819,124],[157,92],[699,928],[905,699],[798,262],[458,547],[586,856],[974,489],[856,545],[75,974],[75,578],[905,826],[856,705],[489,547]]".toGrid()
    ).print()

}

class SolutionD {
    fun validArrangement(pairs: Array<IntArray>): Array<IntArray> {
        val map = HashMap<Int, Stack<Int>>()
        val c = HashMap<Int, Int>()
        pairs.forEach {
            map[it[0]] = map.getOrDefault(it[0], Stack())
            map[it[0]]!!.add(it[1])

            c[it[1]] = 1
        }
        var start = pairs[0][0]
        val f = map.keys.filter { c.getOrDefault(it, 0) == 0 }
        if (f.isNotEmpty()) {
            start = f[0]
        }
        val ans = ArrayList<IntArray>()
        fun dfs(cur: Int, arr: ArrayList<IntArray>) {
            val next = map[cur]?.pop() ?: return
            if (map[cur]!!.isEmpty()) {
                map.remove(cur)
            }
            arr.add(intArrayOf(cur, next))
            dfs(next, arr)
        }
        dfs(start, ans)
        ans.joinToString { it.joinToString(" ") }.print()
        while (map.isNotEmpty()) {
            val temp = ArrayList<IntArray>()
            val key = map.keys.firstOrNull { it in ans.map { it[1] } } ?: map.keys.first()
            dfs(key, temp)

            var index = ans.indexOfFirst { it[1] == key }
            println("index $index")
            while (temp.first()[0] != key) {
                temp.add(temp.removeAt(0))
            }
            println("temp ${temp.joinToString { it.joinToString(" ") }}")
            ans.addAll(index + 1, temp)
        }

        for (i in 1 until ans.size) {
            if (ans[i][0] != ans[i - 1][1]) {
                println("Error ${ans[i].joinToString()}")
            }
        }
        return ans.toTypedArray()
    }
}