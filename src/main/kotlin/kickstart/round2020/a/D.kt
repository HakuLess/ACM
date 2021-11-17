package kickstart.round2020.a

import utils.Trie
import utils.insert

fun main() {
    val t = readLine()!!.trim().toInt()
    repeat(t) {
        val (n, k) = readLine()!!.trim().split(' ').map { it.toInt() }
        val arr = ArrayList<String>()
        repeat(n) {
            arr.add(readLine()!!.trim())
        }

        var ans = 0
        val trie = Trie<Char>()
        arr.forEach {
            trie.insert(it.toCharArray().toTypedArray())
        }
        fun dfs(root: Trie.TrieNode<Char>) {
            root.children.forEach {
                ans += it.cnt / k
                dfs(it)
            }
        }
        dfs(trie.root)
        println("Case #${it + 1}: $ans")
    }
}