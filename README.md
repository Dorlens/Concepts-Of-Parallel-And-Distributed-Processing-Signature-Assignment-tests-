# Concepts-Of-Parallel-And-Distributed-Processing-Signature-Assignment-tests-
# TCP Client-Server Application

## Overview
This project implements a simple TCP client-server application in Java.  
The client sends a text message to the server, the server swaps two words in the message, and the altered message is sent back to the client.

---

## Features
- Sends a single line message from client to server
- Swaps two random words in the message on the server
- Sends the modified message back to the client
- Demonstrates basic TCP socket programming in Java
- Includes unit tests for the word-swapping logic

---

## Requirements
- Java JDK 11 or higher
- JUnit 4.13.2 and Hamcrest 1.3 (for unit tests)

---

## How to Compile

### Compile Server and Client
javac TCPServer.java
javac TCPClient.java

### Complie Unit tests
javac -cp .:junit-4.13.2.jar:hamcrest-core-1.3.jar junit_wordSwap_tests.java TCPServer.java

### Run unit tests 

java -cp .:junit-4.13.2.jar:hamcrest-core-1.3.jar org.junit.runner.JUnitCore junit_wordSwap_tests

---

## System/Intergation Testing 
- Tested by running the server first, then the client.
- Client sent the message, server alters it, and client receives the modified message.


