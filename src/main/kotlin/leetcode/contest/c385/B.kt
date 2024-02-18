package leetcode.contest.c385

import utils.*
import java.util.*
import kotlin.collections.HashSet

fun main() {
    val s = SolutionB()
    s.longestCommonPrefix(intArrayOf(1, 10, 100), intArrayOf(1000)).print()
    s.longestCommonPrefix(intArrayOf(1, 2, 3), intArrayOf(4, 4, 4)).print()
}

class SolutionB {
    fun longestCommonPrefix(arr1: IntArray, arr2: IntArray): Int {
        val trie1 = Trie<Char>()
        val trie2 = Trie<Char>()
        arr1.forEach {
            trie1.insert(it.toString().toCharArray().toTypedArray())
        }
        arr2.forEach {
            trie2.insert(it.toString().toCharArray().toTypedArray())
        }

        val queue = LinkedList<String>()
        for (i in '1'..'9') {
            queue.offer(i.toString())
        }
        var ans = 0
        while (queue.isNotEmpty()) {
            val size = queue.size
            for (i in 0 until size) {
                val item = queue.poll()
                if (trie1.search(item.toCharArray().toTypedArray(), loop = false, needEnd = false) &&
                    trie2.search(item.toCharArray().toTypedArray(), loop = false, needEnd = false)
                ) {
                    ans = maxOf(ans, item.length)

                    for (i in '0'..'9') {
                        val next = "$item$i"
                        queue.offer(next)
                    }
                }
            }
        }
        return ans
    }
}