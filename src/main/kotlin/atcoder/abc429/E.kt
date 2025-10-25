package atcoder.abc429

import utils.file.FastScanner
import java.util.ArrayDeque

data class Entry(val node: Int, val src: Int)

fun main() {
    val fs = FastScanner()
    val n = fs.nextInt()
    val m = fs.nextInt()
    val tmp = Array(n) { ArrayList<Int>() }
    repeat(m) {
        val u = fs.nextInt() - 1
        val v = fs.nextInt() - 1
        tmp[u].add(v)
        tmp[v].add(u)
    }
    val adj = Array(n) { tmp[it].toIntArray() }
    val s = fs.nextString()
    val inf = 1_000_000_000
    val src1 = IntArray(n) { -1 }
    val src2 = IntArray(n) { -1 }
    val d1 = IntArray(n) { inf }
    val d2 = IntArray(n) { inf }
    val q = ArrayDeque<Entry>()
    for (i in 0 until n) if (s[i] == 'S') {
        src1[i] = i
        d1[i] = 0
        q.add(Entry(i, i))
    }
    while (q.isNotEmpty()) {
        val (u, src) = q.removeFirst()
        val dist = if (src1[u] == src) d1[u] else d2[u]
        for (v in adj[u]) {
            val nd = dist + 1
            if (src1[v] == src) {
                if (nd < d1[v]) {
                    d1[v] = nd
                    q.add(Entry(v, src))
                }
                continue
            }
            if (src2[v] == src) {
                if (nd < d2[v]) {
                    d2[v] = nd
                    q.add(Entry(v, src))
                }
                continue
            }
            if (nd < d1[v]) {
                src2[v] = src1[v]
                d2[v] = d1[v]
                src1[v] = src
                d1[v] = nd
                q.add(Entry(v, src))
            } else if (nd < d2[v]) {
                src2[v] = src
                d2[v] = nd
                q.add(Entry(v, src))
            }
        }
    }
    val out = StringBuilder()
    for (i in 0 until n) if (s[i] == 'D') out.append((d1[i] + d2[i]).toLong()).append('\n')
    print(out)
}
