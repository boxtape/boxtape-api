package io.boxtape.core;

import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.boxtape.yaml.Mappers;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;

public class DynamicPlayTest {

    @Test
    public void canReadFromYaml() throws IOException {
        String yaml = IOUtils.toString(ClassLoader.getSystemResourceAsStream("example.yml"));
        ObjectMapper mapper = Mappers.Yaml.mapper();
        Recipe play = mapper.readValue(yaml, Recipe.class);
        assertThat(play.getName(), is("MySql"));
        assertThat(play.getResolutions(), hasSize(1));
        assertThat(play.getForwardedPorts(), hasSize(1));
        assertThat(play.getRoles(), hasSize(1));
        assertThat(play.getProperties().entrySet(),  hasSize(3));
    }

}
