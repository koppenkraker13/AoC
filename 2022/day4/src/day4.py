solution1, solution2 = 0, 0
with open("../data/data.txt") as fp:
    for line in fp.read().splitlines():
        left, right = tuple(tuple(map(lambda x: int(x), y.split("-"))) for y in line.split(","))

        if (left[0] == right[0] or left[1] == right[1]) or (left[0] < right[0] and left[1] > right[1]) or (left[0] > right[0] and left[1] < right[1]):
            solution1 += 1

        if (left[0] <= right[0] <= left[1]) or (left[0] <= right[1] <= left[1]) or (right[0] < left[0] and right[1] > left[1]):
            solution2 += 1

print(solution1)
print(solution2)
