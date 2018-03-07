import sys

readInput = []
i = 0;
for i in sys.stdin:
    readInput.append(i);

ans = []
for letter in readInput[0]:
    if letter is "<":
        if len(ans) > 0:
            ans.pop()
    else:
        ans.append(letter)

print(''.join(ans))
