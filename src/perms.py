from itertools import permutations

read_file = open("words", "r").read().splitlines()

for line in read_file:
     print ">" + line
     perms = ["".join(p) for p in permutations(line, 4)]
     print "%s" % "\n".join(map(str, perms))

