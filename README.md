# solactive
# Curl Commands:
Please use the following curl commands to test “POST /ticks”,”GET /statistics” and “GET /statistics/{instrument_identifier}” functionalities:

-Get one statistics by instrument_identifier
curl -i -H "Content-Type: application/json" \
    -X GET localhost:8080/statistics/2

-List all statistics
curl -i -H "Content-Type: application/json" \
    -X GET localhost:8080/statistics

-Add new tick
curl -i -H "Content-Type: application/json" -X POST -d '{
        "instrument": "UNICRE",
        "price": "16.6",
"timestamp": "600"
    }' localhost:8080/ticks



# Assumptions:

To briefly introduce the project, it’s a rest API application built in Spring Boot. For the parallelism part, being done by multithreading concept. At the end test unit part take cares of the functionality valuation part. It can be used in JAVA 11 or higher. There is also possibility to reduce it to lower versions of JAVA.
 

1.	In multithreading part, it uses only two thread but it can be easily set to any required number of threads.
2.	I was intending to use h2 database to use as in-memory database structure, but as it is being mentioned to not use any kind of database, I used List as the container to store all the tick’s information.
3.	 In statistics calculation part, specifically in volatility and quantile calculation, I tried to use the most efficient structure. In quantile part, It should be used the sorted List of tick’s base on the prices, but as making sort is far more complex than O(1), I just tried to get first %5 pf tick’s. 
4.	In volatility, twap and twap2 calculation as no specific formula being provided, I tried to reach a reasonable formula.


# Improvements:

In my opinion, if I had a chance and time, I could try to use gRPC and compare it with REST in terms of performance and development structure.  


# Did I like the project:
Yes. There was some challenging points that made me thinking and got excited solving them.
