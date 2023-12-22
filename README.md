# Configuration class for multiple databases
```
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "multiEntityManager",
        transactionManagerRef = "multiTransactionManager")
@EntityScan("com.example.mdi.*")
public class DatabaseConfiguration {
    //add JPA entities path here
    private final String PACKAGE_SCAN = "com.example.*";

    //set db1 as the primary and default database connection
    @Primary
    @Bean(name = "db1DataSource")
    @ConfigurationProperties("spring.datasource.db1")
    public DataSource db1DataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    //connection objects for the remaining 2 databases
    @Bean(name = "db2DataSource")
    @ConfigurationProperties("spring.datasource.db2")
    public DataSource db2DataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    //The multidatasource configuration
    @Bean(name = "multiRoutingDataSource")
    public DataSource multiRoutingDataSource() {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(ClientNames.DB1, db1DataSource());
        targetDataSources.put(ClientNames.DB2, db2DataSource());
        MultiRoutingDataSource multiRoutingDataSource
                = new MultiRoutingDataSource();
        multiRoutingDataSource.setDefaultTargetDataSource(db1DataSource());
        multiRoutingDataSource.setTargetDataSources(targetDataSources);
        return multiRoutingDataSource;
    }

    //add multi entity configuration code
    @Bean(name = "multiEntityManager")
    public LocalContainerEntityManagerFactoryBean multiEntityManager() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(multiRoutingDataSource());
        em.setPackagesToScan(PACKAGE_SCAN);
        HibernateJpaVendorAdapter vendorAdapter
                = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(hibernateProperties());
        return em;
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager multiTransactionManager() {
        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                multiEntityManager().getObject());
        return transactionManager;
    }

    @Primary
    @Bean(name="entityManagerFactory")
    public LocalSessionFactoryBean dbSessionFactory() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(multiRoutingDataSource());
        sessionFactoryBean.setPackagesToScan(PACKAGE_SCAN);
        sessionFactoryBean.setHibernateProperties(hibernateProperties());
        return sessionFactoryBean;
    }

    //add hibernate properties
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.show_sql", true);
        properties.put("hibernate.format_sql", true);
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
//        properties.put("hibernate.id.new_generator_mappings", false);
//        properties.put("hibernate.jdbc.lob.non_contextual_creation", true);
        properties.put("hibernate.hbm2ddl.auto", "update");
        return properties;
    }
}
```


# MultiRoutingDataSource
```
class MultiRoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DBContextHolder.getCurrentDb();
    }
}
```

# DBContextHolder
```
public class DBContextHolder {
    private static final ThreadLocal<ClientNames> contextHolder = new ThreadLocal<>();
    public static void setCurrentDb(ClientNames dbType) {
        contextHolder.set(dbType);
    }
    public static ClientNames getCurrentDb() {
        return contextHolder.get();
    }
    public static void clear() {
        contextHolder.remove();
    }
}
```

# ClientNames
```
public enum ClientNames {
    DB1, DB2
}
```


