import math


def part1():
    location = 'AAA'
    i = 0
    while True:
        location = locations[location][0 if instructions[i % len(instructions)] == 'L' else 1]
        i += 1
        if location == 'ZZZ':
            print(f'solution1: {i}')
            break


def part2(starts: list[str]):
    loops = [detectLoop(start) for start in starts]
    print(f'solution2: {math.lcm(*loops)}')


def detectLoop(start: str):
    loop = {}
    location = start
    i = 0
    between = 0
    while True:
        location = locations[location][0 if instructions[i % len(instructions)] == 'L' else 1]
        if location[2] == 'Z':
            between = 0
            if location in loop and i % len(instructions) in loop.get(location):
                return i // (len(loop.keys()) + 1) + 1
            if location in loop:
                loop[location].append(i % len(instructions))
            else:
                loop[location] = [i % len(instructions)]
        i += 1
        between += 1


with open('input') as f:
    lines = f.read().splitlines()
    instructions = lines[0]
    locations = {}
    p2start = []
    for line in lines[2:]:
        splitted = line.split(' = (')
        if splitted[0][2] == 'A':
            p2start.append(splitted[0])
        splitt = splitted[1].removesuffix(')').split(", ")
        locations[splitted[0]] = splitt
    part1()
    part2(p2start)
