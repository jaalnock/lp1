# Import necessary libraries
import pandas as pd
from sklearn.preprocessing import LabelEncoder, StandardScaler
from sklearn.cluster import KMeans
import matplotlib.pyplot as plt
import seaborn as sns

# Load the dataset
url = '/kaggle/input/jaalnockdatasets/Social_Network_Ads.csv'  # Replace with the correct file path
data = pd.read_csv(url)

# Check the first few rows of the dataset
print(data.head())

# Data Preprocessing
# Label Encoding the 'Gender' column
label_encoder = LabelEncoder()
data['Gender'] = label_encoder.fit_transform(data['Gender'])

# Feature Scaling
scaler = StandardScaler()
data_scaled = scaler.fit_transform(data[['Age', 'EstimatedSalary']])

# Apply K-Means clustering
kmeans = KMeans(n_clusters=2, init='k-means++', max_iter=300, n_init=10, random_state=42)
data['Cluster'] = kmeans.fit_predict(data_scaled)

# Visualize the clusters
plt.figure(figsize=(8, 6))
sns.scatterplot(x=data['Age'], y=data['EstimatedSalary'], hue=data['Cluster'], palette='viridis', s=100, alpha=0.7)
plt.title("K-Means Clustering based on EstimatedSalary", fontsize=15)
plt.xlabel("Age")
plt.ylabel("Estimated Salary")
plt.legend(title='Cluster')
plt.show()

# Display the dataset with cluster labels
print(data[['User ID', 'Gender', 'Age', 'EstimatedSalary', 'Cluster']].head())
