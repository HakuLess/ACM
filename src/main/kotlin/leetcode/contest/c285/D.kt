package leetcode.contest.c285

import utils.SegmentTree
import utils.print

fun main() {
    val s = SolutionD()
//    s.longestRepeating("babacc", "bcb", intArrayOf(1, 3, 3)).print()
//    s.longestRepeating("abyzz", "aa", intArrayOf(2, 1)).print()
    s.longestRepeating("geuqjmt", "bgemoegklm", intArrayOf(3, 4, 2, 6, 5, 6, 5, 4, 3, 2)).print()
}

class SolutionD {
    fun longestRepeating(s: String, queryCharacters: String, queryIndices: IntArray): IntArray {
        class Item(val left: Char, val lc: Int, val right: Char, val rc: Int, val total: Int, val max: Int) {
            override fun toString(): String {
                return "$left $lc & $right $rc $total $max"
            }
        }

        val seg = SegmentTree<Item>(
            start = 0,
            end = s.lastIndex
        ) { a, b ->
            val left = a.left
            val lc = if (a.left == a.right && b.left == a.left && a.max == a.total) {
                a.lc + b.lc
            } else {
                a.lc
            }
            val right = b.right
            val rc = if (b.left == b.right && b.left == a.right && b.max == b.total) {
                a.rc + b.rc
            } else {
                b.rc
            }
            var max = maxOf(lc, rc)
            max = maxOf(max, a.max)
            max = maxOf(max, b.max)
            if (b.left == a.right) {
                max = maxOf(max, b.lc + a.rc)
            }
            Item(left, lc, right, rc, a.total + b.total, max)
        }
        for (i in s.indices) {
            seg.update(seg, i, Item(s[i], 1, s[i], 1, 1, 1))
        }
        val ans = ArrayList<Int>()
        for (i in queryCharacters.indices) {
            seg.update(seg, queryIndices[i], Item(queryCharacters[i], 1, queryCharacters[i], 1, 1, 1))
            ans.add(seg.query(seg, 0, s.lastIndex).max)
        }
        return ans.toIntArray()
    }
}