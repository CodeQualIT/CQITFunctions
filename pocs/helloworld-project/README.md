`cd helloworld-app`

`mvn clean native:compile -Pnative`

`./target/helloworld-app`

```bash
curl --header "Content-Type: application/json" \
--request POST \
--data '{"firstName":"John","lastName":"Johnson"}' \
http://localhost:8080/helloWorld
```
