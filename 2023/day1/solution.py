
def getFirst(line, numbers):
    min = (-1, '')
    for letter in numbers:
        i = line.find(letter)
        if i != -1 and (min[0] == -1 or min[0] > i):
            min = (i, letter)
    lina = line
    if min[0] != -1:
        lina = lina.replace(min[1], str(numbers.index(min[1]) + 1))
    for char in lina:
        if char.isdigit():
            return int(char)

with open("input", "r") as f:
    sol1, sol2 = 0, 0
    li = ['one', 'two', 'three', 'four', 'five', 'six', 'seven', 'eight', 'nine']
    for line in f.readlines():
        # num1 = 10*getFirst(line, [])
        # num1 += getFirst(line[::-1], [x[::-1] for x in li])
        num2 = 10*getFirst(line, li)
        num2 += getFirst(line[::-1], [x[::-1] for x in li])
        print(num2)
        # sol1 += num1
        sol2 += num2
    # print(f'solution1: {sol1}')
    print(f'solution2: {sol2}')
