import numpy as np

# Define the points
points = np.array([
    [0.1, 0.6],  # P1
    [0.15, 0.71],  # P2
    [0.08, 0.9],  # P3
    [0.16, 0.85],  # P4
    [0.2, 0.3],  # P5
    [0.25, 0.5],  # P6
    [0.24, 0.1],  # P7
    [0.3, 0.2]  # P8
])

# Initial centroids
m1 = points[0]  # P1
m2 = points[7]  # P8


# Function to calculate the Euclidean distance
def euclidean_distance(point, centroid):
    return np.sqrt(np.sum((point - centroid) ** 2))


# Perform one iteration of K-means clustering
def k_means_iteration(points, m1, m2):
    cluster_1 = []
    cluster_2 = []

    # Assign each point to the nearest cluster
    for point in points:
        distance_to_m1 = euclidean_distance(point, m1)
        distance_to_m2 = euclidean_distance(point, m2)

        if distance_to_m1 < distance_to_m2:
            cluster_1.append(point)
        else:
            cluster_2.append(point)

    # Calculate new centroids
    new_m1 = np.mean(cluster_1, axis=0)
    new_m2 = np.mean(cluster_2, axis=0)

    return cluster_1, cluster_2, new_m1, new_m2


# Run the K-means iteration
cluster_1, cluster_2, new_m1, new_m2 = k_means_iteration(points, m1, m2)

# Find which cluster P6 belongs to
p6 = points[5]
p6_cluster = "C1" if euclidean_distance(p6, new_m1) < euclidean_distance(p6, new_m2) else "C2"

# Display the results
print("Cluster assignments:")
print("Cluster 1 (C1):", cluster_1)
print("Cluster 2 (C2):", cluster_2)
print()
print(f"1) P6 belongs to Cluster {p6_cluster}")
print(f"2) Population of Cluster around m2 (C2): {len(cluster_2)}")
print(f"3) Updated centroids:\n   m1 = {new_m1}\n   m2 = {new_m2}")
