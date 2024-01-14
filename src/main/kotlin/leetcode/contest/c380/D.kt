package leetcode.contest.c380

import utils.SegmentTree
import utils.kmpSearch
import utils.print
import kotlin.math.abs

fun main() {
    val s = SolutionD()
    s.beautifulIndices("isawsquirrelnearmysquirrelhouseohmy", "my", "squirrel", 15).joinToString().print()
    s.beautifulIndices("abcd", "a", "a", 4).joinToString().print()
}

class SolutionD {
    fun beautifulIndices(s: String, a: String, b: String, k: Int): List<Int> {
        val aList = ArrayList<Int>()
        val bList = ArrayList<Int>()

        kmpSearch(a, s) {
            aList.add(it)
        }
        kmpSearch(b, s) {
            bList.add(it)
        }

        val ans = ArrayList<Int>()
//        aList.joinToString().print()
//        bList.joinToString().print()

        val root = SegmentTree<Int>(0, Int.MAX_VALUE / 2, 0) { a, b ->
            a + b
        }
        bList.forEach { root.update(root, it, it, 1) }

        aList.forEach { aItem ->
//            println("query ${aItem - k}..${aItem + k}")
            if (root.query(root, aItem - k, aItem + k) > 0) {
                ans.add(aItem)
            }
        }
        return ans
    }
}