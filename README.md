# apilighthouse🔭


-----------------------

## 👋Introduction
Do you often need to query the IP address of the service? Try apilighthouse, which will help you improve efficiency. Uniformly manage all service URL addresses here.

This is a cloud native cross cloud connector base.
Make the delivery personnel no longer need to configure too much content during the privatization deployment, and improve the product delivery efficiency

## ⚡Quick Start

### Local Version

#### dependent

Make sure you have docker installed (https://www.docker.com/get-started/)

#### step1

```markdown
docker run -itd -p 20080:80 --name=apilighthouse apilighthouse/apilighthouse
```

#### step2

![demo1](https://user-images.githubusercontent.com/103189101/211203987-145e4c46-55e1-4fdd-a38b-3c07dca39b7c.gif)

(Fill in the IP address of the host computer where "apilighthouse" is located)

### SaaS Version With GUI

(🤔 This version is still incomplete.Welcome to commit code)

![img](https://user-images.githubusercontent.com/103189101/211203994-a27f35df-24d1-4250-8388-7825b597d7ec.png)

## How to configure the forwarding target?

### Local Version

#### How to obtain defined forwarding logic?


GET http://(yours ip):20080/list

(Fill in the IP address of the host computer where "apilighthouse" is located)


#### How to add or update forwarding logic?

GET http://(yours ip):20080/add?serviceName=xxxxxx&url=xxxxxx&remarks=xxxxxx

Incoming Parameters

|  name   | remarks  |
|  ----  | ----  |
| serviceName | Target Code (Any name) |
| url | target url |
| remarks | alias |

(Fill in the IP address of the host computer where "apilighthouse" is located)

---------------------------------
# 📫 Connect or Commit

[![Gitter](https://badges.gitter.im/apilighthouse/community.svg)](https://gitter.im/apilighthouse/community?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge)

This project has more than 10 stars, and I firmly believe that this momentum can be maintained

I hope that after a few years, the product will be widely used in the industry. We can have the opportunity to call you to thank you for your contribution to the code submission of this project, and invite you to join us to share our common victory fruits.

# 💬 Question

#### Why not use DNS?

In the era of docker, the container ip will change constantly. It is not easy to specify the container ip of the DNS server for the Docker container. However, using api lighthouse and docker-compose, a common tool for deployment, can easily implement 0 configuration during private deployment.
