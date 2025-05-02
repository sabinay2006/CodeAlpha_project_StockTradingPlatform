# CodeAlpha_project_StockTradingPlatform

Name: sabinaya mahapatra

Company: CODE ALPHA

ID: CA/MA1/4435

Domain: Java Programming

Duration: MAY to JUNE, 2025

Overview of the Project
Project: Stock Trading Platform

Objective: To develop a console-based Java application that simulates a stock trading platform, allowing users to view market data, buy and sell stocks, manage their portfolio, and track transaction history.

Key Activities
Market Data Display: Show available stocks with their symbols, names, and prices.
Buy Stocks: Allow users to purchase stocks, updating their portfolio and recording the transaction.
Sell Stocks: Enable users to sell stocks they own, updating their portfolio and recording the transaction.
Portfolio Management: Display the user's current stock holdings.
Transaction History: Maintain a record of all buy and sell transactions.
Portfolio Reset: Clear all stock holdings in the portfolio.
Transaction History Clear: Clear the transaction history.

Technologies Used
Java: The programming language used for development.
Scanner: Used for reading user input from the console.
HashMap: Used for storing stock data and portfolio holdings.
ArrayList: Used for storing transaction history.
LocalDateTime & DateTimeFormatter: Used for generating timestamps for transactions.

Program Structure
Classes
StockTradingPlatform:

main(String[] args): Main method to execute the program. Manages the main menu loop and user interactions.

Market:
stocks (HashMap<String, Stock>): Stores stock information (symbol as key).
Constructor: Initializes the market with sample stocks.
addStock(Stock stock): Adds a stock to the market.
stockExists(String symbol): Checks if a stock with the given symbol exists.
getStock(String symbol): Retrieves a Stock object by its symbol.
displayMarket(): Displays the list of available stocks and their prices.

Portfolio:
holdings (HashMap<String, Integer>): Stores the user's stock holdings (symbol as key, quantity as value).
addStock(String symbol, int qty): Adds stocks to the portfolio or increases the quantity.
removeStock(String symbol, int qty): Removes stocks from the portfolio or decreases the quantity.
ownsStock(String symbol): Checks if the user owns any shares of the given stock.
displayPortfolio(): Displays the user's current portfolio holdings.
resetPortfolio(): Clears all stock holdings in the portfolio.

Stock:
symbol (String): The stock symbol (e.g., AAPL).
name (String): The name of the company (e.g., Apple Inc.).
price (double): The current price of the stock.
Constructor: Initializes a Stock object.
Getters for symbol, name, and price.
setPrice(double newPrice): Updates the price of the stock.
toString(): Returns a formatted string representation of the stock.

Transaction:
type (TransactionType): The type of transaction (BUY or SELL).
stockSymbol (String): The symbol of the stock involved.
quantity (int): The number of shares traded.
pricePerShare (double): The price per share at the time of the transaction.
timestamp (String): The date and time of the transaction.
Constructor: Initializes a Transaction object.
generateTimestamp(): Generates a timestamp string.
toString(): Returns a formatted string representation of the transaction.

TransactionHistory:
transactions (ArrayList): Stores a list of Transaction objects.
addTransaction(Transaction transaction): Adds a transaction to the history.
displayHistory(): Displays the transaction history.
clearHistory(): Clears the transaction history.
TransactionType (enum):
Defines the possible transaction types: BUY and SELL.
Compilation and Execution
Prerequisites
Java Development Kit (JDK) installed.
Compilation
Save the code as StockTradingPlatform.java.

Open a command prompt or terminal.

Compile the code:

javac StockTradingPlatform.java
Execution
Execute the compiled code:

java StockTradingPlatform

Usage
The program displays a welcome message and the main menu.
Users can select options from the menu to:
View market data (option 1).
Buy stocks (option 2).
Sell stocks (option 3).
View their portfolio (option 4).
View transaction history (option 5).
Reset their portfolio (option 6).
Clear transaction history (option 7).
Exit the program (option 8).
The program prompts the user for necessary input (e.g., stock symbol, quantity) based on their selection.
The program displays appropriate output (e.g., market data, transaction confirmations, portfolio details)


Future Work
Data Persistence: Implement functionality to save and load market data, portfolio holdings, and transaction history from files (e.g., CSV, JSON) or a database.
Graphical User Interface (GUI): Develop a graphical interface using libraries like Swing or JavaFX for a more user-friendly and interactive experience.
Real-Time Data Integration: Integrate with an external API to fetch real-time stock prices instead of using static data.
Advanced Order Types: Implement support for different order types (e.g., limit orders, stop-loss orders).
User Accounts: Add user authentication and management features to support multiple users.
Charting and Visualization: Include features to display stock price charts and visualize portfolio performance.
Portfolio Analysis: Implement tools to analyze portfolio performance, calculate returns, and assess risk.
Error Handling and Validation: Enhance error handling and input validation to make the application more robust.
Multi-Threading: Use multi-threading to improve performance, especially when handling real-time data or complex calculations.
Automated Trading Strategies: Explore the possibility of adding features for users to implement and test automated trading strategies.
