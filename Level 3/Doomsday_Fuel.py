import fractions


def make_list(rows, cols):
    result = []
    for row in xrange(rows):
        result += [cols * [0]]
    return result


def identity(size):
    result = make_list(size, size)
    for i in xrange(size):
        result[i][i] = fractions.Fraction(1, 1)
    return result


def multiply(matrix1, matrix2):
    mat1_rows = len(matrix1)
    mat1_cols = len(matrix1[0])
    mat2_cols = len(matrix2[0])

    rows, cols = mat1_rows, mat2_cols
    result = make_list(rows, cols)

    for row in xrange(rows):
        for col in xrange(cols):
            product = fractions.Fraction(0, 1)
            for i in xrange(mat1_cols):
                product += matrix1[row][i] * matrix2[i][col]
            result[row][col] = product

    return result


def multiply_rows(matrix, row, i):
    size = len(matrix)
    row_operation = identity(size)
    row_operation[row][row] = i
    return multiply(row_operation, matrix)


def subtract(matrix, denominator):
    for i in range(len(matrix)):
        for j in range(len(matrix)):
            if i == j:
                matrix[i][j] = denominator - matrix[i][j]
            else:
                matrix[i][j] = matrix[i][j] * (-1)


def subtract_matrix(matrix, rows, cols):
    result = []
    for i in rows:
        row = []
        for j in cols:
            row.append(matrix[i][j])
        result.append(row)
    return result


def matrices_subtraction(mat1, mat2):
    result = []
    for index, row in enumerate(mat1):
        column = []
        for col_index, col in enumerate(row):
            column.append(mat1[index][col_index] - mat2[index][col_index])
        result.append(column)
    return result


def transform(matrix):
    for index, row in enumerate(matrix):
        row_sum = sum(matrix[index])
        if row_sum == 0:
            matrix[index][index] = 1
        else:
            for col_index, col in enumerate(row):
                matrix[index][col_index] = fractions.Fraction(col, row_sum)


def q_matrix(matrix, non_terminal):
    return subtract_matrix(matrix, non_terminal, non_terminal)


def r_matrix(matrix, non_terminal, teminal):
    return subtract_matrix(matrix, non_terminal, teminal)


def lcm(a, b):
    return a * b / fractions.gcd(a, b)


def calculate_array_lcm(nums):
    size = len(nums)
    if size <= 2:
        return lcm(*nums)

    init = lcm(nums[0], nums[1])
    i = 2
    while i < size:
        init = lcm(init, nums[i])
        i += 1
    return init


def add_rows(matrix, src, k, index):
    size = len(matrix)
    operation = identity(size)
    operation[index][src] = k
    return multiply(operation, matrix)


def inversion(matrix):
    size = len(matrix)
    invers = identity(size)

    for col in xrange(size):
        diag = col
        k = fractions.Fraction(1, matrix[diag][col])
        matrix = multiply_rows(matrix, diag, k)
        invers = multiply_rows(invers, diag, k)
        src = diag

        for i in xrange(size):
            if src != i:
                k = (-1) * matrix[i][col]
                matrix = add_rows(matrix, src, k, i)
                invers = add_rows(invers, src, k, i)
    return invers


def solution(m):
    terminal, non_terminal = [], []

    for i, row in enumerate(m):
        if sum(row) == 0:
            terminal.append(i)
        else:
            non_terminal.append(i)

    if len(terminal) == 1:
        return [1, 1]

    transform(m)
    Q = q_matrix(m, non_terminal)
    R = r_matrix(m, non_terminal, terminal)

    result = multiply(inversion(matrices_subtraction(identity(len(Q)), Q)), R)
    denominator = calculate_array_lcm([i.denominator for i in result[0]])

    result = [i.numerator * denominator / i.denominator for i in result[0]]
    result.append(denominator)
    return result
