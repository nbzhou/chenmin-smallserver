#OAuth2.0  授权中心

在service 有一个简单的登录页面（图个方便） 用与implicit grant type（swagger 后台）

需要更改存储方式 修改这个bean就好了

``` java
  @Bean
  public TokenStore tokenStore() {
       return new JdbcTokenStore(dataSource);
  }
```