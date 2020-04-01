### useful endpoints:
--------------------------------
/monitoring -JavaMelody
--------------------------------

### Online tool to generate encripted value for Jasypt
https://www.devglan.com/online-tools/jasypt-online-encryption-decryption

### Run the project with the commandline with Jasypt secret instead of adding it to properties file:
mvn-Djasypt.encryptor.password=secretkye spring-boot:run

#### Don't forget to add this to main application class
@EnableEncryptableProperties




# CACHING
## @Cacheable
@Cacheable(value = "users", key = "#userId")
public User getUser(String userId) {
  return userRepository.findOne(Long.valueOf(userId));
}

@Cacheable(cacheNames = "myControlledCache", key = "'myControlledPrefix_'.concat(#relevant)")
public String getFromCache(String relevant) {
    return null;
}

## CACHE EVICT
@CacheEvict(cacheNames = "myControlledCache", key = "'myControlledPrefix_'.concat(#relevant)")
public void removeFromCache(String relevant) {
}

@CacheEvict(cacheNames = "myControlledCache", allEntries=true)
public void removeFromCache(String relevant) {
}

@CacheEvict(cacheNames = "myControlledCache"){...}

@CacheEvict(value="addresses", allEntries=true)
public String getAddress(Customer customer) {...}

## CACHE PUT
@CachePut(cacheNames = "myControlledCache"){...}

@CachePut(cacheNames = "myControlledCache", key = "'myControlledPrefix_'.concat(#relevant)")
public String populateCache(String relevant, String unrelevantTrackingId) {
    return "this is it again!";
}

@CachePut(value="addresses")
public String getAddress(Customer customer) {...}

### @CacheConfig annotation, you can streamline some of the cache configuration into a single place – at the class level – so that you don't have to declare things multiple times:
--------------------------------------------------
@CacheConfig(cacheNames={"addresses"})
public class CustomerDataService {

    @Cacheable
    public String getAddress(Customer customer) {...}
---------------------------------------------------------------

### control the caching based on the output of the method rather than the input – via the unless parameter:
-------------------------------------------------------------
@CachePut(value="addresses", unless="#result.length()<64")
public String getAddress(Customer customer) {...}
-------------------------------------------------------------
### 
-------------------------------------------------------------
@Cacheable(value="book", condition="#name.length < 50")
public Book findStoryBook (String name)
-------------------------------------------------------------

### 
-------------------------------------------------------------
@CachePut(value="addresses", condition="#customer.name=='Tom'")
public String getAddress(Customer customer) {...}
-------------------------------------------------------------