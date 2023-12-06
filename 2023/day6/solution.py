import math
import re

with open('input') as f:
    lines = [[int(x) for x in re.compile("(\\d+)").findall(line)] for line in f.read().splitlines()]
    linesSol2 = [int(''.join([str(x) for x in line])) for line in lines]
    sol1 = math.prod(sum(1 for hold in range(1, lines[0][i]) if hold * (lines[0][i] - hold) > lines[1][i]) for i in range(len(lines[0])))
    sol2 = sum(1 for hold in range(1, linesSol2[0]) if hold * (linesSol2[0] - hold) > linesSol2[1])
    print(f'solution1: {sol1}')
    print(f'solution2: {sol2}')
