package fr.brouillard.oss.jee.samples.echo;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class EchoServiceInServerIT {
    @Deployment
    public static JavaArchive createDeployment() {
        JavaArchive jar = ShrinkWrap.create(JavaArchive.class)
                .addPackages(true, "fr.brouillard.oss.jee.samples")
                .addAsResource(EmptyAsset.INSTANCE, "META-INF/beans.xml")
                ;
        return jar;
    }
    
    @Inject private EchoService es = new EchoService();

    @Test
    public void echo_answers_null_for_null_message() {
        assertThat(es.echo(null), nullValue());
    }
    
    @Test
    public void echo_answers_identity_for_a_given_message() {
        String fullMessage = "Lorem ipsum dolor sit amet";
        assertThat(es.echo(fullMessage), is(fullMessage));
        
        for (String word : fullMessage.split("\\s")) {
            assertThat(es.echo(word), is(word));
        }
    }
}
