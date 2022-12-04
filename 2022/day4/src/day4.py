solution1 = 0
with open("../data/data.txt") as fp:
    for line in fp:
        splitted = line.split(",")
        left = splitted[0].split("-")
        right = splitted[1].replace("\n", "").split("-")
        if int(left[0]) == int(right[0]) or int(left[1]) == int(right[1]):
            solution1 += 1
            continue
        if int(left[0]) < int(right[0]) and int(left[1]) > int(right[1]):
            solution1 += 1
            continue
        if int(left[0]) > int(right[0]) and int(left[1]) < int(right[1]):
            solution1 += 1

print(solution1)

solution2 = 0
with open("../data/data.txt") as fp:
    for line in fp:
        splitted = line.split(",")
        left = splitted[0].split("-")
        right = splitted[1].replace("\n", "").split("-")
        if int(left[0]) <= int(right[0]) <= int(left[1]):
            solution2 += 1
            continue
        if int(left[0]) <= int(right[1]) <= int(left[1]):
            solution2 += 1
            continue
        if int(right[0]) < int(left[0]) and int(right[1]) > int(left[1]):
            solution2 += 1

print(solution2)
