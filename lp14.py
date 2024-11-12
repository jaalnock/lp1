#lp14
# Import necessary libraries
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns
from sklearn.model_selection import train_test_split
from sklearn.linear_model import LinearRegression
from sklearn.metrics import mean_squared_error, r2_score

# Load dataset
data = pd.read_csv('/kaggle/input/jaalnockdatasets/advertising.csv')

# Check for null values (Data Pre-processing)
print("Checking for null values:\n", data.isnull().sum())

# Select the feature and target variable
X = data[['Newspaper']]
y = data['Sales']

# Split data into training and testing sets
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# Create and train the model
model = LinearRegression()
model.fit(X_train, y_train)

# Make predictions
y_pred = model.predict(X_test)

# Print regression results
print("Intercept:", model.intercept_)
print("Coefficient:", model.coef_[0])
print("Mean Squared Error:", mean_squared_error(y_test, y_pred))
print("R-squared:", r2_score(y_test, y_pred))

# Plot the regression line
plt.figure(figsize=(10, 6))
plt.scatter(X, y, color='blue', label="Actual Data")
plt.plot(X_test, y_pred, color='red', label="Regression Line")
plt.xlabel("Newspaper Advertising Spending")
plt.ylabel("Sales")
plt.title("Simple Linear Regression: Newspaper Spending vs Sales")
plt.legend()
plt.show()
