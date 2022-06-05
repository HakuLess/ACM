package leetcode.contest.c296

import utils.print
import java.util.*


fun main() {
    val textEditor = TextEditor()
    textEditor.addText("leetcode")
    textEditor.deleteText(4).print()
    textEditor.addText("practice")
    textEditor.cursorRight(3).print()
    textEditor.cursorLeft(8).print()
    textEditor.deleteText(10).print()
    textEditor.cursorLeft(2).print()
    textEditor.cursorRight(6).print()
}

// 链表就使用ArrayDeque!
// 可以用两个ArrayDeque模拟一整条链表（当前Node在链表中间的Case）
class TextEditor() {

    val left = ArrayDeque<Char>()

    val right = ArrayDeque<Char>()

    fun addText(text: String) {
        text.forEach {
            left.addLast(it)
        }
    }

    fun deleteText(k: Int): Int {
        var ans = 0
        while (left.isNotEmpty() && ans < k) {
            left.removeLast()
            ans++
        }
        return ans
    }

    fun cursorLeft(k: Int): String {
        var step = 0
        while (left.isNotEmpty() && step < k) {
            right.addFirst(left.removeLast())
            step++
        }
        val ans = StringBuilder()
        val t = minOf(left.size, 10)
        repeat(t) {
            val c = left.removeLast()
            ans.append(c)
            right.addFirst(c)
        }
        repeat(t) {
            left.addLast(right.removeFirst())
        }
        return ans.reversed().toString()
    }

    fun cursorRight(k: Int): String {
        var step = 0
        while (right.isNotEmpty() && step < k) {
            left.addLast(right.removeFirst())
            step++
        }
        val ans = StringBuilder()
        val t = minOf(left.size, 10)
        repeat(t) {
            val c = left.removeLast()
            ans.append(c)
            right.addFirst(c)
        }
        repeat(t) {
            left.addLast(right.removeFirst())
        }
        return ans.reversed().toString()
    }

}

//class TextEditor() {
//
//    var index = 0
//
//    var cur = StringBuilder()
//
//    fun addText(text: String) {
//        cur.insert(index, text)
//        index += text.length
//    }
//
//    fun deleteText(k: Int): Int {
//        val right = index
//        val left = maxOf(0, index - k)
//        cur.delete(left, right)
//        if (index >= k) {
//            index -= k
//            return k
//        }
//        val ans = index
//        index = 0
//        return ans
//    }
//
//    fun cursorLeft(k: Int): String {
//        index = maxOf(0, index - k)
//        return cur.substring(maxOf(0, index - 10), index)
//    }
//
//    fun cursorRight(k: Int): String {
//        index = minOf(index + k, cur.length)
//        return cur.substring(maxOf(0, index - 10), index)
//    }
//
//}

/**
 * Your TextEditor object will be instantiated and called as such:
 * var obj = TextEditor()
 * obj.addText(text)
 * var param_2 = obj.deleteText(k)
 * var param_3 = obj.cursorLeft(k)
 * var param_4 = obj.cursorRight(k)
 */