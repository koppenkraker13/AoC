import numpy

with open("../data/data.txt") as fp:
    forest = list(map(lambda y: [int(x) for x in [*y]], fp.read().splitlines()))
    visible = numpy.full((len(forest), len(forest[0])), False).tolist()
    surface = numpy.full((len(forest), len(forest[0])), 0).tolist()
    for i, direction in enumerate([(range(1, len(forest) - 1), range(1, len(forest[0]) - 1)), (range(len(forest) - 2, 0, -1), range(len(forest[0]) - 2, 0, -1))]):
        highest_y = numpy.full((len(forest[0]), 10), -1 if i == 0 else len(forest)).tolist()
        for y, tree in enumerate(forest[i*-1]):
            highest_y[y][tree] = i*(len(forest) - 1)
        for y in direction[0]:
            highest_x = [-1 if i == 0 else len(forest[y])] * 10
            highest_x[forest[y][i*-1]] = i*(len(forest[y]) - 1)
            for x in direction[1]:
                if i == 0:
                    visible[y][x] = max(highest_x[forest[y][x]:]) == -1 or max(highest_y[x][forest[y][x]:]) == -1
                    surface[y][x] = x - (max(highest_x[forest[y][x]:]) if max(highest_x[forest[y][x]:]) != -1 else 0)
                    surface[y][x] *= y - (max(highest_y[x][forest[y][x]:]) if max(highest_y[x][forest[y][x]:]) != -1 else 0)
                else:
                    visible[y][x] = min(highest_x[forest[y][x]:]) == len(forest[y]) or min(highest_y[x][forest[y][x]:]) == len(forest) if not visible[y][x] else True
                    surface[y][x] *= (min(highest_x[forest[y][x]:]) if min(highest_x[forest[y][x]:]) != len(forest[y]) else len(forest[y]) - 1) - x
                    surface[y][x] *= (min(highest_y[x][forest[y][x]:]) if min(highest_y[x][forest[y][x]:]) != len(forest) else (len(forest) - 1)) - y
                highest_x[forest[y][x]] = x
                highest_y[x][forest[y][x]] = y
    print(sum([sum(line) for line in visible]) + 2 * len(forest) + 2 * len(forest[0]) - 4)
    print(max([max(line) for line in surface]))
