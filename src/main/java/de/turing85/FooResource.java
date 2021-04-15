package de.turing85;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("foo")
public class FooResource {

  private final FooRepository fooRepository;

  public FooResource(FooRepository fooRepository) {
    this.fooRepository = fooRepository;
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public void foo(Foo foo) {
    fooRepository.save(foo);
  }
}
