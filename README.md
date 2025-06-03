http://localhost:8080/swagger-ui/index.html
1. add logging to project log4j2 - done
  1.1 convert this into slf4j - using streams
2. Custom exception handling for 4XX and 5XX - almost done(check)
  2.1 manage invalid uri - done
  2.2 learn the difference b/w uri & url
  2.3 invalid http verb management
  2.4 invalid argument management - done
3. Swagger detailed all possible exceptions and outcomes - done
4. pagination for get all employess end point url - done
  4.1 learn metadata of pagination - done (changed the response body)
5. use streams for filtering end point - add new end point for search by name - done
  5.1 add pagination to filterByName end point
  5.2 implement predicate
7. getById and getAll should be respond https://developer.mozilla.org/en-US/docs/Web/HTTP/Reference/Status/204 - instead of 404 - done
8. write Junit test cases for each case(all end point urls) it should be 100% test coverage
9. make database pertisance to file system(https://stackoverflow.com/questions/43470295/how-to-store-h2-database-file-into-project-directory) <- How to store H2 database file into project directory
10. database versioning (https://stackoverflow.com/questions/43470295/how-to-store-h2-database-file-into-project-directory)
11. learn about reactive java
12. change the datatype of salary field - done
13. Check the workflow action maven build - error in Dev branch
14. Add the project to the Docker - done
15. Learn JPA Criteria Queries
16. define swagger file -> learn to read swagger file (https://editor.swagger.io/)
17. write Scheduled which runs onces in 24 hours and sends email of newly created employess of 24 hours
18. learn about kafka + springboot
