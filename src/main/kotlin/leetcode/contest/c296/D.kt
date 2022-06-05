package leetcode.contest.c296

import utils.print
import java.util.*


fun main() {
    val e = TextEditor()
    e.addText("leetcode")
    e.deleteText(4).print()
    e.addText("practice")
    e.cursorRight(3).print()
}

class TextEditor() {

    var index = 0

    var cur = StringBuilder()

    fun addText(text: String) {
        cur.insert(index, text)
        index += text.length
    }

    fun deleteText(k: Int): Int {
        val right = index
        val left = maxOf(0, index - k)
        cur.delete(left, right)
        if (index >= k) {
            index -= k
            return k
        }
        val ans = index
        index = 0
        return ans
    }

    fun cursorLeft(k: Int): String {
        index = maxOf(0, index - k)
        return cur.substring(maxOf(0, index - 10), index)
    }

    fun cursorRight(k: Int): String {
        index = minOf(index + k, cur.length)
        return cur.substring(maxOf(0, index - 10), index)
    }

}

/**
 * Your TextEditor object will be instantiated and called as such:
 * var obj = TextEditor()
 * obj.addText(text)
 * var param_2 = obj.deleteText(k)
 * var param_3 = obj.cursorLeft(k)
 * var param_4 = obj.cursorRight(k)
 */