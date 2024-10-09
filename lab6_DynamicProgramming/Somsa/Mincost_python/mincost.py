import numpy as np
import matplotlib.pyplot as plt
import math

class Point:
    def __init__(self, x, y):
        self.x = x
        self.y = y

def sort_vertices(vertices):
    x, y = np.array(vertices)
    center_x, center_y = np.array(vertices).mean(1)
    angles = np.arctan2(y - center_y, x - center_x)
    indices = np.argsort(angles)

    result_x = x[indices]
    result_y = y[indices]
    return [result_x.tolist(), result_y.tolist()]

def dist(p1, p2):
    return math.sqrt(pow(p1.x - p2.x, 2) + pow(p1.y - p2.y, 2))

def dist(p1, p2):
    return math.sqrt(pow(p1[0] - p2[0], 2) + pow(p1[1] - p2[1], 2))

def perimeter(vertices, i, j, k):
    p1 = vertices[i]
    p2 = vertices[j]
    p3 = vertices[k]
    return dist(p1, p2) + dist(p2, p3) + dist(p1, p3)

def perimeter(vertices, i, j, k):
    p1 = [vertices[0][i], vertices[1][i]]
    p2 = [vertices[0][j], vertices[1][j]]
    p3 = [vertices[0][k], vertices[1][k]]
    return dist(p1, p2) + dist(p2, p3) + dist(p1, p3)

def print_vertice(index, i, j):
    if (j < i + 2):
        return
    else:
        print(f"[{i} {index[i][j]} {j}]")
        print_vertice(index, i, index[i][j])
        print_vertice(index, index[i][j], j)

def get_triangle_vertice(index, i, j, triangle_vertices):
    if (j < i + 2):
        return
    else:
        triangle_vertices.append([i, index[i][j], j])
        get_triangle_vertice(index, i, index[i][j], triangle_vertices)
        get_triangle_vertice(index, index[i][j], j, triangle_vertices)

def plotting(vertices, index, n):
    x = vertices[0]
    y = vertices[1]

    # line
    triangle_vertices = []
    get_triangle_vertice(index, 0, n-1, triangle_vertices)

    line_x = []
    line_y = []
    for i in range(len(triangle_vertices)):
        for j in range(3):
            tmp = triangle_vertices[i][j]
            line_x.append(vertices[0][tmp])
            line_y.append(vertices[1][tmp])

        tmp = triangle_vertices[i][0]
        line_x.append(vertices[0][tmp])
        line_y.append(vertices[1][tmp])

    plt.plot(line_x, line_y,"-ro")

    # savefig
    plt.savefig("lab6_DynamicProgramming/Somsa/Mincost_python/pic/ans5.png")

def main():
    n = int(input())
    vertices = [[], []]
    for i in range(n):
        tmp = input().split(" ")
        vertices[0].append(float(tmp[0]))
        vertices[1].append(float(tmp[1]))
    vertices = sort_vertices(vertices)

    if (n < 3):
        print("can't do a triangulation")
    else:
        table = [[0.0 for i in range(n)] for j in range(n)]
        track = [[0 for i in range(n)] for j in range(n)]
        
        gap = 0
        while (gap < n):

            i = 0
            j = gap
            while (j < n):
                if (j < i + 2):
                    table[i][j] = 0.0
                else:
                    table[i][j] = np.finfo(np.float32).max
                    k = i + 1
                    while (k < j):
                        temp = table[i][k] + table[k][j] + perimeter(vertices, i, j, k)
                        if (temp < table[i][j]):
                            table[i][j] = temp
                            track[i][j] = k
                        k += 1
                i += 1
                j += 1
            gap += 1

        print("minimum cost = %.2f" %table[0][n-1])
        plotting(vertices, track, n)
        print(table)

main()

"""
5
1 2
4 3
5 7
3 10
0 7

5
0 0
1 0
2 1
1 2
0 2

"""
