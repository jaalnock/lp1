#lp17
import pandas as pd
from mlxtend.frequent_patterns import apriori, association_rules
from mlxtend.preprocessing import TransactionEncoder
import matplotlib.pyplot as plt

# Step 1: Data Preprocessing
# Load the dataset
data_path = "/kaggle/input/jaalnockdatasets/Oder3.csv"  # Update the path to your dataset
df = pd.read_csv(data_path)

# Generate transactions by grouping items by TransactionNo
transactions = df.groupby('TransactionNo')['Items'].apply(list).tolist()

# Step 2: Prepare the data for the Apriori algorithm
# Convert transactions into a one-hot encoded format
te = TransactionEncoder()
te_ary = te.fit(transactions).transform(transactions)
df_onehot = pd.DataFrame(te_ary, columns=te.columns_)

# Step 3: Apply the Apriori Algorithm
# Set minimum support to find frequent itemsets (example: 0.02)
frequent_itemsets = apriori(df_onehot, min_support=0.02, use_colnames=True)

# Step 4: Apply Association Rules
# Apply the association rules with a minimum lift threshold of 1.0
rules = association_rules(frequent_itemsets, metric="lift", min_threshold=1.0)

# Step 5: Visualize the results
# Print the rules
print(rules)

# Visualize the top 10 rules based on lift
top_rules = rules.nlargest(10, 'lift')
plt.figure(figsize=(10, 6))
plt.barh(top_rules['antecedents'].astype(str), top_rules['lift'])
plt.xlabel('Lift')
plt.title('Top 10 Association Rules Based on Lift')
plt.show()

