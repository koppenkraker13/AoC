from collections import defaultdict
from functools import cmp_to_key
from functools import partial


def _getRank(values: list[int]):
    if len(values) <= 1:
        return 6
    if len(values) == 2:
        if values[0] == 4 or values[1] == 4:
            return 5
        return 4
    if len(values) == 3:
        if values[0] == 3 or values[1] == 3 or values[2] == 3:
            return 3
        return 2
    if len(values) == 4:
        return 1
    return 0


def getRankSol1(cards: str):
    couples = defaultdict(int)
    for card in cards:
        couples[card] += 1
    return _getRank(list(couples.values()))


def getRankSol2(cards: str):
    couples = defaultdict(int)
    for card in cards:
        couples[card] += 1
    jokers = 0
    if 'J' in couples:
        jokers = couples['J']
        couples.pop('J')
    return _getRank([x + jokers for x in list(couples.values())])


def compare(order, tuple1, tuple2):
    for i in range(len(tuple1[0])):
        if order.index(tuple1[0][i]) > order.index(tuple2[0][i]):
            return 1
        if order.index(tuple1[0][i]) < order.index(tuple2[0][i]):
            return -1
    return tuple1


with open('input') as f:
    sol1, sol2 = 0, 0
    orderSol1 = ['2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A']
    orderSol2 = ['J', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'Q', 'K', 'A']
    ranksSol1, ranksSol2 = [[] for _ in range(10)], [[] for _ in range(10)]
    for line in f.read().splitlines():
        card = tuple(line.split(' '))
        ranksSol1[getRankSol1(card[0])].append(card)
        ranksSol2[getRankSol2(card[0])].append(card)
    cardNr = 1
    for rank in ranksSol1:
        for card in sorted(rank, key=cmp_to_key(partial(compare, orderSol1))):
            sol1 += int(card[1]) * cardNr
            cardNr += 1
    cardNr = 1
    for rank in ranksSol2:
        for card in sorted(rank, key=cmp_to_key(partial(compare, orderSol2))):
            sol2 += int(card[1]) * cardNr
            cardNr += 1
    print(f'solution1: {sol1}')
    print(f'solution2: {sol2}')
