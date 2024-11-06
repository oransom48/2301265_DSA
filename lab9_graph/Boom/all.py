
from collections import defaultdict

# ฟังก์ชันหา Strongly Connected Components (SCC) ด้วยอัลกอริทึม Kosaraju
def kosaraju_scc(N, graph):
    visited = [False] * (N + 1)
    stack = []

    # ฟังก์ชัน DFS เพื่อสร้างสแต็คตามลำดับการเยี่ยมชม
    def dfs_first(node):
        visited[node] = True
        for neighbor in graph[node]:
            if not visited[neighbor]:
                dfs_first(neighbor)
        stack.append(node)

    # ฟังก์ชัน DFS สำหรับกราฟที่ถูกทรานสโพส
    def dfs_second(node, transposed_graph, component):
        visited[node] = True
        component.append(node)
        for neighbor in transposed_graph[node]:
            if not visited[neighbor]:
                dfs_second(neighbor, transposed_graph, component)

    # สร้างกราฟทรานสโพส
    transposed_graph = defaultdict(list)
    for u in range(1, N + 1):
        for v in graph[u]:
            transposed_graph[v].append(u)

    # ดำเนินการ DFS ครั้งแรกเพื่อสร้างสแต็ค
    for i in range(1, N + 1):
        if not visited[i]:
            dfs_first(i)

    # เคลียร์ visited และดำเนินการ DFS ครั้งที่สองเพื่อหา SCC
    visited = [False] * (N + 1)
    scc = []

    while stack:
        node = stack.pop()
        if not visited[node]:
            component = []
            dfs_second(node, transposed_graph, component)
            scc.append(component)

    return scc

# ฟังก์ชันหา source และ sink ของ SCCs
def find_sources_and_sinks(N, scc, graph):
    scc_index = {}
    for i, component in enumerate(scc):
        for node in component:
            scc_index[node] = i

    num_sccs = len(scc)
    out_degree = [0] * num_sccs
    in_degree = [0] * num_sccs

    # คำนวณ in-degree และ out-degree ของแต่ละ SCC
    for u in range(1, N + 1):
        for v in graph[u]:
            if scc_index[u] != scc_index[v]:
                out_degree[scc_index[u]] += 1
                in_degree[scc_index[v]] += 1

    sources = [i for i in range(num_sccs) if in_degree[i] == 0]
    sinks = [i for i in range(num_sccs) if out_degree[i] == 0]

    return sources, sinks

# รับข้อมูลจาก input
N, M = map(int, input().split())  # จำนวนโหนดและจำนวนเส้นเชื่อม
edges = [tuple(map(int, input().split())) for _ in range(M)]  # เส้นเชื่อมแต่ละเส้น

# สร้างกราฟจากข้อมูลที่กำหนด
graph = defaultdict(list)
for u, v in edges:
    graph[u].append(v)

# หา SCCs
scc = kosaraju_scc(N, graph)

# แสดงผลลัพธ์ของ SCCs
print("\nStrongly Connected Components (SCCs):")
for i, component in enumerate(scc, 1):
    print(f"SCC {i}: {component}")

# หา sources และ sinks ของ SCCs
sources, sinks = find_sources_and_sinks(N, scc, graph)

# เพิ่มเส้นเชื่อมเพื่อเชื่อมต่อ SCCs
new_edges = []
for i in range(min(len(sources), len(sinks))):
    source_scc = scc[sources[i]][0]  # โหนดใดโหนดหนึ่งใน source SCC
    sink_scc = scc[sinks[i]][0]      # โหนดใดโหนดหนึ่งใน sink SCC
    new_edges.append((sink_scc, source_scc))

# แสดงเส้นเชื่อมใหม่ที่เพิ่มเข้ามา
print("\nNew edges added to connect SCCs:")
for u, v in new_edges:
    print(f"{u} -> {v}")

# เพิ่มเส้นเชื่อมใหม่ลงใน graph dictionary
for u, v in new_edges:
    graph[u].append(v)

# แสดงกราฟทั้งหมดในรูปแบบ dictionary หลังจากเพิ่มเส้นเชื่อม
print("\nGraph representation as a dictionary after adding edges:")
for node, connections in graph.items():
    print(f"{node}: {connections}")

# แสดงการเชื่อมต่อของแต่ละโหนดในกราฟ
print("\nFinal graph connections (Node -> Connected Nodes):")
for node in range(1, N + 1):
    if node in graph:
        print(f"{node}: {graph[node]}")