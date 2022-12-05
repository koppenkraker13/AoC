import copy

warehouse = []
with open("../data/data.txt") as fp:
    # Build warehouse
    for line in fp:
        if line[1].isdigit():
            break
        for i in range(1, len(line.replace("\n", "")), 4):
            if (i - 1) // 4 >= len(warehouse):
                warehouse.append([])
            if line[i] != " ":
                warehouse[(i - 1) // 4].append(line[i])
    fp.readline()
    for line in warehouse:
        line.reverse()
    warehouse_9001 = copy.deepcopy(warehouse)

    # The crane moves stuff
    for line in fp:
        splitted = [int(x) for x in line.removesuffix("\n").split(" ") if x.isdigit()]
        for i in range(splitted[0]):
            warehouse[splitted[2] - 1].append(warehouse[splitted[1] - 1].pop())
        warehouse_9001[splitted[2] - 1].extend(warehouse_9001[splitted[1] - 1][-splitted[0]:])
        warehouse_9001[splitted[1] - 1] = warehouse_9001[splitted[1] - 1][:-splitted[0] or None]

    # Print results
    print("".join([stack[-1] for stack in warehouse]))
    print("".join([stack[-1] for stack in warehouse_9001]))
