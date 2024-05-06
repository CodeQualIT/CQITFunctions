`mvn clean native:compile -Pnative`

`./target/boxedhello-minimal-app`

```bash
curl --header "Content-Type: application/json" \
--request POST \
--data '{"firstName":"John","lastName":"Johnson"}' \
http://localhost:8080/boxedHello
```