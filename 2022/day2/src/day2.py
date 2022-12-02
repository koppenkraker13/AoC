opponent, me = ["A", "B", "C"], ["X", "Y", "Z"]
with open("../data/data.txt", mode="r") as fp:
    print(sum(map(lambda y: ((me.index(y[1]) - opponent.index(y[0]) + 1) % 3) * 3 + me.index(y[1]) + 1, map(lambda x: x.replace("\n", "").split(" "), fp))))
    fp.seek(0)
    print(sum(map(lambda y: (me.index(y[1]) * 3) + ((opponent.index(y[0]) + me.index(y[1]) - 1) % 3) + 1, map(lambda x: x.replace("\n", "").split(" "), fp))))
