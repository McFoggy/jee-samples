package fr.brouillard.oss.jee.samples.echo;

import static org.junit.Assert.assertThat;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.nullValue;

public class TestEchoService {
    @Test
    public void echo_answers_null_for_null_message() {
        EchoService es = new EchoService();
        assertThat(es.echo(null), nullValue());
    }
    
    @Test
    public void echo_answers_identity_for_a_given_message() {
        String fullMessage = "Lorem ipsum dolor sit amet";
        EchoService es = new EchoService();

        assertThat(es.echo(fullMessage), is(fullMessage));
        
        for (String word : fullMessage.split("\\s")) {
            assertThat(es.echo(word), is(word));
        }
    }
}
