package leetcode.lccup.round2023.spring.team

import utils.*

fun main() {
    val s = SolutionD()
    s.evolutionaryRecord(intArrayOf(-1, 0, 0, 2)).print()
    s.evolutionaryRecord(intArrayOf(-1, 0, 0, 1, 2, 2)).print()
}

class SolutionD {
    fun evolutionaryRecord(parents: IntArray): String {
        val root = parents.toNTree()

        fun dfs(node: NTreeNode): String {
            val l = ArrayList<String>()
            node.children.forEach {
                l.add("0${dfs(it)}1")
            }
            l.sort()
            return l.joinToString("")
        }

        return dfs(root).trimEnd { it == '1' }
    }
}