// https://www.acmicpc.net/problem/11651

fun main() = with(System.`in`.bufferedReader()){
    val n = readLine().toInt() // 점의 개수
    val points = Array(n) {
        val (x, y) = readLine().split(" ").map { it.toInt() }
        x to y
    }

    // y좌표가 증가하는 순으로, y좌표가 같으면 x좌표가 증가하는 순서로 정렬
    points.sortWith(compareBy ({ it.second }, {it.first }))

    val result = StringBuilder()
    for ((x, y) in points) {
        result.append("$x $y\n")
    }

    print(result)
    close()
}