#lp16
import pandas as pd
from mlxtend.frequent_patterns import apriori, association_rules
from mlxtend.preprocessing import TransactionEncoder

# Step 1: Data Preprocessing
# Load the dataset
data_path = "/kaggle/input/jaalnockdatasets/Order2.csv"  # Replace with the correct path to your dataset
transactions = pd.read_csv(data_path, header=None)

# Data cleaning: Remove NaN or any non-item entries from each transaction
# Convert the entire DataFrame to a list of transactions
transactions = transactions.apply(lambda row: [str(item) for item in row if str(item) != 'nan'], axis=1).tolist()

# Step 2: Prepare the data for the Apriori algorithm
# Convert transactions into a one-hot encoded format
te = TransactionEncoder()
te_ary = te.fit(transactions).transform(transactions)
df = pd.DataFrame(te_ary, columns=te.columns_)

# Step 3: Apply Apriori Algorithm
# Set minimum support to find frequent itemsets (example: 0.02)
frequent_itemsets = apriori(df, min_support=0.02, use_colnames=True)

# Step 4: Apply Association Rules (example: min_threshold=0.7)
rules = association_rules(frequent_itemsets, metric="lift", min_threshold=0.7)

# Step 5: Visualize the results
print(rules)
