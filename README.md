# JEE Samples

This project is a collection of self contained JEE project serving as examples or demo of JEE functionalities.

## Samples

### CDI
- [JAXRS & Conversation](conversation-jaxrs/README.md)  
  _demo usage of `javax.enterprise.context.Conversation` & `@ConversationScoped` beans inside a JAXRS application_ 
- [Servlet & Conversation](conversation-servlet/README.md)  
  _demo usage of `javax.enterprise.context.Conversation` & `@ConversationScoped` beans inside a Servlet based application_ 

## Build

__Integration tests on embedded server__

- `mvn clean install`

__Integration tests on remote server__

- `mvn -Premote clean install`

## Release

This project uses [jgitver-maven-plugin](https://github.com/jgitver/jgitver-maven-plugin) to handle its versioning, check [documentation](https://github.com/jgitver/jgitver-maven-plugin#jgitver-maven-plugin) for further details.


Once your are satisfied of the HEAD commit (ie you performed all your tests)

- `git tag -a -m "release X.Y.Z, additional reason" X.Y.Z`
- `mvn -Prelease -DskipTests deploy`
- `git push --follow-tags origin master`

## Other JEE Samples

- by [Arun Gupta](https://github.com/arun-gupta): https://github.com/javaee-samples
- by [Olivier Cailloux](https://github.com/oliviercailloux): https://github.com/oliviercailloux/samples
- by [Apache TomEE](https://tomee.apache.org/index.html): https://tomee.apache.org/examples-trunk/