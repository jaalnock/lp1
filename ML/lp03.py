import pandas as pd
from sklearn.preprocessing import LabelEncoder, StandardScaler
from sklearn.cluster import KMeans
import matplotlib.pyplot as plt

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

# Label Encoding for categorical columns (for non-numeric features)
label_encoder = LabelEncoder()
for col in categorical_cols:
    df[col] = label_encoder.fit_transform(df[col])

# Step 3: Data Transformation

# If necessary, normalize or standardize the data
# Here we standardize numeric features to bring them to the same scale
scaler = StandardScaler()
df[numeric_cols] = scaler.fit_transform(df[numeric_cols])

# Step 4: Apply K-Means clustering based on 'total_graduates' (you can select more columns if needed)
# Extract the relevant feature(s) for clustering
X = df[['total_graduates']]

# Apply K-Means clustering (let's choose 3 clusters as an example)
kmeans = KMeans(n_clusters=3, random_state=42)
df['cluster'] = kmeans.fit_predict(X)

# Step 5: Analyzing the results
# Visualize the clusters
plt.scatter(df['total_graduates'], [0] * len(df), c=df['cluster'], cmap='viridis')
plt.xlabel('Total Graduates')
plt.title('K-Means Clustering based on Total Graduates')
plt.show()

# Step 6: View the grouped data
print(df[['city', 'total_graduates', 'cluster']].head())
