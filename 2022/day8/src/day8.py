import numpy
with open("../data/data.txt") as fp:
    forest = list(map(lambda y: [int(x) for x in [*y]], fp.read().splitlines()))
    visible = numpy.full((len(forest), len(forest[0])), False)
    for i, direction in enumerate([(range(1, len(forest) - 1), range(1, len(forest[0]) - 1)), (range(len(forest) - 2, 0, -1), range(len(forest[0]) - 2, 0, -1))]):
        highest_y = forest[i*-1].copy()
        for y in direction[0]:
            highest_x = forest[y][i*-1]
            for x in direction[1]:
                if forest[y][x] > highest_x or forest[y][x] > highest_y[x]:
                    visible[y][x] = True
                    highest_x = max(highest_x, forest[y][x])
                    highest_y[x] = max(highest_y[x], forest[y][x])
    print(sum([sum(line) for line in visible]) + 2 * len(forest) + 2 * len(forest[0]) - 4)
