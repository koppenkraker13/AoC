import re

with open('input') as f:
    d = re.compile("(\\d+)")
    read = f.read().splitlines()
    lines = [[int(x) for x in d.findall(line)] for line in read]
    linesSol2 = [int(''.join(d.findall(line))) for line in read]
    sol1, sol2 = 1, 0
    for i in range(len(lines[0])):
        beaten = 0
        for hold in range(1, lines[0][i]):
            if hold * (lines[0][i]-hold) > lines[1][i]:
                beaten += 1
        sol1 *= beaten
    for hold in range(1, linesSol2[0]):
        if hold * (linesSol2[0]-hold) > linesSol2[1]:
            sol2 += 1

    print(f'solution1: {sol1}')
    print(f'solution2: {sol2}')
