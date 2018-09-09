package com.example.springplayground.Service;

import com.example.springplayground.Config.MovieConfig;
import com.example.springplayground.Model.Movie;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieServiceTest {

    @MockBean
    private MovieConfig movieConfig;
    private MovieService movieService;
    private MockRestServiceServer mockServer;

    @Before
    public void setUp() throws Exception {

        when(movieConfig.getHost()).thenReturn("http://www.omdbapi.com");
        when(movieConfig.getApiKey()).thenReturn("b983e82f");

        movieService = new MovieService(movieConfig);
        mockServer  = MockRestServiceServer.createServer(movieService.getRestTemplate());
    }

    @Test
    public void getMovies_returnsTheCorrectResponse() throws Exception {
        String json = getJSON("/movie.json");

        URI targetUrl= UriComponentsBuilder.fromUriString(movieConfig.getHost())
                .path("/")
                .queryParam("apikey", movieConfig.getApiKey())
                .queryParam("s", "Harry Potter and the Deathly Hallows: Part 2")
                .build()
                .encode()
                .toUri();

        Movie movie = new Movie();
        movie.setTitle("Harry Potter and the Deathly Hallows: Part 2");
        movie.setImdbId("tt1201607");
        movie.setPoster("https://m.media-amazon.com/images/M/MV5BMjIyZGU4YzUtNDkzYi00ZDRhLTljYzctYTMxMDQ4M2E0Y2YxXkEyXkFqcGdeQXVyNTIzOTk5ODM@._V1_SX300.jpg");
        movie.setYear("2011");

        List<Movie> expected = new ArrayList<Movie>();
        expected.add(movie);

        mockServer
                .expect(requestTo(targetUrl))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(json, MediaType.APPLICATION_JSON));

        assertThat(movieService.getMovies("Harry Potter and the Deathly Hallows: Part 2").get(0).getTitle(), equalTo(expected.get(0).getTitle()));
        mockServer.verify();
    }

    private String getJSON(String path) throws Exception {
        URL url = this.getClass().getResource(path);
        return new String(Files.readAllBytes(Paths.get(url.getFile())));
    }

}