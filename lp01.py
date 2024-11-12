import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.tree import DecisionTreeClassifier, plot_tree
from sklearn.metrics import accuracy_score, classification_report
from sklearn.preprocessing import LabelEncoder
import matplotlib.pyplot as plt

# Load the dataset
# Change the dataset path to its exact path
data = pd.read_csv('/kaggle/input/jaalnockdatasets/madfhantr.csv')

# Drop the identifier column (e.g., Loan_ID)
if 'Loan_ID' in data.columns:
    data = data.drop('Loan_ID', axis=1)

# Handle non-numeric values
# Convert '3+' in the 'Dependents' column to numeric (if 'Dependents' exists)
if 'Dependents' in data.columns:
    data['Dependents'] = data['Dependents'].replace('3+', 3).astype(float)

# Preprocessing
# Fill missing values in numeric columns with the column mean
numeric_cols = data.select_dtypes(include=['float64', 'int64']).columns
data[numeric_cols] = data[numeric_cols].fillna(data[numeric_cols].mean())

# Encode categorical columns
categorical_cols = data.select_dtypes(include=['object']).columns
for col in categorical_cols:
    data[col] = LabelEncoder().fit_transform(data[col])

# Split data
X = data.drop('Loan_Status', axis=1)
y = data['Loan_Status']
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# Train the model
model = DecisionTreeClassifier(max_depth=4, random_state=42)
model.fit(X_train, y_train)

# Evaluate the model
y_pred = model.predict(X_test)
print("Accuracy:", accuracy_score(y_test, y_pred))
print(classification_report(y_test, y_pred))

# Plot the Decision Tree
plt.figure(figsize=(20, 10))
plot_tree(model, feature_names=X.columns, class_names=['No', 'Yes'], filled=True, rounded=True, fontsize=10)
plt.show()