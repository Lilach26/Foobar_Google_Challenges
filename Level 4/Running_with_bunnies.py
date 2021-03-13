from itertools import permutations


def solution(times, times_limit):
    size = len(times)
    bunnies = range(size - 2)

    if not bunnies:
        return []

    distanes = floyd_warshall(times, size)
    check_negative_cycle = []
    for i, infinite in enumerate(distanes):
        if infinite[i] < 0:
            check_negative_cycle.append(infinite[i])
    if check_negative_cycle:
        return bunnies

    generate_set_bunnies = reduce(lambda z, x: z + [y + [x] for y in z], bunnies, [[]])
    result = []

    for k in [list(i) for sub in generate_set_bunnies for i in permutations(sub)]:
        curr, temp = 0, 0
        for bunny in [x + 1 for x in k]:
            temp += distanes[curr][bunny]
            curr = bunny
        temp += distanes[curr][size - 1]

        if temp <= times_limit and len(result) < len(k):
            result = sorted(k)
        if len(result) == size - 2:
            return result
    return result


def floyd_warshall(paths, size):
    for i in range(size):
        for j in range(size):
            for k in range(size):
                paths[j][k] = min(paths[j][k], paths[j][i] + paths[i][k])
    return paths


print(solution([[0, 2, 2, 2, -1], [9, 0, 2, 2, -1], [9, 3, 0, 2, -1], [9, 3, 2, 0, -1], [9, 3, 2, 2, 0]], 1))
