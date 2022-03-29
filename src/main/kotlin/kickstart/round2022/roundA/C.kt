package kickstart.round2022.roundA

// 通配过后，没有长度大于等于5的回文子字符串
fun main() {

    var ans = false
    fun dfs(arr: CharArray, i: Int) {
        if (ans) {
            return
        }
        if (i == arr.size) {
            ans = true
            return
        }
        if (arr[i] != '?') {
            if (i >= 4 && arr[i] == arr[i - 4] && arr[i - 1] == arr[i - 3]) {
                return
            }
            if (i >= 5 && arr[i] == arr[i - 5] && arr[i - 1] == arr[i - 4] && arr[i - 2] == arr[i - 3]) {
                return
            }
            dfs(arr, i + 1)
        } else {
            arr[i] = '0'
            dfs(arr, i)
            arr[i] = '1'
            dfs(arr, i)
            arr[i] = '?'
        }
    }

    val t = readLine()!!.trim().toInt()
    repeat(t) {
        val n = readLine()!!.toInt()
        val s = readLine()!!.toString()
        ans = false
        dfs(s.toCharArray(), 0)
        if (ans) {
            println("Case #${it + 1}: POSSIBLE")
        } else {
            println("Case #${it + 1}: IMPOSSIBLE")
        }
    }
}