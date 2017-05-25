这个教程主要包括以下部分：
1，基础SSM(Spring+SpringMVC+Mybatis)搭建教程
2，集成Redis
3，[Redis Sentinel高可用方案（基于docker）](http://blog.csdn.net/KilluaZoldyck/article/details/72720953)
4，Druid

该教程写完可以当作一个脚手架以后直接使用，很方便，同时会讲解一下缓存方案，为下面搭建一个分布式高可用的系统先做点铺垫，源码以及全部上传到git上面，https://github.com/wacxt/pretty_ssm ，下面开始
（1）首先是项目结构和程序架构图：
![这里写图片描述](http://img.blog.csdn.net/20170525085527668?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvS2lsbHVhWm9sZHljaw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
![这里写图片描述](http://img.blog.csdn.net/20170524233844898?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvS2lsbHVhWm9sZHljaw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

(2)pom.xml

```
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>pretty_ssm</groupId>
  <artifactId>pretty_ssm</artifactId>
  <packaging>war</packaging>
  <version>0.0.1-SNAPSHOT</version>
  <name>pretty_ssm Maven Webapp</name>
  <url>http://maven.apache.org</url>
  
  <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.build.timestamp.format>yyyyMMdd</maven.build.timestamp.format>
        <spring.version>4.2.0.RELEASE</spring.version>
    </properties>
  
  <dependencies>
        <!--common start -->
        <dependency>
            <groupId>com.google.guava</groupId>
            <artifactId>guava</artifactId>
            <version>18.0</version>
        </dependency>

        <dependency>
            <groupId>log4j</groupId>
            <artifactId>log4j</artifactId>
            <version>1.2.17</version>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>1.7.5</version>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-log4j12</artifactId>
            <version>1.7.5</version>
        </dependency>

        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-lang3</artifactId>
            <version>3.3.2</version>
        </dependency>

        <dependency>
            <groupId>commons-io</groupId>
            <artifactId>commons-io</artifactId>
            <version>2.4</version>
        </dependency>

        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
        </dependency>

        <dependency>
            <groupId>commons-collections</groupId>
            <artifactId>commons-collections</artifactId>
            <version>3.2.1</version>
        </dependency>

        <dependency>
            <groupId>org.quartz-scheduler</groupId>
            <artifactId>quartz</artifactId>
            <version>1.8.5</version>
        </dependency>

        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
            <version>1.2.8</version>
        </dependency>

        <dependency>
            <groupId>org.hibernate</groupId>
            <artifactId>hibernate-validator</artifactId>
            <version>4.2.0.Final</version>
        </dependency>
        <!--common end -->

        <!--spring start -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
            <version>${spring.version}</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-beans</artifactId>
            <version>${spring.version}</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>${spring.version}</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context-support</artifactId>
            <version>${spring.version}</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-aop</artifactId>
            <version>${spring.version}</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-aspects</artifactId>
            <version>${spring.version}</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-expression</artifactId>
            <version>${spring.version}</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-tx</artifactId>
            <version>${spring.version}</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-test</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <!--spring end -->

        <!--springmvc 控制层 start -->
        <dependency>
            <groupId>jstl</groupId>
            <artifactId>jstl</artifactId>
            <version>1.2</version>
        </dependency>

        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <version>3.1.0</version>
        </dependency>

        <dependency>
            <groupId>taglibs</groupId>
            <artifactId>standard</artifactId>
            <version>1.1.2</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-web</artifactId>
            <version>${spring.version}</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>${spring.version}</version>
        </dependency>

        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.7.4</version>
        </dependency>
        <!--springmvc end -->

        <!--mybatis 数据访问层 start -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.38</version>
            <scope>runtime</scope>
        </dependency>

        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>1.0.20</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>${spring.version}</version>
        </dependency>

        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.4.0</version>
        </dependency>

        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis-spring</artifactId>
            <version>1.3.0</version>
        </dependency>

        <dependency>
            <groupId>redis.clients</groupId>
            <artifactId>jedis</artifactId>
            <version>2.8.0</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.data</groupId>
            <artifactId>spring-data-redis</artifactId>
            <version>1.6.4.RELEASE</version>
        </dependency>

        <dependency>
            <groupId>com.dyuproject.protostuff</groupId>
            <artifactId>protostuff-core</artifactId>
            <version>1.0.8</version>
        </dependency>

        <dependency>
            <groupId>com.dyuproject.protostuff</groupId>
            <artifactId>protostuff-runtime</artifactId>
            <version>1.0.8</version>
        </dependency>
        <!--mybatis 数据访问层 end -->
    </dependencies>
    <!-- Build Settings -->
    <!--http://maven.apache.org/pom.html#Build_Settings -->
    <build>
        <finalName>${project.artifactId}_${project.version}_${maven.build.timestamp}</finalName>
        <resources>
            <resource>
                <!--directory: 资源所在的位置 -->
                <directory>src/main/resources</directory>
                <!--filtering: 是否替换资源中的属性placehold -->
                <filtering>true</filtering>
            </resource>
        </resources>
        <plugins>
            <plugin>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>2.3.2</version>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-war-plugin</artifactId>
                <version>2.6</version>
            </plugin>
        </plugins>
    </build>
</project>

```

(3)web.xml

```
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee 
    http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
    version="3.1">

    <!--配置DispatcherServlet -->
    <servlet>
        <servlet-name>spring-dispatcher</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <!-- 配置SpringMVC需要加载的配置文件 spring-xxx.xml -->
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:spring/spring-*.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>spring-dispatcher</servlet-name>
        <!--默认匹配所有的请求 -->
        <url-pattern>/</url-pattern>
    </servlet-mapping>

    <!-- druid -->
    <servlet>
        <servlet-name>DruidStatView</servlet-name>
        <servlet-class>com.alibaba.druid.support.http.StatViewServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>DruidStatView</servlet-name>
        <url-pattern>/druid/*</url-pattern>
    </servlet-mapping>

    <filter>
        <filter-name>DruidWebStatFilter</filter-name>
        <filter-class>com.alibaba.druid.support.http.WebStatFilter</filter-class>
        <init-param>
            <param-name>exclusions</param-name>
            <param-value>*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>DruidWebStatFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>

</web-app>
```
上面主要配置了DispatcherServlet，这是非常重要的，还有配置了Druid，以后我们就可以通过访问:youurl+/druid看到一些监控数据了。

(4)接下来我们就要在spring文件夹里面配置了，先配置spring-dao.xml文件，这里主要配置：数据库连接参数，连接池，SqlSessionFactory对象（mybatis），扫描dao层接口，动态实现dao接口
spring-dao.xml

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd 
        http://www.springframework.org/schema/context 
        http://www.springframework.org/schema/context/spring-context.xsd"
>

    <!--配置整合mybatis过程-->

    <!--1、配置数据库相关参数-->
    <context:property-placeholder location="classpath:jdbc.properties" ignore-unresolvable="true"/>

    <!--2.数据源druid -->
    <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource" 
            init-method="init" destroy-method="close">
        <property name="driverClassName" value="${jdbc.driverClassName}" />
        <property name="url" value="${jdbc.url}" />
        <property name="username" value="${jdbc.username}" />
        <property name="password" value="${jdbc.password}" />
        
         <!-- 配置初始化大小、最小、最大 -->  
        <property name="initialSize" value="${druid.pool.size.init}" />  
        <property name="minIdle" value="${druid.pool.size.min}" />   
        <property name="maxActive" value="${druid.pool.size.max}" />  
        
        <!-- 配置监控统计拦截的filters，wall用于防止sql注入，stat用于统计分析 -->
        <property name="filters" value="wall,stat" /> 
    </bean>
    

    <!--3、配置SqlSessionFactory对象-->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <!--注入数据库连接池-->
        <property name="dataSource" ref="dataSource"/>
        <!--配置mybatis全局配置文件:mybatis-config.xml-->
        <property name="configLocation" value="classpath:mybatis-config.xml"/>
        <!--扫描entity包,使用别名,多个用;隔开-->
        <property name="typeAliasesPackage" value="com.pretty.ssm.entity"/>
        <!--扫描sql配置文件:mapper需要的xml文件-->
        <property name="mapperLocations" value="classpath:mapper/*.xml"/>
    </bean>

    <!--4、配置扫描Dao接口包,动态实现DAO接口,注入到spring容器-->
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <!--注入SqlSessionFactory-->
        <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"/>
        <!-- 给出需要扫描的Dao接口-->
        <property name="basePackage" value="com.pretty.ssm.dao"/>
    </bean>

</beans>

```
**这里千万别忘记配置一下jdbc.properties**

```
jdbc.driverClassName=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://127.0.0.1:3306/pretty_ssm?useUnicode=true&characterEncoding=UTF-8&useSSL=false
jdbc.username=root
jdbc.password=shaoqing

druid.pool.size.max=20
druid.pool.size.min=3
druid.pool.size.init=3
```
**上面useSSL=false一定要下，或者true，如果你用的是mysql高版本**

接下来是另外一个套餐，mybatis-config.xml

```
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <!--配置全局属性-->
    <settings>
        <!--使用jdbc的getGeneratekeys获取自增主键值-->
        <setting name="useGeneratedKeys" value="true"/>
        <!--使用列别名替换别名　　默认true-->
        <setting name="useColumnLabel" value="true"/>
        <!--开启驼峰命名转换Table:create_time到 Entity(createTime)-->
        <setting name="mapUnderscoreToCamelCase" value="true"/>
    </settings>
</configuration>

```

(5)配置完了DAO层，接下来就是service层了。在spring中新建spring-service.xml文件。需要的工作有：扫描service包所有注解 @Service；配置事务管理器，把事务管理交由spring来完成；配置基于注解的声明式事务，可以直接在方法上@Transaction

**spring-service.xml**
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xsi:schemaLocation="
        http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd
        http://www.springframework.org/schema/aop
        http://www.springframework.org/schema/aop/spring-aop.xsd
        http://www.springframework.org/schema/tx
        http://www.springframework.org/schema/tx/spring-tx.xsd">

    <!--扫描service包(包含子包)下所有使用注解的类型-->
    <context:component-scan base-package="com.pretty.ssm.service"/>

    <!--配置事务管理器(mybatis采用的是JDBC的事务管理器)-->
    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource"></property>
    </bean>

    <!--配置基于注解的声明式事务,默认使用注解来管理事务行为-->
    <tx:annotation-driven transaction-manager="transactionManager"/>

</beans>

```

(6)配置web层，在spring文件夹新建spring-web.xml，需要的工作有：开启SpringMVC注解模式，可以使用@RequestMapping，@PathVariable，@ResponseBody等；对静态资源处理，如js，css，jpg等；配置jsp 显示ViewResolver；扫描web层 @Controller

**spring-web.xml**

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd
        http://www.springframework.org/schema/mvc 
        http://www.springframework.org/schema/mvc/spring-mvc.xsd http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd">


    <!-- 激活组件扫描功能,扫描aop的相关组件组件 -->
    <context:component-scan base-package="com.pretty.ssm.aop"/>
    <!-- 启动对@AspectJ注解的支持 -->
    <aop:aspectj-autoproxy proxy-target-class="true" />

    <!--简化配置:
        1、自动注册DefaultAnnotationHandlerMapping,AnnotationMethodHandlerAdapter
        2、提供一系列:数据绑定,数字和日期的format,@NumberFormat,@DataTimeFormat,xml,json默认读写支持
    -->
    <mvc:annotation-driven/>

    <!--静态资源默认servlet配置
        1、加入对静态资源的处理:js,css,gif,png
        2、允许使用"/"做整体映射
    -->
    <mvc:default-servlet-handler/>

    <!--配置JSP　显示ViewResolver-->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="viewClass" value="org.springframework.web.servlet.view.JstlView"/>
        <property name="prefix" value="/WEB-INF/jsp/"/>
        <property name="suffix" value=".jsp"/>
    </bean>

    <!--扫描web相关的controller-->
    <context:component-scan base-package="com.pretty.ssm.web"/>

</beans>
```
(7)接下来来做个小实例
首先新建一个数据库，命名为pretty_ssm,然后导入sql文件夹下面的sql文件，接着在entity层下面添加一个实体User,然后在dao层下面新建一个UserDao接口，接着在mapper文件夹下面新建UserDao.xml，编程相关的sql语句。然后在service层新建UserService接口以及实现类了，这些相关代码我都放在git（[实例代码](https://github.com/wacxt/pretty_ssm)）上面了，可以借鉴着写，喜欢记得给个星。
接下来就是验证的时刻了，访问http://localhost:8080/yourproject/user/list,如果能正常启动并且看到json数据，那么说明配置完成。

**跑不起来的看这里**
注意：如果本地没有安装redis的，个人建议安装，这确实是个好东西，如果实在不想安装，那么就不要导入spring-redis.xml，UserServiceImpl文件中的getUserList中，去掉跟redis相关的代码。

(8)这个项目只是想当作一个脚手架，以后需要可以直接使用，而且是为了后面搭建一个高可用的分布式架构做准备，所以这里牵扯到了redis，接下来将讲解redis缓存和redis sentinel高可用方案，而且是基于docker来实现，链接如下：[Redis Sentinel高可用方案（基于docker）](http://blog.csdn.net/KilluaZoldyck/article/details/72720953)


