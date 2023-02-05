package leetcode.contest.c331

import utils.SegmentTree

class SolutionB {
    fun vowelStrings(words: Array<String>, queries: Array<IntArray>): IntArray {
        val set = hashSetOf<Char>('a', 'e', 'i', 'o', 'u')
        val root = SegmentTree<Int>(start = 0, end = 100005, value = 0) { a, b ->
            a + b
        }
        for (i in words.indices) {
            words[i].run {
                if (this.first() in set && this.last() in set) {
                    root.update(root, i, i, 1)
                }
            }
        }
        val ans = ArrayList<Int>()
        queries.forEach {
            ans.add(root.query(root, it[0], it[1]))
        }
        return ans.toIntArray()
    }
}