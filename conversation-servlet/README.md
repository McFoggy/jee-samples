# Web Servlet & Conversation

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

- Open your browser on [http://localhost:8080/conversation-servlet/view](http://localhost:8080/conversation-servlet/view)
- play with the links
    ![WebServlet with conversation](src/doc/images/conversation-servlet.png?raw=true "conversation webservlet")
