# JAXRS & Conversation

## Execution & deployment

### Start a server and deploy to it
In order to start a fresh new server and deploy to it

```
mvn clean wildfly:run
```

You will have to stop the server afterwards

```
mvn wildfly:shutdown
```

### Deploy to an already running server

If you want to deploy to a local wildfly instance

```
mvn clean wildfly:deploy
```

## Usage

1. Show that no conversation exists  

    ```
    [~]> http -v -j --session=mysession localhost:8080/conversation-jaxrs/resources/jaxrs/view

    GET /conversation-jaxrs/resources/jaxrs/view HTTP/1.1
    Accept: application/json, */*
    Accept-Encoding: gzip, deflate
    Connection: keep-alive
    Content-Type: application/json
    Host: localhost:8080
    User-Agent: HTTPie/0.9.9

    HTTP/1.1 204 No Content
    Date: Tue, 17 Jan 2017 13:56:30 GMT
    Server: WildFly/10
    X-Powered-By: Undertow/1
    ```

1. Create a conversation  
    ```
    [~]> http -v -j --session=mysession localhost:8080/conversation-jaxrs/resources/jaxrs/start

    GET /conversation-jaxrs/resources/jaxrs/start HTTP/1.1
    Accept: application/json, */*
    Accept-Encoding: gzip, deflate
    Connection: keep-alive
    Content-Type: application/json
    Host: localhost:8080
    User-Agent: HTTPie/0.9.9

    HTTP/1.1 200 OK
    Connection: keep-alive
    Content-Length: 399
    Content-Type: application/json
    Date: Tue, 17 Jan 2017 13:57:24 GMT
    Server: WildFly/10
    Set-Cookie: JSESSIONID=T5c5TrGa3hyVIISCztAhnwEZpnlZcWJxql2xPV-P.morli894; path=/conversation-jaxrs
    X-Powered-By: Undertow/1

    {
        "action": "started new conversation: 1",
        "data": {
            "time-local": "2017-01-17T14:57:24.353",
            "time-utc": "2017-01-17T13:57:24.353Z",
            "time-zoned": "2017-01-17T14:57:24.353+01:00[Europe/Paris]"
        },
        "links": {
            "start": "http://localhost:8080/conversation-jaxrs/resources/start",
            "stop": "http://localhost:8080/conversation-jaxrs/resources/stop",
            "view": "http://localhost:8080/conversation-jaxrs/resources/start?cid=1"
        }
    }
    ```

    Here already we can see that the JEE server created an http session to store the newly created conversation (n°1) and asks the client to keep the sessionId as a Cookie

1. Create another conversation  
    ```
    [~]> http -v -j --session=mysession localhost:8080/conversation-jaxrs/resources/jaxrs/start

    GET /conversation-jaxrs/resources/jaxrs/start HTTP/1.1
    Accept: application/json, */*
    Accept-Encoding: gzip, deflate
    Connection: keep-alive
    Content-Type: application/json
    Cookie: JSESSIONID=T5c5TrGa3hyVIISCztAhnwEZpnlZcWJxql2xPV-P.morli894
    Host: localhost:8080
    User-Agent: HTTPie/0.9.9

    HTTP/1.1 200 OK
    Connection: keep-alive
    Content-Length: 399
    Content-Type: application/json
    Date: Tue, 17 Jan 2017 13:58:32 GMT
    Server: WildFly/10
    X-Powered-By: Undertow/1

    {
        "action": "started new conversation: 2",
        "data": {
            "time-local": "2017-01-17T14:58:32.905",
            "time-utc": "2017-01-17T13:58:32.905Z",
            "time-zoned": "2017-01-17T14:58:32.905+01:00[Europe/Paris]"
        },
        "links": {
            "start": "http://localhost:8080/conversation-jaxrs/resources/start",
            "stop": "http://localhost:8080/conversation-jaxrs/resources/stop",
            "view": "http://localhost:8080/conversation-jaxrs/resources/start?cid=2"
        }
    }
    ```

1. View conversation n°1
    ```
    [~]> http -v -j --session=mysession localhost:8080/conversation-jaxrs/resources/jaxrs/view cid==1

    GET /conversation-jaxrs/resources/jaxrs/view?cid=1 HTTP/1.1
    Accept: application/json, */*
    Accept-Encoding: gzip, deflate
    Connection: keep-alive
    Content-Type: application/json
    Cookie: JSESSIONID=T5c5TrGa3hyVIISCztAhnwEZpnlZcWJxql2xPV-P.morli894
    Host: localhost:8080
    User-Agent: HTTPie/0.9.9

    HTTP/1.1 200 OK
    Connection: keep-alive
    Content-Length: 396
    Content-Type: application/json
    Date: Tue, 17 Jan 2017 13:59:43 GMT
    Server: WildFly/10
    X-Powered-By: Undertow/1

    {
        "action": "accessing data of conversation:",
        "data": {
            "time-local": "2017-01-17T14:57:24.353",
            "time-utc": "2017-01-17T13:57:24.353Z",
            "time-zoned": "2017-01-17T14:57:24.353+01:00[Europe/Paris]"
        },
        "links": {
            "start": "http://localhost:8080/conversation-jaxrs/resources/start",
            "stop": "http://localhost:8080/conversation-jaxrs/resources/stop",
            "view": "http://localhost:8080/conversation-jaxrs/resources/view"
        }
    }
    ```

    We can see that we have correctly retrieved the first created conversation data by looking at the time registered within the conversation scoped data object.

1. End a conversation, the n°1 for example
    ```
    [~]> http -v -j --session=mysession localhost:8080/conversation-jaxrs/resources/jaxrs/stop cid==1

    GET /conversation-jaxrs/resources/jaxrs/stop?cid=1 HTTP/1.1
    Accept: application/json, */*
    Accept-Encoding: gzip, deflate
    Connection: keep-alive
    Content-Type: application/json
    Cookie: JSESSIONID=T5c5TrGa3hyVIISCztAhnwEZpnlZcWJxql2xPV-P.morli894
    Host: localhost:8080
    User-Agent: HTTPie/0.9.9

    HTTP/1.1 200 OK
    Connection: keep-alive
    Content-Length: 111
    Content-Type: application/json
    Date: Tue, 17 Jan 2017 14:02:13 GMT
    Server: WildFly/10
    X-Powered-By: Undertow/1

    {
        "action": "conversation 1 closed",
        "links": {
            "start": "http://localhost:8080/conversation-jaxrs/resources/start"
        }
    }
    ```
