# Autocomplete

This project is my implementation of the common Autocomplete algorithm - used in many modern applications. When the user types text, the application suggests possible completions for that text.
Each term will have a predetermined, constant weight/likelihood, whereas actual autocomplete algorithms might change a term's likelihood based on previous searches.
We will only consider terms which start with the user query, whereas actual autocomplete algorithms (such as the web browser example above) might consider terms which contain but do not start with the query.
