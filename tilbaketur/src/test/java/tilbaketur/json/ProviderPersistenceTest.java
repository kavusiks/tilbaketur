package tilbaketur.json;

import static org.junit.jupiter.api.Assertions.assertEquals;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import org.junit.jupiter.api.Test;

import tilbaketur.core.Provider;

public class ProviderPersistenceTest extends AbstractPersistenceTest {

    @Test
    public void providerSerializerTest() throws JsonProcessingException {
        assertEquals(providerCorrectFormat, mapper.writeValueAsString(provider1));
    }

    @Test
    public void providerDeserializerTest() throws JsonMappingException, JsonProcessingException {
        Provider provider2 = mapper.readValue(providerCorrectFormat, Provider.class);
        checkTwoProviders(provider1, provider2);
    }

   
}