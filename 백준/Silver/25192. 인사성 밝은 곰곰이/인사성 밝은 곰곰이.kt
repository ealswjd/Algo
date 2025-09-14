// https://www.acmicpc.net/problem/25192

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt() // 채팅방의 기록 수
    var cnt = 0 // 곰곰티콘 사용 횟수
    val userSet = mutableSetOf<String>()

    repeat(n) {
        // ENTER, 혹은 채팅을 입력한 유저의 닉네임이 문자열로 주어진다.
        val input = readLine()

        if (input.equals("ENTER")) {
            cnt += userSet.size
            userSet.clear()
        } else {
            userSet.add(input)
        }
    }

    cnt += userSet.size

    close()
    print(cnt)
}