package atcoder.abc240

import java.util.*
import kotlin.collections.ArrayList

fun main(args: Array<String>) {
    val n = readLine()!!.trim().toInt()
    val arr = readLine()!!.trim().split(" ").map { it.toInt() }
    val ans = ArrayList<Int>()
    val st = Stack<Pair<Int, Int>>()
    var c = 0
    var lst = -1
    for (i in arr.indices) {
        val cur = arr[i]
        if (lst == cur) {
            c++
        } else {
            lst = cur
            c = 1
        }
        st.push(Pair(cur, c))
        if (c == cur) {
            repeat(c) {
                st.pop()
            }
        }
        if (st.isNotEmpty()) {
            lst = st.peek().first
            c = st.peek().second
        } else {
            lst = -1
            c = 0
        }
        ans.add(st.size)
    }
    println(ans.joinToString("\n"))
}