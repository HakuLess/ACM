package kickstart.round2022.roundA

fun main() {
    val t = readLine()!!.trim().toInt()
    repeat(t) {
        val a = readLine()!!.toString()
        var cur = 0
        for (i in a.indices) {
            cur += a[i] - '0'
        }
        val left = 9 - cur % 9
        if (left == 9) {
            val ans = ArrayList<Char>()
            ans.addAll(a.toCharArray().toTypedArray())
            ans.add(1, '0')
            println("Case #${it + 1}: ${ans.joinToString("")}")
        } else {
            val ans = ArrayList<Char>()
            ans.addAll(a.toCharArray().toTypedArray())
            for (i in ans.indices) {
                if (ans[i] - '0' > left) {
                    ans.add(i, '0' + left)
                    break
                }
            }
            if (ans.size == a.length) {
                ans.add('0' + left)
            }
            println("Case #${it + 1}: ${ans.joinToString("")}")
        }
    }
}