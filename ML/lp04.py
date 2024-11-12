import pandas as pd
from sklearn.preprocessing import LabelEncoder, StandardScaler
from sklearn.cluster import AgglomerativeClustering
import matplotlib.pyplot as plt
import seaborn as sns

# Step 1: Load the dataset
# Change the dataset path to its exact path
df = pd.read_csv('/kaggle/input/jaalnockdatasets/cities_r2.csv')

# Step 2: Data Preprocessing

# Handle missing values for numeric columns by filling with the mean
numeric_cols = df.select_dtypes(include=['number']).columns
df[numeric_cols] = df[numeric_cols].fillna(df[numeric_cols].mean())

# Handle missing values for categorical columns by filling with the mode
categorical_cols = df.select_dtypes(include=['object']).columns
for col in categorical_cols:
    df[col].fillna(df[col].mode()[0], inplace=True)  # Fill with the mode for categorical columns

# Label Encoding for categorical columns
label_encoder = LabelEncoder()
for col in categorical_cols:
    df[col] = label_encoder.fit_transform(df[col])

# Step 3: Data Transformation

# Standardize the numeric features to bring them to the same scale
scaler = StandardScaler()
df[numeric_cols] = scaler.fit_transform(df[numeric_cols])

# Step 4: Apply Hierarchical Clustering based on 'effective_literacy_rate_total'
X = df[['effective_literacy_rate_total']]  # Using 'effective_literacy_rate_total' for clustering

# Apply Agglomerative Hierarchical Clustering
hierarchical_clustering = AgglomerativeClustering(n_clusters=3)  # Assuming 3 clusters
df['cluster'] = hierarchical_clustering.fit_predict(X)

# Step 5: Visualizing the clusters
plt.figure(figsize=(10, 6))
sns.scatterplot(x=df['effective_literacy_rate_total'], y=[0] * len(df), hue=df['cluster'], palette='viridis')
plt.title('Hierarchical Clustering based on Effective Literacy Rate')
plt.xlabel('Effective Literacy Rate Total')
plt.yticks([])
plt.show()

# Step 6: View the grouped data
print(df[['city', 'effective_literacy_rate_total', 'cluster']].head())

import scipy.cluster.hierarchy as sch

# Create the linkage matrix
linkage_matrix = sch.linkage(X, method='ward')

# # Plot the dendrogram
# plt.figure(figsize=(10, 7))
# sch.dendrogram(linkage_matrix)
# plt.title('Dendrogram for Hierarchical Clustering')
# plt.xlabel('Cities')
# plt.ylabel('Euclidean Distance')
# plt.show()
