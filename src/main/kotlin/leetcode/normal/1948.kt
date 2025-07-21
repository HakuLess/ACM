package leetcode.normal

import utils.print
import java.util.*


fun main() {
    val s = Solution1948()
    s.deleteDuplicateFolder(listOf(listOf("a"), listOf("c"), listOf("d"), listOf("a", "b"), listOf("c", "b"), listOf("d", "a"))).forEach {
        it.joinToString().print()
    }
}

class Solution1948 {
    class Trie {
        var serial: String? = null
        var children: MutableMap<String, Trie> = HashMap()
    }

    fun deleteDuplicateFolder(paths: List<List<String>>): List<List<String>> {
        val root: Trie = Trie()

        for (path in paths) {
            var cur: Trie? = root
            for (node in path) {
                cur!!.children.putIfAbsent(node, Trie())
                cur = cur.children[node]
            }
        }
        val freq: MutableMap<String?, Int> = HashMap()
        construct(root, freq)
        val ans: MutableList<List<String>> = ArrayList()
        val path: MutableList<String> = ArrayList()
        // 操作字典树，删除重复文件夹
        operate(root, freq, path, ans)
        return ans
    }

    private fun construct(node: Trie, freq: MutableMap<String?, Int>) {
        if (node.children.isEmpty()) return
        val v: MutableList<String> = ArrayList()
        for ((key, value) in node.children) {
            construct(value, freq)
            v.add(key + "(" + value.serial + ")")
        }
        v.sort()
        val sb = StringBuilder()
        for (s in v) {
            sb.append(s)
        }
        node.serial = sb.toString()
        freq[node.serial] = freq.getOrDefault(node.serial, 0) + 1
    }

    private fun operate(node: Trie, freq: Map<String?, Int>, path: MutableList<String>, ans: MutableList<List<String>>) {
        if ((freq[node.serial] ?: 0) > 1) return
        if (path.isNotEmpty()) {
            ans.add(ArrayList(path))
        }
        for ((key, value) in node.children) {
            path.add(key)
            operate(value, freq, path, ans)
            path.removeAt(path.size - 1)
        }
    }
}