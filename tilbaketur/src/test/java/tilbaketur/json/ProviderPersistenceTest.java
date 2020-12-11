package tilbaketur.json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import tilbaketur.core.Provider;

public class ProviderPersistenceTest {
    static ObjectMapper mapper;
    static Provider provider1 = new Provider("provider1", "Provider123", "CarRentals");
    static String providerCorrectFormat = "{\"name\":\"CarRentals\",\"username\":\"provider1\",\"password\":\"Provider123\"}";

    @BeforeAll
    public static void setUp() {
        mapper = new ObjectMapper();
        mapper.registerModule(new TilbaketurModule());
    }

    @Test
    public void providerSerializerTest() throws JsonProcessingException {
        assertEquals(providerCorrectFormat, mapper.writeValueAsString(provider1));
    }

    @Test
    public void providerDeserializerTest() throws JsonMappingException, JsonProcessingException {
        Provider provider2 = mapper.readValue(providerCorrectFormat, Provider.class);
        checkTwoProviders(provider1, provider2);
        //assertTrue(provider1.equals(provider2));
    }

    public static void checkTwoProviders(Provider provider1, Provider provider2) {
        assertEquals(provider1.getName(),provider2.getName());
        assertEquals(provider1.getUsername(), provider2.getUsername());
        assertEquals(provider1.getPassword(), provider2.getPassword());
        assertEquals(provider1.getCars(), provider2.getCars());
    }
}