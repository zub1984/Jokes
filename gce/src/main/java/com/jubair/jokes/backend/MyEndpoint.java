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
/*Copyright (C) 2015  Mohammad Jubair Khan (zub1984.kn@gmail.com) - An Udacity Android Nano Degree, Project 4: Build It Bigger.
        Licensed under the Apache License, Version 2.0 (the "License");
        you may not use this file except in compliance with the License.
        You may obtain a copy of the License at
        http://www.apache.org/licenses/LICENSE-2.0
        Unless required by applicable law or agreed to in writing, software
        distributed under the License is distributed on an "AS IS" BASIS,
        WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
        See the License for the specific language governing permissions and
        limitations under the License.*/
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
