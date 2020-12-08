def solution(n, b):
    k = len(n)
    list_of_cycle = []

    while True:
        x = ''.join(sorted(n, reverse=True))
        y = ''.join(sorted(n))
        z = int(x, b) - int(y, b)

        if b == 10:
            n = str(z)
        else:
            base_list = []
            while z:
                base_list.insert(0, str(z % b))
                z = z // b
            n = ''.join(base_list)

        n_size = len(n)
        if n_size != k:
            n.zfill(k - n_size)

        if n in list_of_cycle:
            return len(list_of_cycle) - list_of_cycle.index(n)

        list_of_cycle.append(n)
