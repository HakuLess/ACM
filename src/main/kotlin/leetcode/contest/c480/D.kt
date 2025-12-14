package leetcode.contest.c480

import utils.SegmentTree
import utils.print
import utils.toGrid

fun main() {
    val s = SolutionD()
    // [0, 2]
    s.minDeletions("ABA", "[[2,1,2],[1,1],[2,0,2]]".toGrid()).print()
    // [1, 0]
    s.minDeletions("ABB", "[[2,0,2],[1,2],[2,0,2]]".toGrid()).print()
    // [0, 1]
    s.minDeletions("BABA", "[[2,0,3],[1,1],[2,1,3]]".toGrid()).print()
}

class SolutionD {
    fun minDeletions(s: String, queries: Array<IntArray>): IntArray {

        data class RunNode(
            val runs: Int,
            val left: Char,
            val right: Char
        )

        val charArray = s.toCharArray()
        val n = charArray.size

        val root = SegmentTree<RunNode>(start = 0, end = n - 1, value = null) { a, b ->
            RunNode(
                runs = a.runs + b.runs - if (a.right == b.left) 1 else 0,
                left = a.left,
                right = b.right
            )
        }

        for (i in 0 until n) {
            root.update(root, i, RunNode(1, charArray[i], charArray[i]))
        }

        val ans = ArrayList<Int>()
        for (q in queries) {
            if (q[0] == 1) {
                val idx = q[1]
                charArray[idx] = if (charArray[idx] == 'A') 'B' else 'A'
                root.update(root, idx, RunNode(1, charArray[idx], charArray[idx]))
            } else {
                val l = q[1]
                val r = q[2]
                val node = root.query(root, l, r)
                val len = r - l + 1
                ans.add(len - node.runs)
            }
        }

        return ans.toIntArray()
    }
}