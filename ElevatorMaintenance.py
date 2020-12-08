def solution(l):
    split_list = [i.split(".") for i in l]
    list_to_sort = [map(int, i) for i in split_list]
    sorted_list = sorted(list_to_sort)
    final_list = [('.'.join(str(i) for i in j)) for j in sorted_list]

    return final_list
