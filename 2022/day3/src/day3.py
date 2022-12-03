solution2 = 0
with open("../data/data.txt") as fp:
    lines = fp.read().splitlines()
    print(sum(map(lambda y: ord(y) - (38 if y.isupper() else 96), map(lambda x: list(set.intersection(set(x[:len(x) // 2]), set(x[len(x) // 2:])))[0], lines))))
    for i in range(len(lines) // 3):
        char = list(set.intersection(set(lines[i * 3]), set(lines[i * 3 + 1]), set(lines[i * 3 + 2])))[0]
        solution2 += ord(char) - (38 if char.isupper() else 96)

print(solution2)
