for x in [4, 14]:
    with open("../data/data.txt") as fp:
        sequence = fp.read()
        for i in range(len(sequence) - x):
            if len(set(sequence[i:i+x])) == x:
                print(i + x)
                break