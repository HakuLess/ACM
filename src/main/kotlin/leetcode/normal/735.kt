package leetcode.normal

import java.util.*

class Solution735 {
    fun asteroidCollision(asteroids: IntArray): IntArray {
        val st = Stack<Int>()
        asteroids.forEach {
            st.push(it)
            while (st.size >= 2) {
                val b = st.pop()
                val a = st.pop()
                if (a > 0 && b < 0) {
                    if (a == -b) break
                    else if (a < -b) st.push(b)
                    else st.push(a)
                } else {
                    st.push(a)
                    st.push(b)
                    break
                }
            }
        }
        return st.toIntArray()
    }
}