package leetcode.normal

import utils.Graph
import utils.print
import utils.topologicalSort

fun main() {
    val s = Solution269()
    s.alienOrder(arrayOf("wrt", "wrf", "er", "ett", "rftt")).print()
    s.alienOrder(arrayOf("z", "x", "z")).print()
}

class Solution269 {
    fun alienOrder(words: Array<String>): String {
//        val max = words.map { it.length }.max()!!
        for (i in words.indices) {
            for (j in i + 1 until words.size) {
                if (words[i].startsWith(words[j]) && words[i] != words[j]) return ""
            }
        }
        val max = words.maxOf { it.length }
        val seen = HashSet<Char>()
        words.forEach {
            seen.addAll(it.toList())
        }
        val g = Graph(26)
        for (j in 0 until max) {
            for (i in words.indices) {
                for (k in i + 1 until words.size) {
                    if (j in words[i].indices && j in words[k].indices) {
                        if (words[i].substring(0, j) == words[k].substring(0, j) && words[i][j] != words[k][j]) {
                            g.addEdgeOri(words[i][j] - 'a', words[k][j] - 'a')
                        }
                    }
                }
            }
        }
        return g.topologicalSort().map {
            'a' + it
        }.filter { it in seen }.joinToString("")
    }
}