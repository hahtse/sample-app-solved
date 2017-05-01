# Sample-App

*Spring-boot* sample app which needs to get flesh on the bones.

Currently 4 acceptance tests are running red.

## Build and Test
```
$ ./gradlew clean build

or in Windows
$ gradlew.bat clean build
```


## Development
Starts a local SpringBoot Application @ http://localhost:8080
```
$ ./gradlew bootRun

or in Windows
$ gradlew.bat bootRun
```

## Tasks
There are some tasks defined which are described as acceptance tests for the API.

### Task 1
Make Task01Test.java run green by implementing the missing endpoint.

It should return a contact as JSON for the given Id and a http error code if an 
unknown id is used.

No real persistence is needed. The contract test should not be changed.  

### Task2
Make Task02Test.java run green by implementing the missing endpoint.

Create a method which handles post requests for contacts. Ensure that only valid contacts (with the 
mandatory fields) can be saved.
 
No real persistence is needed.

### Task 3 (optional)
Implement a parent-child relation for contacts. When saving a contact A with a related contact B, ensure that B already exists.


## Background
The tests are generated from the contract definition in /test/resources/contracts/contacts/. They could help
 to better understand the expected output.

