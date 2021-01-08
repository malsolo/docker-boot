# Spring boot image creation

Docker with buildback or dockerfiles and facilities for Kubernetes.

See [Whats New in Spring Boot 2.3](https://www.youtube.com/watch?v=WL7U-yGfUXA)

## Spring Boot Maven Plugin

### Create and run an image

See [Packaging OCI Images](https://docs.spring.io/spring-boot/docs/2.4.1/maven-plugin/reference/htmlsingle/#build-image)
to find a new goal: *build-image*

```
$ mvnv spring-boot:build-image -Dspring-boot.build-image.imageName=malsolo/docker-boot:0.0.1-SNAPSHOT

...
[INFO] --- spring-boot-maven-plugin:2.4.1:build-image (default-cli) @ docker-boot ---
[INFO] Building image 'docker.io/malsolo/docker-boot:0.0.1-SNAPSHOT'
[INFO] 
[INFO]  > Pulling builder image 'docker.io/paketobuildpacks/builder:base' 100%
[INFO]  > Pulled builder image 'paketobuildpacks/builder@sha256:4b91576bbf90ee67ccb6a178a345bc5266e4f9eac62d8df580d66ea70ff54e47'
[INFO]  > Pulling run image 'docker.io/paketobuildpacks/run:base-cnb' 100%
[INFO]  > Pulled run image 'paketobuildpacks/run@sha256:7c0092c534e1646fc80d1578fb9cf0ab925af2ad95a7469edf681539cba44c56'
[INFO]  > Executing lifecycle version v0.10.1
[INFO]  > Using build cache volume 'pack-cache-c4ebed762453.build'
[INFO] 
[INFO]  > Running creator
[INFO]     [creator]     ===> DETECTING
[INFO]     [creator]     5 of 18 buildpacks participating
[INFO]     [creator]     paketo-buildpacks/ca-certificates   1.0.1
[INFO]     [creator]     paketo-buildpacks/bellsoft-liberica 6.0.0
[INFO]     [creator]     paketo-buildpacks/executable-jar    3.1.3
[INFO]     [creator]     paketo-buildpacks/dist-zip          2.2.2
[INFO]     [creator]     paketo-buildpacks/spring-boot       3.5.0
[INFO]     [creator]     ===> ANALYZING
[INFO]     [creator]     Previous image with name "docker.io/malsolo/docker-boot:0.0.1-SNAPSHOT" not found
[INFO]     [creator]     ===> RESTORING
[INFO]     [creator]     ===> BUILDING
[INFO]     [creator]     
[INFO]     [creator]     Paketo CA Certificates Buildpack 1.0.1
...
[INFO]     [creator]     *** Images (1eb15efe03cb):
[INFO]     [creator]           docker.io/malsolo/docker-boot:0.0.1-SNAPSHOT
[INFO] 
[INFO] Successfully built image 'docker.io/malsolo/docker-boot:0.0.1-SNAPSHOT'
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  35.880 s
...

$ docker image list
...
malsolo/docker-boot                         0.0.1-SNAPSHOT      1eb15efe03cb   41 years ago    300MB
```

Now you can run the container:

```
$ docker run -it -p8080:8080 malsolo/docker-boot:0.0.1-SNAPSHOT
```

And verify that the application is up and running:

```
$ curl http://localhost:8080/hello
Hello World
```

### Inspect the image

By using [dive](https://github.com/wagoodman/dive#installation) we'll take a look to the image

```
$ dive malsolo/docker-boot:0.0.1-SNAPSHOT
```

### Create a Dockerfile

It's also possibleto create your own Dockerfile as explained at [Building Docker images](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-container-images)
with a reference to the spring boot maven plugin [Layered Jars section](https://docs.spring.io/spring-boot/docs/2.4.1/maven-plugin/reference/htmlsingle/#repackage-layers) that explains about layering the application 
for creating a better image:

Coming back to spring boot documentation, you can find instructions for [Writing the Dockerfile](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#writing-the-dockerfile), for instance, 
how to see or extract the layers for creating the image:

```
$ java -Djarmode=layertools -jar target/docker-boot-0.0.1-SNAPSHOT.jar list
dependencies
spring-boot-loader
snapshot-dependencies
application
``` 

## Kubernetes features

### Graceful shutdown

See [Graceful shutdown support](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-graceful-shutdown)

## Additional resources

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.1/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.4.1/maven-plugin/reference/html/#build-image)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/2.4.1/reference/htmlsingle/#production-ready)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)

