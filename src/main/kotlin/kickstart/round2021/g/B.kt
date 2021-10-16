package kickstart.round2021.g

fun main() {
    val t = readLine()!!.trim().toInt()
    repeat(t) {
        val k = readLine()!!.trim().toInt()
        val arrX = ArrayList<Pair<Long, Int>>()
        val arrY = ArrayList<Pair<Long, Int>>()
        repeat(k) {
            val (x1, y1, x2, y2) = readLine()!!.trim().split(' ').map { it.toLong() }
            arrX.add(Pair(x1, 1))
            arrX.add(Pair(x2, -1))
            arrY.add(Pair(y1, 1))
            arrY.add(Pair(y2, -1))
        }

        arrX.sortBy { it.first }
        arrY.sortBy { it.first }
        fun get(arr: ArrayList<Pair<Long, Int>>): Long {
            var left = 0
            var right = k
            for (i in arr.indices) {
                if (arr[i].second == 1) {
                    right--
                } else if (arr[i].second == -1) {
                    left++
                }
                if (right == left) {
                    return arr[i].first
                }
            }
            return 0L
        }

        val x = get(arrX)
        val y = get(arrY)
        println("Case #${it + 1}: $x $y")
    }
}