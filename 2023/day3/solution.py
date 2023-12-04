def isSymbol(char):
    return not (char.isdigit() or char == '.')


def getParts(pre: str, line: str, nex: str):
    total = 0
    num = 0
    part = False
    for i in range(1, len(line) - 1):
        up, down, char = pre[i], nex[i], line[i]
        if char.isdigit():
            num = num * 10 + int(char)
            if num < 10:
                part = isSymbol(pre[i - 1]) or isSymbol(nex[i - 1]) or isSymbol(line[i - 1])
            if isSymbol(up) or isSymbol(down): part = True
        if num != 0 and not (char.isdigit() and i != len(line) - 2):
            if isSymbol(up) or isSymbol(down) or isSymbol(char): part = True
            if part: total += num
            num = 0
    return total


def getNumber(line, index):
    start = index
    num = 0
    for i in range(index, -1, -1):
        if not line[i].isdigit():
            if i != index: start = i + 1
            break
    for i in range(start, len(line) - 1):
        if line[i].isdigit():
            num = num * 10 + int(line[i])
        else:
            break
    return num, start+len(str(num))-1


def getGearRatio(pre, line, nex):
    total = 0
    for i in range(1, len(line) - 1):
        if line[i] == '*':
            numbers = []
            for li in [pre, line, nex]:
                possible = True
                for x in range(i-1, i+2):
                    if possible:
                        ret = getNumber(li, x)
                        if ret[0] != 0:
                            numbers.append(ret[0])
                        possible = ret[1] <= x
            if len(numbers) == 2:
                total += numbers[0] * numbers[1]
    return total


with open('input') as f:
    lines = ['.' + line + '.' for line in f.read().splitlines()]
    sol1 = 0
    sol2 = 0
    for y in range(len(lines)):
        line = lines[y]
        pre = '.' * len(line)
        nex = pre
        if y != 0: pre = lines[y - 1]
        if y != len(lines) - 1: nex = lines[y + 1]
        sol1 += getParts(pre, line, nex)
        sol2 += getGearRatio(pre, line, nex)
    print(f'solution1: ' + str(sol1))
    print(f'solution2: ' + str(sol2))
