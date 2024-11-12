#lp19
import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.naive_bayes import GaussianNB
from sklearn.metrics import accuracy_score, confusion_matrix, classification_report
from sklearn.preprocessing import LabelEncoder

# Load the dataset
url = "/kaggle/input/jaalnockdatasets/Social_Network_Ads.csv"
df = pd.read_csv(url)

# Preprocessing the data: Label Encoding 'Gender' column (Male=0, Female=1)
label_encoder = LabelEncoder()
df['Gender'] = label_encoder.fit_transform(df['Gender'])

# Split data into features and target variable
X = df[['Gender', 'Age', 'EstimatedSalary']]  # Features
y = df['Purchased']  # Target

# Split the dataset into training and test sets
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.3, random_state=42)

# Create and train Naïve Bayes model
model = GaussianNB()
model.fit(X_train, y_train)

# Make predictions on the test set
y_pred = model.predict(X_test)

# Evaluate the model
accuracy = accuracy_score(y_test, y_pred)
print(f"Accuracy: {accuracy * 100:.2f}%")

# Confusion matrix
print("\nConfusion Matrix:")
print(confusion_matrix(y_test, y_pred))

# Classification report (precision, recall, f1-score)
print("\nClassification Report:")
print(classification_report(y_test, y_pred))

# Asking user for input to predict 'Purchased' status
print("\nPlease enter the details for prediction:")
gender_input = input("Gender (Male/Female): ")
age_input = int(input("Age: "))
salary_input = float(input("Estimated Salary: "))

# Encode gender input
gender_encoded = label_encoder.transform([gender_input])[0]

# Prepare the input data for prediction
user_input = [[gender_encoded, age_input, salary_input]]

# Predict the purchase status
purchase_prediction = model.predict(user_input)

# Output the prediction
if purchase_prediction == 1:
    print("\nPrediction: The user will purchase the product.")
else:
    print("\nPrediction: The user will not purchase the product.")
