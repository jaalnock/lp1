#lp13
# Import necessary libraries
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns
from sklearn.model_selection import train_test_split
from sklearn.linear_model import LinearRegression
from sklearn.metrics import mean_squared_error, r2_score

# Load the dataset
data = pd.read_csv('/kaggle/input/jaalnockdatasets/advertising.csv')

# Display the first few rows of the dataset
print(data.head())

# Extract 'Radio' as the predictor variable and 'Sales' as the response variable
X = data[['Radio']]
y = data['Sales']

# Split the data into training and test sets
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# Initialize and train the linear regression model
model = LinearRegression()
model.fit(X_train, y_train)

# Make predictions on the test set
y_pred = model.predict(X_test)

# Calculate model metrics
mse = mean_squared_error(y_test, y_pred)
r2 = r2_score(y_test, y_pred)

# Print regression results
print(f"Mean Squared Error: {mse}")
print(f"R-squared: {r2}")
print(f"Intercept: {model.intercept_}")
print(f"Coefficient (Slope): {model.coef_[0]}")

# Plot the regression line with data points
plt.figure(figsize=(10, 6))
sns.scatterplot(x='Radio', y='Sales', data=data, color='blue', label='Actual Data')
plt.plot(X_test, y_pred, color='red', label='Regression Line')
plt.xlabel('Radio Advertising Spend')
plt.ylabel('Sales')
plt.title('Simple Linear Regression: Radio Advertising vs. Sales')
plt.legend()
plt.show()
