class SmallMap:

    def __init__(self, maptxt: list[str]):
        self.mapping = [[int(y) for y in x.split(' ')] for x in maptxt]

    def _getnum(self, input: int, entry: list[int]):
        return input - entry[1] + entry[0]

    def map(self, input: int):
        for entry in self.mapping:
            if entry[1] <= input < entry[1] + entry[2]:
                return self._getnum(input, entry)
        return input


class BigMap:

    def __init__(self, lines: list[str]):
        self.mapping = []
        buff = []
        skip = False
        for line in lines:
            if skip:
                skip = False
            elif line == '':
                skip = True
                self.mapping.append(SmallMap(buff))
                buff = []
            else:
                buff.append(line)

    def map(self, input: int):
        res = input
        for mapp in self.mapping:
            res = mapp.map(res)
        return res


with open('input') as f:
    lines = f.read().splitlines()
    seeds = [int(seed) for seed in lines[0].removeprefix('seeds: ').split(' ')]
    seedmap = BigMap(lines[3:])
    soil = [seedmap.map(seed) for seed in seeds]
    print(f'solution1: {min(soil)}')
