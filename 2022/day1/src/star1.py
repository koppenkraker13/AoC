elf_list = []
elf_total = 0

with open("../data/data.txt", mode="r") as fp:
    for line in fp:
        if line == "\n":
            elf_list.append(elf_total)
            elf_total = 0
        else:
            elf_total += int(line)

elf_list.append(elf_total)
elf_list.sort()
print("res1: " + str(elf_list[-1]))
print("res2: " + str(elf_list[-1] + elf_list[-2] + elf_list[-3]))
