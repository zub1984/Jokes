/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.jubair.jokes.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.jubair.jokes.Joker;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.jokes.jubair.com",
                ownerName = "backend.jokes.jubair.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    /**
     * A simple endpoint method that provides a joke
     */
    @ApiMethod(name = "getJokes")
    public JokerBean getJokes() {
        JokerBean response = new JokerBean();
        Joker joker = new Joker();
        response.setData(joker.getJoke());
        return response;
    }

}
