package luogu

import java.util.*
import kotlin.collections.ArrayList

// 单调栈
// MLE不理会，绝逼是Judge问题
fun main(args: Array<String>) {
    val n = readLine()!!.trim().toInt()
    val h = ArrayList<Int>()
    val v = ArrayList<Int>()
    repeat(n) {
        readLine()!!.trim().split(" ").map { it.toInt() }.let {
            h.add(it[0])
            v.add(it[1])
        }
    }
    val ans = IntArray(n)
    // 从左向右
    val st = Stack<Int>()
    for (i in h.indices) {
        while (st.isNotEmpty() && h[st.peek()] < h[i]) {
            ans[i] += v[st.pop()]
        }
        st.push(i)
    }
    st.clear()
    for (i in h.indices.reversed()) {
        while (st.isNotEmpty() && h[st.peek()] < h[i]) {
            ans[i] += v[st.pop()]
        }
        st.push(i)
    }
    println(ans.maxOrNull())
}