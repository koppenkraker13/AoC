oponent, me, score = ["A", "B", "C"], ["X", "Y", "Z"], 0
with open("../data/data.txt", mode="r") as fp:
    print(sum(map(lambda y: ((me.index(y[1]) - oponent.index(y[0]) + 1) % 3) * 3 + me.index(y[1]) + 1, map(lambda x: x.replace("\n", "").split(" "), fp))))
    fp.seek(0)
    print(sum(map(lambda y: (me.index(y[1]) * 3) + ((oponent.index(y[0]) + me.index(y[1]) - 1) % 3) + 1, map(lambda x: x.replace("\n", "").split(" "), fp))))

