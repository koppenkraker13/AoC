def parseIntput(line: str):
    # (gameId, [{colour, num}])
    gameId = int(line.split(':')[0].split(' ')[1])
    li = []
    for se in line.split(':')[1].split(';'):
        ans = {}
        for di in se.split(','):
            ans[di.split(' ')[2]] = int(di.split(' ')[1])
        li.append(ans)
    return gameId, li


def isPossible(li: list[dict[str, int]], cubes: list[tuple[str, int]]):
    for se in li:
        for cube, amount in cubes:
            if cube in se and se[cube] > amount:
                return False
    return True


def getPower(li: list[dict[str, int]]):
    minRed = 0
    minBlue = 0
    minGreen = 0
    for se in li:
        for colour, amount in se.items():
            if colour == 'red': minRed = max(minRed, amount)
            if colour == 'blue': minBlue = max(minBlue, amount)
            if colour == 'green': minGreen = max(minGreen, amount)
    return minRed * minBlue * minGreen


with open('input') as f:
    sol1 = 0
    sol2 = 0
    cubes = [('blue', 14), ('red', 12), ('green', 13)]
    for line in f.readlines():
        parsed = parseIntput(line.removesuffix('\n'))
        if isPossible(parsed[1], cubes):
            sol1 += parsed[0]
        sol2 += getPower(parsed[1])
    print(f'solution1: {sol1}')
    print(f'solution2: {sol2}')
