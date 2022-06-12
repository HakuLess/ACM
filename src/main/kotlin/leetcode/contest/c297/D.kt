package leetcode.contest.c297

import utils.print

fun main() {
    val s = SolutionD()
    s.distinctNames(arrayOf("coffee", "donuts", "time", "toffee")).print()
    s.distinctNames(arrayOf("lack", "back")).print()
}

class SolutionD {
    fun distinctNames(ideas: Array<String>): Long {
        val set = HashSet<String>()
        set.addAll(ideas)

        val mapA = HashMap<Char, ArrayList<String>>()
        ideas.forEach {
            val sub = it.substring(1, it.length)
            mapA[it[0]] = mapA.getOrDefault(it[0], arrayListOf())
            mapA[it[0]]!!.add(sub)
        }
        var ans = 0L
        for (i in 'a'..'z') {
            for (j in i + 1..'z') {
                if (i == j) continue
                if (mapA.getOrDefault(i, arrayListOf()).isEmpty() ||
                    mapA.getOrDefault(j, arrayListOf()).isEmpty()
                ) continue
                var a = 0
                var b = 0
                mapA[i]?.forEach {
                    if ("$j$it" !in set) a++
                }
                mapA[j]?.forEach {
                    if ("$i$it" !in set) b++
                }
                if (a == 0 && b == 0) continue
                ans += 1L * a * b * 2
            }
        }

        return ans
    }
}