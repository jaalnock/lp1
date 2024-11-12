import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.naive_bayes import GaussianNB
from sklearn.metrics import accuracy_score, classification_report, confusion_matrix

# Load the dataset
data = pd.read_csv('/kaggle/input/jaalnockdatasets/NaiveBayes.csv')

# Preprocessing
# Separate features and target variable
X = data[['Age', 'Salary']]
y = data['Purchased']

# Split the data into training and testing sets
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# Train the Gaussian Naïve Bayes model
model = GaussianNB()
model.fit(X_train, y_train)

# Make predictions on the test set
y_pred = model.predict(X_test)

# Evaluate the model
accuracy = accuracy_score(y_test, y_pred)
report = classification_report(y_test, y_pred)
conf_matrix = confusion_matrix(y_test, y_pred)

# Output the results
print("Model Evaluation:")
print("Accuracy:", accuracy)
print("\nClassification Report:\n", report)
print("\nConfusion Matrix:\n", conf_matrix)

# Taking user input and making a prediction
try:
    user_age = int(input("Enter Age: "))
    user_salary = int(input("Enter Salary: "))

    # Convert user input to a DataFrame with matching column names
    user_data = pd.DataFrame([[user_age, user_salary]], columns=['Age', 'Salary'])

    # Predict purchase decision based on user input
    user_prediction = model.predict(user_data)
    print("\nPrediction: Purchased" if user_prediction[0] == 1 else "\nPrediction: Not Purchased")
except ValueError:
    print("Please enter valid numeric values for Age and Salary.")
