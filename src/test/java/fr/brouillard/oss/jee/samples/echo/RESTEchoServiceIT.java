package fr.brouillard.oss.jee.samples.echo;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.extension.rest.client.ArquillianResteasyResource;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import fr.brouillard.oss.jee.samples.JaxrsStarter;

@RunWith(Arquillian.class)
public class RESTEchoServiceIT {
    @Deployment(testable=false)
    public static WebArchive createDeployment() {
        WebArchive war = ShrinkWrap.create(WebArchive.class)
                .addPackages(true, "fr.brouillard.oss.jee.samples")
                .addClass(JaxrsStarter.class)
                .addAsResource(EmptyAsset.INSTANCE, "META-INF/beans.xml")
                ;
        return war;
    }
    
    @Test
    public void echo_answers_null_for_null_message(@ArquillianResteasyResource("resources") WebTarget target) {
        Response resp = target.path("echo").request(MediaType.TEXT_PLAIN).get();
        assertThat(resp, notNullValue());
        assertThat(resp.getStatus(), is(Status.NO_CONTENT.getStatusCode()));
        
        String strResponse = resp.readEntity(String.class);
        assertThat(strResponse, nullValue());
        
        resp.close();
    }
    
    @Test
    public void echo_answers_identity_for_a_given_message(@ArquillianResteasyResource("resources") WebTarget target) {
        String fullMessage = "Lorem ipsum dolor sit amet";
        Response resp = target.path("echo").queryParam("message", fullMessage).request(MediaType.TEXT_PLAIN).get();
        
        assertThat(resp, notNullValue());
        assertThat(resp.getStatus(), is(Status.OK.getStatusCode()));
        
        String strResponse = resp.readEntity(String.class);
        assertThat(strResponse, is(fullMessage));
        
        resp.close();
    }
}
