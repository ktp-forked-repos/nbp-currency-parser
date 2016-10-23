### nbp-currency-parser
NBP currency parser

### build
 mvn clean package
 
###run 
    
 java -jar target/nbp-currency-parser-1.0-SNAPSHOT-jar-with-dependencies.jar currency_code start_date end_date
 
 **currency_code** - https://en.wikipedia.org/wiki/ISO_4217
 **start_date** - yyyy-mm-dd
 **end_date** - yyyy-mm-dd
 
 *example:*
 java -jar target/nbp-currency-parser-1.0-SNAPSHOT-jar-with-dependencies.jar EUR 2016-10-01 2016-10-14
 
 *result:*
 4.2490
 0.0105

