# PickUp SpringBoot REST API **Facebook**
## PickUp
### Purpose
1. Understand OAuth. 
2. Using facebook account information
3. Implementing a PickUp Backend.

### Technical Stack
* Spring Boot(Maven)
* MyBatis
* MySQL
* OAuth
* REST API
* Swagger


## Spring Boot
**HttpRequest -> Controller -> Service -> Mapper -> MySQL**
### Applicatin.java
Main Method in Spring Boot.
```java
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
```

### GlobalExceptionHandler.java
400, 401, 404, 500, etc...
Handle exceptions and Return Httpstatus.
```java
@RestControllerAdvice
@RestController
public class GlobalExceptionHandler implements ErrorController {

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void baseException (Exception e) {e.printStackTrace();}

}
```

### JWTInterceptor.java
JWT intercepts all requests.
```java
@Component
public class FacebookToken {

    public String isUsable(String facebook) {
        Map<String, Object> map;
        ObjectMapper mapper = new ObjectMapper();
        RestTemplate restTemplate = new RestTemplate();

        URI uri = UriComponentsBuilder.newInstance().scheme("https").host("graph.facebook.com")
                .path("/me")
                .queryParam("fields", "id")
                .queryParam("access_token", facebook)
                .build()
                .encode()
                .toUri();
    }

}
```

### MVCConfig.java
Solve CORS Issues.
```java
@Component
public class CORSFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setHeader("Access-Control-Allow-Method", "POST, GET, PUT, DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Max-Age", "3600");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "facebook");

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

}
```

### SqlSession.java
Creating SQL Session.
```java
@Configuration
public class SqlSession {

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);

        Resource[] res = new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml");
        sqlSessionFactoryBean.setMapperLocations(res);

        sqlSessionFactoryBean.setTypeAliases(new Class<?>[] { UserModel.class, PostModel.class, ReplyModel.class, FileModel.class});

        return sqlSessionFactoryBean.getObject();

    }

}
```

### SwaggerConfig.java
Swagger Configure
```java
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.nomsa.bbs.Controller"))
                .paths(PathSelectors.any())
                .build();
    }

}
```


## DataBase
### User
Column | Datatype | PK | NN | UQ | UN | AI | Default
--- | --- | --- | --- | --- | --- | --- | ---
id | VARCHAR(16) | Yes | Yes | Yes |  |  |
name | VARCHAR(70) |  | Yes | Yes |  |  |
phone | VARCHAR(15) |  | Yes |  |  |  |
email | VARCHAR(255) |  | Yes |  |  |  |
photo | LONGBLOB |  | Yes |  |  |  |

### Party
Column | Datatype | PK | NN | UQ | UN | AI | Default
--- | --- | --- | --- | --- | --- | --- | --
index | INT(11) | Yes | Yes |  | Yes | Yes | 
status | VHARCHAR(10) |  | Yes |  |  |  | 
departure | VARCHAR(255) |  | Yes |  |  |  | 
destination | VARCHAR(255) |  | Yes |  |  |  |
date | VARCHAR(10) |  | Yes |  |  |  | 
time | VARCHAR(5) |  | Yes |  |  |  |
recruit | TINYINT(4) |  | Yes |  | Yes |  |
attend | TINYINT(4) |  | Yes |  | Yes |  |
title | VARCHAR(50) |  | Yes |  |  |  |
content | VARCHAR(300) |  |  |  |  |  | NULL

### Log
Column | Datatype | PK | NN | UQ | UN  | AI | Default
--- | --- | --- | --- | --- | --- | --- | --
user | VARCHAR(16) |  | Yes |  |  |  | 
party | INT(11) |  | Yes |  | Yes |  | 
status | VARCHAR(10) |  | Yes |  |  |  | 


## Histories
    2018.09.01. ~ 2018.09.01. Default Setting.
    2018.09.02. ~ 2018.09.02. Define REST API.
    2018.09.03. ~ 2018.09.04. Define Database.
    2018.09.05. ~ 2018.09.09. Facebook OAuth.
    2018.09.10. ~ 2018.09.10. User.
    2018.09.11. ~ 2018.09.11. Party.
    2018.09.12. ~ 2018.09.12. Log.
