# [Silver I] 영재의 징검다리 - 24392 

[문제 링크](https://www.acmicpc.net/problem/24392) 

### 성능 요약

메모리: 76844 KB, 시간: 412 ms

### 분류

다이나믹 프로그래밍

### 제출 일자

2025년 6월 26일 15:39:44

### 문제 설명

<p>푸앙이 게임에 참가한 영재는 유리 징검다리 게임을 통과해야 한다.</p>

<p>유리 징검다리 게임의 규칙은 간단하다. 총 <em>N</em>번의 걸음을 통해 건널 수 있고, 각 걸음마다 <em>M</em>개의 칸이 있다. 영재는 시작점(<em>N</em> - 1 번째 줄)의 한 칸에서 건너기 시작해 이후 앞의 인접한 최대 3개의 유리 중 하나를 선택해 건너갈 수 있다. 밟은 칸이 강화유리라면 안전하게 건널 수 있지만, 일반 유리는 밟을 수 없다.</p>

<p>다음은 <em>N</em> = 3, <em>M</em> = 5인 어느 순간에 영재가 가능한 이동을 나타낸 그림이다.</p>

<p style="text-align: center;"><img alt="" src="https://upload.acmicpc.net/31b77266-faac-4ac6-a4c5-8a43cb1637e3/-/preview/"></p>

<p>다리의 정보가 주어지면, 영재가 다리를 무사히 건널 수 있는 경우의 수를 알아내보자.</p>

### 입력 

 <p>첫 줄에 <em>N</em>과 <em>M</em>(1 ≤ <em>N</em>, <em>M</em> ≤ 1,000)이 공백으로 구분되어 주어지고, 그 뒤에는 <em>N</em>줄에 걸쳐 다리의 정보가 주어진다.</p>

<p>강화유리의 경우 <code>1</code>, 일반 유리의 경우 <code>0</code>으로 주어진다.</p>

### 출력 

 <p>영재가 무사히 다리를 건널 수 있는 경우의 수를 1,000,000,007로 나눈 나머지를 출력한다.</p>

