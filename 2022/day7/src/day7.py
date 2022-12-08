def getRes1(folder):
    size, result = 0, 0
    for item in folder[1]:
        if isinstance(item[1], list):
            temp = getRes1(item)
            size += temp[0]
            result += temp[1] + (temp[0] if temp[0] <= 100000 else 0)
        else:
            size += item[1]
    return size, result


def getRes2(size, folder):
    result2, used = 70000000, 0
    for item in folder[1]:
        if isinstance(item[1], list):
            temp = getRes2(size, item)
            result2 = temp[0] if result2 > temp[0] >= size else result2
            used += temp[1]
        else:
            used += item[1]
    return used if result2 > used >= size else result2, used


root = ("/", [])
path = [root]
with open("../data/data.txt") as fp:
    for line in [x.split(" ") for x in fp.read().splitlines()]:
        if line[0] == "$" and line[1] == "cd":
            if line[2] == "..":
                path.pop()
            elif line[2] == "/":
                path = [root]
            else:
                path[-1][1].append((line[2], []))
                path.append(path[-1][1][-1])
        elif line[0].isdigit():
            path[-1][1].append((line[1], int(line[0])))

temp = getRes1(root)
print(temp[1])
print(getRes2(temp[0] - 40000000, root)[0])
