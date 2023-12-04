from collections import defaultdict


def parse(line: str):
    splitted = line.split(':')[1].split(' | ')
    return splitted[0].split(' '), splitted[1].split(' ')


with open('input') as f:
    sol1 = 0
    cardCopies = defaultdict(int)
    for i, line in enumerate(f.read().splitlines()):
        cardCopies[i] += 1
        card = parse(line)
        linepoints = 0
        toBeCopied = []
        for num in [x for x in card[1] if x != '']:
            if num in card[0]:
                linepoints = linepoints*2 if linepoints > 0 else 1
                toBeCopied.append(i+len(toBeCopied)+1)
        for copy in toBeCopied:
            cardCopies[copy] += cardCopies[i]
        sol1 += linepoints
    print(f'solution1: {sol1}')
    print(f'solution2: {sum(cardCopies.values())}')




