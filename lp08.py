# Import necessary libraries
import pandas as pd
from sklearn.preprocessing import LabelEncoder, StandardScaler
from sklearn.cluster import AgglomerativeClustering
import matplotlib.pyplot as plt
import seaborn as sns
from scipy.cluster.hierarchy import dendrogram, linkage

# Load the dataset
url = '/kaggle/input/jaalnockdatasets/50_Startups.csv'  # Replace with the correct file path
data = pd.read_csv(url)

# Check the first few rows of the dataset
print(data.head())

# Data Preprocessing
# Label Encoding for the 'STATE' column (New York, California, Florida)
label_encoder = LabelEncoder()
data['STATE'] = label_encoder.fit_transform(data['STATE'])

# Feature Scaling
scaler = StandardScaler()
data_scaled = scaler.fit_transform(data[['RND', 'ADMIN', 'MKT', 'STATE', 'PROFIT']])

# Hierarchical Clustering (Agglomerative Clustering)
# Create a linkage matrix for the dendrogram
linked = linkage(data_scaled, method='ward')

# Plot the Dendrogram
plt.figure(figsize=(10, 7))
dendrogram(linked)
plt.title("Dendrogram for Hierarchical Clustering")
plt.xlabel("Index")
plt.ylabel("Distance")
plt.show()

# Apply Agglomerative Clustering
# Choose a suitable number of clusters based on the dendrogram
agglomerative = AgglomerativeClustering(n_clusters=4, affinity='euclidean', linkage='ward')
data['Cluster'] = agglomerative.fit_predict(data_scaled)

# Visualize the clusters
plt.figure(figsize=(8, 6))
sns.scatterplot(x=data['RND'], y=data['PROFIT'], hue=data['Cluster'], palette='viridis', s=100, alpha=0.7)
plt.title("Hierarchical Clustering based on PROFIT", fontsize=15)
plt.xlabel("R&D Spend")
plt.ylabel("Profit")
plt.legend(title='Cluster')
plt.show()

# Display the dataset with cluster labels
print(data[['RND', 'ADMIN', 'MKT', 'STATE', 'PROFIT', 'Cluster']].head())
