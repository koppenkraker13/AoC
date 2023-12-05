class SmallMap:

    def __init__(self, maptxt: list[str]):
        premapping = [[int(y) for y in x.split(' ')] for x in maptxt]
        self.mapping = [(range(li[0], li[0] + li[2]), range(li[1], li[1] + li[2])) for li in premapping]

    def _getmapped(self, dest: range, source: range, input: range):
        return range(input.start - source.start + dest.start, input.stop - source.start + dest.start)

    def _removeintersection(self, input: list[range], intersections: list[range]):
        res = input
        for intersection in intersections:
            newres = []
            for ran in res:
                if max(intersection.start, ran.start) >= min(intersection.stop, ran.stop):
                    newres.append(ran)
                    continue
                if ran.start < intersection.start:
                    newres.append(range(ran.start, intersection.start))
                if ran.stop > intersection.stop:
                    newres.append(range(intersection.stop, ran.stop))
            res = newres
        return res

    def map(self, input: list[range]):
        res = []
        intersections = []
        for entry in self.mapping:
            x = entry[1]
            for y in input:
                if max(x.start, y.start) < min(x.stop, y.stop):
                    intersection = range(max(x.start, y.start), min(x.stop, y.stop))
                    intersections.append(intersection)
                    res.append(self._getmapped(entry[0], x, intersection))
        return res + self._removeintersection(input, intersections)


class BigMap:

    def __init__(self, lines: list[str]):
        self.mapping = []
        buff = []
        skip = False
        for i, line in enumerate(lines):
            if skip:
                skip = False
            elif line == '':
                skip = True
                self.mapping.append(SmallMap(buff))
                buff = []
            elif i == len(lines) -1:
                buff.append(line)
                self.mapping.append(SmallMap(buff))
                buff = []
            else:
                buff.append(line)

    def map(self, input: range):
        res = [input]
        for mapp in self.mapping:
            res = mapp.map(res)
        return res


with open('input') as f:
    lines = f.read().splitlines()
    seeds = [int(seed) for seed in lines[0].removeprefix('seeds: ').split(' ')]
    seedmap = BigMap(lines[3:])
    minimum = -1
    for i in range(0, len(seeds), 2):
        ran = range(seeds[i], seeds[i] + seeds[i + 1])
        innermin = min([x.start for x in seedmap.map(ran)])
        minimum = innermin if minimum == -1 or innermin < minimum else minimum
    print(f'solution2: {minimum}')
