import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns
from sklearn.linear_model import LinearRegression
from sklearn.model_selection import train_test_split
from sklearn.metrics import mean_squared_error, r2_score

# Step 1: Load the dataset
file_path = '/kaggle/input/jaalnockdatasets/1.01. Simple linear regression.csv'
df = pd.read_csv(file_path)

# Step 2: Data Preprocessing
# Check for missing values
print(df.isnull().sum())

# Inspect the first few rows of the data
print(df.head())

# Step 3: Exploratory Data Analysis (EDA)
# Plotting the data
plt.figure(figsize=(8,6))
sns.scatterplot(x='SAT', y='GPA', data=df)
plt.title('Scatter Plot: SAT Score vs GPA')
plt.xlabel('SAT Score')
plt.ylabel('GPA')
plt.show()

# Step 4: Correlation
correlation = df.corr()
print(f"Correlation between SAT and GPA: {correlation.iloc[0, 1]}")

# Step 5: Building the Simple Linear Regression Model
X = df[['SAT']]  # Independent variable (SAT score)
y = df['GPA']    # Dependent variable (GPA)

# Splitting the data into training and testing sets (80% train, 20% test)
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# Initialize the Linear Regression model
model = LinearRegression()

# Train the model
model.fit(X_train, y_train)

# Predictions
y_pred = model.predict(X_test)

# Step 6: Model Evaluation
print(f"Intercept: {model.intercept_}")
print(f"Coefficient: {model.coef_[0]}")

# Evaluating the model
mse = mean_squared_error(y_test, y_pred)
r2 = r2_score(y_test, y_pred)
print(f"Mean Squared Error: {mse}")
print(f"R^2 Score: {r2}")

# Step 7: Plotting the regression line
plt.figure(figsize=(8,6))
sns.scatterplot(x='SAT', y='GPA', data=df, color='blue')
plt.plot(X_test, y_pred, color='red')  # Regression line
plt.title('Linear Regression: SAT Score vs GPA')
plt.xlabel('SAT Score')
plt.ylabel('GPA')
plt.show()
