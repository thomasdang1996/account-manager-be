## ACCOUNT MANAGER - BE
### Prerequisites
- Clone and run [Message Broker](https://github.com/thomasdang1996/message-broker.git) before you run this application
- Install [Maven](https://www.baeldung.com/install-maven-on-windows-linux-mac)
### How to run Application
With IDE
1) Clone project: `git clone https://github.com/thomasdang1996/account-manager-be.git`
2) Run `mvn compile -U` to download and generate `CreateAccountPayload` message schema for messaging via message bus
3) Run account-manager-be.
4) Address: http://localhost:8082

Without IDE using command line
1) `git clone https://github.com/thomasdang1996/account-manager-be.git`
2) `mvn spring-boot:run -U`
### Swagger
API yet to be added: http://localhost:8082/swagger-ui/index.html

### Other Apps
Run [PearStore BE]( https://github.com/thomasdang1996/pear-store-be.git) for sending `CreateAccountPayload`


