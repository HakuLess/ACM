package atcoder.abc428

import java.util.Stack

fun main() {
    val q = readLine()!!.toInt()

    data class State(val status: Int, val minStatus: Int)

    val st = Stack<State>()
    var status = 0
    var minStatus = 0
    repeat(q) {
        val query = readLine()!!

        if (query[0] == '1') {
            val c = query[2]
            if (c == '(') status++ else status--
            minStatus = minOf(minStatus, status)
            st.push(State(status, minStatus))
        } else {
            st.pop()
            if (st.isEmpty()) {
                status = 0
                minStatus = 0
            } else {
                val last = st.peek()
                status = last.status
                minStatus = last.minStatus
            }
        }
        if (status == 0 && minStatus >= 0) println("Yes") else println("No")
    }
}