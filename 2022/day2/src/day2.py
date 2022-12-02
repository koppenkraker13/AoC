with open("../data/data.txt") as fp:
    print(sum(map(lambda y: ((ord(y[1]) - ord(y[0]) - 22) % 3) * 3 + ord(y[1]) - 87, map(lambda x: x.replace("\n", "").split(" "), fp))))
    fp.seek(0)
    print(sum(map(lambda y: ((ord(y[1]) - 88) * 3) + ((ord(y[0]) - 65 + ord(y[1]) - 89) % 3) + 1, map(lambda x: x.replace("\n", "").split(" "), fp))))
